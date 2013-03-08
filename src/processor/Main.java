package processor;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.concurrent.*;

public class Main {
    private static String SOURCE_FILE = "/Users/yasha/sink/source.tsv";
    private static String DEST_FILE = "/Users/yasha/sink/dest.tsv";
    private static int PRODUCER_NTHREADS = 4;

    public static void main(String[] args) {

        OutputStream writer = null;
        InputStream reader = null;
        try{

            ExecutorService consumeExecutor = Executors.newSingleThreadExecutor();
            ThreadPoolExecutor produceExecutor = new ThreadPoolExecutor(PRODUCER_NTHREADS, PRODUCER_NTHREADS, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10)){
                protected void afterExecute(Runnable r, Throwable t) {
                    super.afterExecute(r, t);
                    if (t == null && r instanceof Future<?>) {
                        try {
                            Future<?> future = (Future<?>) r;
                            if (future.isDone())
                                future.get();
                        } catch (CancellationException ce) {
                            t = ce;
                        } catch (ExecutionException ee) {
                            t = ee.getCause();
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt(); // ignore/reset
                        }
                    }
                    if (t != null){
                        System.out.println(t);
                        this.shutdownNow();
                    }

                }

            };
            produceExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

            reader = new FileInputStream(new File(SOURCE_FILE));
            writer = new FileOutputStream(new File(DEST_FILE));
            BlockingQueue<SerialReadProducer> writeQ= new ArrayBlockingQueue<SerialReadProducer>(10);

            //Start the consumer
            Runnable consumerWorker = new AsyncWriteConsumer(writer, writeQ);
            consumeExecutor.execute(consumerWorker);

            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(reader));
            while((line = br.readLine()) != null){
                Runnable worker = new LineProcessorRunnable(line, writeQ, new CompanySerialReadProducer(line));
                produceExecutor.execute(worker);
             }


            produceExecutor.shutdown();
            ((AsyncWriteConsumer)consumerWorker).stop();
            consumeExecutor.shutdown();

            while(!produceExecutor.isTerminated() || !consumeExecutor.isTerminated()){

            }

            System.out.println("All threads terminated");

        } catch (IOException ex){
            ex.printStackTrace();

        } finally{
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(writer);
        }

       //generateSourceFile(1000000);
    }

    public static void generateSourceFile(int lines){

    }
}

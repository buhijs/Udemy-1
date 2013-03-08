package processor;

import decoders.CompanyToBase64Decoder;
import encoders.LineToCompanyEncoder;
import org.apache.commons.io.IOUtils;
import processors.CompanyProcessor;

import java.io.*;
import java.util.concurrent.*;

public class Main {
    private static String SOURCE_FILE = "/Users/yasha/sink/source.tsv";
    private static String DEST_FILE = "/Users/yasha/sink/dest.tsv";
    private static String ERROR_FILE = "/Users/yasha/sink/error.tsv";
    private static int PRODUCER_NTHREADS = 4;

    public static void main(String[] args) {

        OutputStream writer = null;
        OutputStream errorWriter = null;
        InputStream reader = null;
        try{

            ExecutorService consumeExecutor = Executors.newSingleThreadExecutor();
            ExecutorService errorConsumeExecutor = Executors.newSingleThreadExecutor();
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
            errorWriter = new FileOutputStream(new File(ERROR_FILE));
            BlockingQueue<String> writeQ= new ArrayBlockingQueue<String>(10);
            BlockingQueue<String> errorWriteQ= new ArrayBlockingQueue<String>(10);

            //Start the consumer
            Runnable consumerWorker = new WriteProcessorRunnable(writer, writeQ);
            consumeExecutor.execute(consumerWorker);

            //Start the error consumer
            Runnable errorConsumerWorker = new WriteProcessorRunnable(errorWriter, errorWriteQ);
            errorConsumeExecutor.execute(errorConsumerWorker);


            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(reader));
            while((line = br.readLine()) != null){
                Runnable worker = new ProcessorRunnable(new LineToCompanyEncoder(),
                                                            new CompanyToBase64Decoder('\t'),
                                                            new CompanyProcessor(),
                                                            line,
                                                            writeQ,
                                                            errorWriteQ);
                produceExecutor.execute(worker);
             }


            produceExecutor.shutdown();
            ((WriteProcessorRunnable)consumerWorker).stop();
            ((WriteProcessorRunnable)errorConsumerWorker).stop();
            consumeExecutor.shutdown();
            errorConsumeExecutor.shutdown();

            while(!produceExecutor.isTerminated() || !consumeExecutor.isTerminated() || !errorConsumeExecutor.isTerminated()){

            }

            System.out.println("All threads terminated");

        } catch (IOException ex){
            ex.printStackTrace();

        } finally{
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(errorWriter);

        }

       //generateSourceFile(1000000);
    }

    public static void generateSourceFile(int lines){

    }
}

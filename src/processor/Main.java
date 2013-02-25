package processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.*;

public class Main {
    private static String SOURCE_FILE = "/Users/yashr/sink/source.tsv";
    private static String DEST_FILE = "/Users/yashr/sink/dest.tsv";
    private static int NTHREADS = 3;

    public static void main(String[] args) {

        CompanyFileWriter writer = null;
        try{

            //ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
            ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

            writer = new CompanyFileWriter(DEST_FILE);

            String line;
            BufferedReader br = new BufferedReader(new FileReader(SOURCE_FILE));


            while((line = br.readLine()) != null){
                Runnable worker = new CompanyRunnable(line, writer);
                executor.execute(worker);
             }


            executor.shutdown();
            while(!executor.isTerminated()){

            }

            writer.closeFile();
            System.out.println("All threads terminated");

        } catch( Exception ex){
            ex.printStackTrace();
        }

       //generateSourceFile(1000000);
    }

    public static void generateSourceFile(int lines){
                try {
            CompanyFileWriter writer = new CompanyFileWriter(SOURCE_FILE);
            for(int i=0;i<lines;i++){
                writer.write("field1\tfield2\tfield3\tfield4") ;
            }
            writer.closeFile();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}

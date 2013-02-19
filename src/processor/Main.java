package processor;

import com.sun.tools.javac.util.Paths;
import sun.nio.cs.StandardCharsets;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static String SOURCE_FILE = "/Users/yashr/sink/source.tsv";
    private static String DEST_FILE = "/Users/yashr/sink/dest.tsv";
    private static int NTHREADS = 10;

    public static void main(String[] args) {
        CompanyFileReader reader = null;
        CompanyFileWriter writer = null;
        try{

            ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

            reader = new CompanyFileReader(SOURCE_FILE);
            writer = new CompanyFileWriter(DEST_FILE);

            int i = 10;
            Charset ENCODING = StandardCharsets.UTF8;
            Path path = Paths.get(SOURCE_FILE);
            Scanner scanner =  new Scanner(path, ENCODING.name();
            while (scanner.hasNextLine()){
                Runnable worker = new CompanyRunnable(reader, writer);
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


    }
}

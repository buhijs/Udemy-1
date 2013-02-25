package processor;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/18/13
 * Time: 8:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyFileWriter {
    private static BufferedWriter writer = null;

    public CompanyFileWriter(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    public static void write(String line) throws IOException {
        writer.write(line+"\n");
    }

    public static void closeFile() throws IOException {
        writer.close();
    }
}

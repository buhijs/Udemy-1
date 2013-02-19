package processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/18/13
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyFileReader{

    public static BufferedReader getReader() {
        return reader;
    }

    public static void setReader(BufferedReader reader) {
        CompanyFileReader.reader = reader;
    }

    private static BufferedReader reader = null;

    public  CompanyFileReader(String fileName) throws FileNotFoundException {
        loadCSVFile(fileName);

    }

    /**
     * Load supplied file as UTF-8.
     *
     * @param fileName
     *            Name of the file
     * @return BufferedReader instance with UTF-8 character encoding
     *
     * @author Yash Ranadive
     */
    public static BufferedReader loadCSVFile(String fileName) throws FileNotFoundException {
        return new BufferedReader(new FileReader(fileName));
    }

    public static String readNext() throws IOException {
        return reader.readLine();
    }

}

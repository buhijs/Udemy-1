package processor;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/18/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyRunnable implements Runnable {

    private CompanyFileWriter writer = null;
    String line = null;

    public CompanyRunnable(String line, CompanyFileWriter writer)  {
        this.line = line;
        this.writer = writer;
    }

    public void run(){
        //Split the line and process the contents
        try{

            String[] components= line.split("\t");
            Company company = new Company();
            company.setField1(components[0]);
            company.setField2(components[1]);
            company.setField3(components[2]);
            company.setField4(components[3]);

            company.clean();

            CompanyFileWriter.write(company.toString());
        }  catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



    }
}

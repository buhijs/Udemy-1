package processor;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/18/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyProducer implements Runnable {

    private BlockingQueue<Company> writeQ;
    private String line;

    public CompanyProducer(String line, BlockingQueue<Company> writeQ)  {
        this.line = line;
        this.writeQ = writeQ;
    }

    public void run(){

            //Split the line and process the contents
            String[] components= line.split("\t");
            Company company = new Company();
            company.setField1(components[0]);
            company.setField2(components[1]);
            company.setField3(components[2]);
            company.setField4(components[3]);

            company.clean();

            //Wait if quueue is at capacity
            while(!writeQ.offer(company)){}


         //What if there's an error
        //download source for threadpoolexecutor
        //write should be synchronized
        //update injellij
        //synchronize locks apply to what/synchornize block/synchornize static mehtod/renetrant lock

    }
}

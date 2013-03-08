package processor;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/18/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineProcessorRunnable implements Runnable {

    private BlockingQueue<SerialReadProducer> writeQ;
    private String line;
    private SerialReadProducer readProducer;

    public LineProcessorRunnable(String line, BlockingQueue<SerialReadProducer> writeQ, SerialReadProducer readProducer)  {
        this.line = line;
        this.writeQ = writeQ;
        this.readProducer = readProducer;
    }

    public void run(){
            readProducer.process();

            //Wait if queue is at capacity
            while(!writeQ.offer(readProducer)){}

    }
}

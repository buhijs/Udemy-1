package processor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/18/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class AsyncWriteConsumer<T> implements Runnable {

    private OutputStream writer;
    private BlockingQueue<T> readQ;
    private volatile boolean stopSignal = false;

    public AsyncWriteConsumer(OutputStream writer, BlockingQueue<T> readQ){

        this.readQ = readQ;
        this.writer = writer;

    }

    public void run(){
        try {
            while(!stopSignal || !readQ.isEmpty()){
                write();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void stop(){
        stopSignal = true;
    }

    private void write() throws IOException {
        if(!readQ.isEmpty()) {
            writer.write((((T)readQ.poll()).toString() + "\n").getBytes());
            //writer.write((((T)readQ.poll()).toString() + "\n").getBytes());
            writer.flush();
        }
    }
}

package processor;

import decoders.Decoder;
import encoders.Encoder;
import processors.Processor;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/18/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProcessorRunnable<T,V,M> implements Runnable {
    private Encoder<T,V> encoder;
    private Decoder<V,M> decoder;
    private Processor<V> processor;
    private T input;

    private BlockingQueue<M> writeQ;
    private BlockingQueue<T> errorWriteQ;

    public ProcessorRunnable(Encoder<T, V> encoder, Decoder<V, M> decoder, Processor<V> processor, T input, BlockingQueue<M> writeQ, BlockingQueue<T> errorWriteQ)  {
        this.encoder = encoder;
        this.decoder = decoder;
        this.processor = processor;
        this.input = input;
        this.writeQ = writeQ;
        this.errorWriteQ = errorWriteQ;
    }

    public void run(){
        M output = null;
        try{
            output = decoder.decode(processor.process(encoder.encode(input)));
            //Wait if queue is at capacity
            while(!writeQ.offer(output)){}
        } catch (Exception ex){
            ex.printStackTrace();
            while(!errorWriteQ.offer(input)){};
        }


    }
}

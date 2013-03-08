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
public class LineProcessorRunnable<T,V,M> implements Runnable {
    private Encoder<T,V> encoder;
    private Decoder<V,M> decoder;
    private Processor<V> processor;
    private T input;

    private BlockingQueue<M> writeQ;

    public LineProcessorRunnable(Encoder<T,V> encoder, Decoder<V,M> decoder, Processor<V> processor, T input, BlockingQueue<M> writeQ)  {
        this.encoder = encoder;
        this.decoder = decoder;
        this.processor = processor;
        this.input = input;
        this.writeQ = writeQ;
    }

    public void run(){
        M output = decoder.decode(processor.process(encoder.encode(input)));

        //Wait if queue is at capacity
        while(!writeQ.offer(output)){}

    }
}

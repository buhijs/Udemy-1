package misc;

import decoders.Decoder;
import decoders.DecoderException;
import encoders.Encoder;
import encoders.EncoderException;
import processors.Processor;
import processors.ProcessorException;

import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/18/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class PipelineRunnable<T,V,M> implements Runnable {
    private Decoder<T,V> decoder;
    private Encoder<V,M> encoder;
    private Processor<V> processor;
    private T input;

    private BlockingQueue<M> writeQ;
    private BlockingQueue<T> errorWriteQ;

    public PipelineRunnable(Decoder<T, V> decoder, Encoder<V, M> encoder, Processor<V> processor, T input, BlockingQueue<M> writeQ, BlockingQueue<T> errorWriteQ)  {
        this.decoder = decoder;
        this.encoder = encoder;
        this.processor = processor;
        this.input = input;
        this.writeQ = writeQ;
        this.errorWriteQ = errorWriteQ;
    }

    public void run(){
        M output = null;
        V processedOutput = null;
        try{
            processedOutput = processor.process(decoder.decode(input));
            // Do not process filtered records that are marked as null after processing
            if(processedOutput != null) {
                output = encoder.encode(processedOutput);
                if(output != null){
                    //Wait if queue is at capacity
                    while(!writeQ.offer(output)){}
                }
            }
        } catch (EncoderException ex){
            writeToErrorQueueAndPrintStackTrace(ex);
        } catch (DecoderException ex){
            writeToErrorQueueAndPrintStackTrace(ex);
        } catch (ProcessorException ex){
            writeToErrorQueueAndPrintStackTrace(ex);
        }
    }

    public void writeToErrorQueueAndPrintStackTrace(PipelineException ex){
        //Write to Error Queue
        while(!errorWriteQ.offer(this.input)){};
        //Print the stack trace
        ex.printStackTrace();
    }
}

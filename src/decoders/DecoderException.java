package decoders;

import misc.PipelineException;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/13/13
 * Time: 2:22 PM
 * Exception related to Decoders
 */
public class DecoderException extends PipelineException{
    public DecoderException(String msg, Throwable cause){
        super("An exception occurred in the decoder: " + msg, cause);
    }
    public DecoderException(Throwable cause){
        super(cause);
    }
    public DecoderException(String msg){
        super("An exception occurred in the decoder: " + msg);
    }
}
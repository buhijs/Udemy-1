package encoders;

import misc.PipelineException;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/13/13
 * Time: 2:22 PM
 * Exception related to Encoders
 */
public class EncoderException extends PipelineException{
    public EncoderException(String msg, Throwable cause){
        super("An exception occurred in the encoder: " + msg, cause);
    }
    public EncoderException(Throwable cause){
        super(cause);
    }
    public EncoderException(String msg){
        super("An exception occurred in the encoder: " + msg);
    }
}

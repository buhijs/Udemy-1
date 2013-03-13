package processors;

import misc.PipelineException;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/13/13
 * Time: 2:22 PM
 * Exception related to Processors
 */
public class ProcessorException extends PipelineException {
    public ProcessorException(String msg, Throwable cause){
        super("An exception occurred in the processor: " + msg, cause);
    }
    public ProcessorException(Throwable cause){
        super(cause);
    }
    public ProcessorException(String msg){
        super("An exception occurred in the processor: " + msg);
    }
}


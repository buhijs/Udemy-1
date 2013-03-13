package filters;

import processors.ProcessorException;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/13/13
 * Time: 2:22 PM
 * Exception related to Filters
 */
public class FilterException extends ProcessorException {
    public FilterException(String msg, Throwable cause){
        super("An exception occurred in the filter: " + msg, cause);
    }
    public FilterException(Throwable cause){
        super(cause);
    }
    public FilterException(String msg){
        super("An exception occurred in the filter: " + msg);
    }
}

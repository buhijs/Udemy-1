package misc;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/8/13
 * Time: 1:10 AM
 * Top level exception for data pipeline.
 */
public class PipelineException extends Exception{
    public PipelineException(String msg) {
        super(msg);
    }


    public PipelineException(Throwable cause) {
        super(cause);
    }

    public PipelineException(String msg, java.lang.Throwable cause) {
        super(msg, cause);
    }

}

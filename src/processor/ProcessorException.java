package processor;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/8/13
 * Time: 1:10 AM
 * Exception File.
 */
public class ProcessorException extends Exception{
    public ProcessorException(String line){

        System.out.println("Exception was caused while processing input data:\n" + line);

    }
}

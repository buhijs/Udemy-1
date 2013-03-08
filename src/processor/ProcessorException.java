package processor;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/8/13
 * Time: 1:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProcessorException extends RuntimeException{
    public ProcessorException(String line){

        System.out.println("Exception was caused while processing input data:\n" + line);

    }
}

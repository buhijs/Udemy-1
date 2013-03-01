package processor;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/1/13
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SerialReadProducer {
    public String toString();
    public void inflateFromString();
    public void process();
}

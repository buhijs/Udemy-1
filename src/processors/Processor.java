package processors;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Processor<V> {
    public V process(V objectToProcess);
}

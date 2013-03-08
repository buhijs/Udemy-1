package processors;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:55 PM
 * An interfacet to Processor
 * @param <V> the type of object that needs to be processed
 */
public interface Processor<V> {
    public V process(V objectToProcess);
}

package encoders;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:13 PM
 * Encoder Takes in a input and returns an encoded object as output
 */
public interface Encoder<T,V> {
    public V encode(T objectToEncode);
}

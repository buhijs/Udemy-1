package decoders;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:11 PM
 * Decoder takes in an object and returns an output
 * @param <V> the type of object that needs to be decoded
 * @param <M> the type of object that the output should result in
 */
public interface Decoder<V,M> {
    public M decode(V objectToDecode);
}

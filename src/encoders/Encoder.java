package encoders;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:11 PM
 * Encoder takes in an object to be encoded and returns an output
 * @param <V> the type of object that needs to be encoded
 * @param <M> the type of object that the output should result in
 */
public interface Encoder<V,M> {
    public M encode(V objectToEncode) throws EncoderException ;
}

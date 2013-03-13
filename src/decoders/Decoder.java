package decoders;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:13 PM
 * Decoder Takes in a input and returns an decoded object as output
 * @param <T> the type of object that needs to be decoded
 * @param <V> the type of object that the output should result in
 */
public interface Decoder<T,V> {
    public V decode(T objectToDecode) throws DecoderException ;
}

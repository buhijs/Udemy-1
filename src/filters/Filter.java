package filters;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/10/13
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Filter<V> {
    public boolean isFilterConditionMet(V objectToFilter) throws FilterException;
}

package processors;

import filters.DNBCompanyBranchFilter;
import filters.Filter;
import filters.FilterException;
import misc.Company;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:57 PM
 * Implementation of Interface to Processor.
 *
 */
public class CompanyProcessor implements Processor<Company> {
    /**
     * Returns null if the
     * @param company
     * @return
     * @throws ProcessorException
     */
    public Company process(Company company) throws ProcessorException {
        try {
            //Filter all branches
            Filter filter = new DNBCompanyBranchFilter();

            //If record is filtered return null
            if(!filter.isFilterConditionMet(company)){
                company.clean();
                return company;
            } else {
                return null;
            }
        } catch (FilterException e) {
            throw new ProcessorException(e);
        }
    }
}

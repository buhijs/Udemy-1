package processors;

import filters.DNBCompanyBranchFilter;
import filters.Filter;
import processor.Company;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:57 PM
 * Implementation of Inteface to Processors.
 *
 */
public class CompanyProcessor implements Processor<Company> {
    public Company process(Company company){
        if(company == null){
            return null;
        }

        //Filter all branches
        Filter filter = new DNBCompanyBranchFilter(company);

        //If record is filtered return null
        if(!filter.isFilterConditionMet()){
            company.clean();
            return company;
        } else {
            return null;
        }

    }
}

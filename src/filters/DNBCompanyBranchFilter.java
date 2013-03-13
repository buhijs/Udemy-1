package filters;

import misc.Company;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/10/13
 * Time: 5:33 PM
 * Filters branches from
 */
public class DNBCompanyBranchFilter implements Filter<Company>{
    //Empty Constructor
    public void DNBCompanyBranchFilter(){}

    public boolean isFilterConditionMet(Company company) throws FilterException {
        try{
            if(company.getField1().equals("field2")){
                return true;
            }else {
                return false;
            }
        } catch (NullPointerException ex){
            throw new FilterException("An error occurred in the filter.", ex);
        }
    }
}

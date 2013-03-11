package filters;

import processor.Company;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/10/13
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DNBCompanyBranchFilter implements Filter{
    private Company company;
    public DNBCompanyBranchFilter(Company company){
        this.company = company;
    }

    public boolean isFilterConditionMet(){
        if(company.getField1().equals("field2")){
            return true;
        }else {
            return false;
        }

    }
}

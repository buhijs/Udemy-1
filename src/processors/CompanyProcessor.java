package processors;

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
        company.clean();
        return company;
    }
}

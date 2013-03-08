package processors;

import processor.Company;
import processors.Processor;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyProcessor implements Processor<Company> {
    public Company process(Company company){
        company.clean();
        return company;
    }
}

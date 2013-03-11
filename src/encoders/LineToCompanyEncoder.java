package encoders;

import processor.Company;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class LineToCompanyEncoder implements Encoder<String, Company> {

    public Company encode(String line){
        if(line == null){
            return null;
        }
        //Split the line and process the contents
        String[] components= line.split("\t");

        Company company = new Company();
        company.setField1(components[0]);
        company.setField2(components[1]);
        company.setField3(components[2]);
        company.setField4(components[3]);

        return company;
    }
}

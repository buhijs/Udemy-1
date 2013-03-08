package decoders;

import processor.Company;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyToLineDecoder implements Decoder<Company,String> {
    private final char separator;
    public CompanyToLineDecoder(char separator){
        this.separator = separator;
    }
    public String decode(Company company){
        return company.getField1()+separator+company.getField2()+separator+company.getField3()+separator+company.getField4();
    }
}

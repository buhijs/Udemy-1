package encoders;

import misc.Company;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyToLineEncoder implements Encoder<Company,String> {
    private final char separator;
    public CompanyToLineEncoder(char separator){
        this.separator = separator;
    }
    public String encode(Company company) throws EncoderException{
        String result = null;

        try{
            result = company.getField1()+separator+company.getField2()+separator+company.getField3()+separator+company.getField4();
        }catch(NullPointerException ex){
            throw new EncoderException("Error processing company ",ex);
        }

        return result;
    }
}

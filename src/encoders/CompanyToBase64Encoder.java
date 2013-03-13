package encoders;

import misc.Company;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/8/13
 * Time: 12:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanyToBase64Encoder implements Encoder<Company, String> {
    private final char separator;
    private static final String DEFAULT_ENCODING="UTF-8";
    private static BASE64Encoder enc=new BASE64Encoder();

    public CompanyToBase64Encoder(char separator){
        this.separator = separator;
    }

    public String encode(Company company){
        if(company == null)
            return null;
        else
            return getBase64(company.getField1()+separator+company.getField2()+separator+company.getField3()+separator+company.getField4());
    }

    public String getBase64(String input){
        String result = "";
        try {
            String rez = enc.encode( input.getBytes( DEFAULT_ENCODING ) );
            return rez;
        }
        catch ( UnsupportedEncodingException e ) {
            return null;
        }
    }
}

package decoders;

import misc.Company;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/7/13
 * Time: 11:17 PM
 *
 */
public class LineToCompanyDecoder implements Decoder<String, Company> {
    public Company decode(String line) throws DecoderException {
        try{
            //Split the line and process the contents
            String[] components= line.split("\t");
            Company company = new Company();
            company.setField1(components[0]);
            company.setField2(components[1]);
            company.setField3(components[2]);
            company.setField4(components[3]);
            return company;
        } catch (ArrayIndexOutOfBoundsException ex){
            throw new DecoderException("Error processing line " + line, ex);
        }  catch (NullPointerException ex){
            throw new DecoderException("Error processing line.", ex);
        }
    }
}

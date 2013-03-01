package processor;

/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 3/1/13
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompanySerialReadProducer implements SerialReadProducer {

    private String line;
    private Company company = null;
    private  String separator="\t";

    public CompanySerialReadProducer(String line){
        this.line = line;
    }

    @Override
    public void inflateFromString() {
        //Split the line and process the contents
        String[] components= line.split("\t");
        company = new Company();
        company.setField1(components[0]);
        company.setField2(components[1]);
        company.setField3(components[2]);
        company.setField4(components[3]);
    }

    @Override
    public void process() {
        inflateFromString();
        company.clean();
    }

    @Override
    public String toString() {
        return company.getField1()+separator+company.getField2()+separator+company.getField3()+separator+company.getField4();
    }
}

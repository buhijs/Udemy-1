package processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: yashr
 * Date: 2/7/13
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Company {
    private  String separator="\t";
    private  String field1;
    private  String field2;
    private  String field3;
    private  String field4;
    private static final List<Pattern> REGEX_PATTERNS = new ArrayList<Pattern>(
            Arrays.asList(Pattern.compile("asbd")
//                    ,Pattern.compile("as|f|as|g|we|g|sdf|gas|asdf|agsD|hsdfg|jgdfh|fgh|wrty|dkh|fghS|DFg|fdj|DjgdfgJD|gjdGFH|dfgh||SDFGsdfgsfdgsdfgsdfgsdfgsdfg|sdfgsfdgsdgfsdfg|sfdg|sdfgsdfg|SfgsFD|Gsdfg|sdfg|Sdfg|sdfg")
//                    ,Pattern.compile("as|f|as|g|we|g|sdf|gas|asdf|agsD|hsdfg|jgdfh|fgh|wrty|dkh|fghS|DFg|fdj|DjgdfgJD|gjdGFH|dfgh||SDFGsdfgsfdgsdfgsdfgsdfgsdfg|sdfgsfdgsdgfsdfg|sfdg|sdfgsdfg|SfgsFD|Gsdfg|sdfg|Sdfg|sdfg")
//                    ,Pattern.compile("as|f|as|g|we|g|sdf|gas|asdf|agsD|hsdfg|jgdfh|fgh|wrty|dkh|fghS|DFg|fdj|DjgdfgJD|gjdGFH|dfgh||SDFGsdfgsfdgsdfgsdfgsdfgsdfg|sdfgsfdgsdgfsdfg|sfdg|sdfgsdfg|SfgsFD|Gsdfg|sdfg|Sdfg|sdfg")
//                    ,Pattern.compile("as|f|as|g|we|g|sdf|gas|asdf|agsD|hsdfg|jgdfh|fgh|wrty|dkh|fghS|DFg|fdj|DjgdfgJD|gjdGFH|dfgh||SDFGsdfgsfdgsdfgsdfgsdfgsdfg|sdfgsfdgsdgfsdfg|sfdg|sdfgsdfg|SfgsFD|Gsdfg|sdfg|Sdfg|sdfg")
//                    ,Pattern.compile("as|f|as|g|we|g|sdf|gas|asdf|agsD|hsdfg|jgdfh|fgh|wrty|dkh|fghS|DFg|fdj|DjgdfgJD|gjdGFH|dfgh||SDFGsdfgsfdgsdfgsdfgsdfgsdfg|sdfgsfdgsdgfsdfg|sfdg|sdfgsdfg|SfgsFD|Gsdfg|sdfg|Sdfg|sdfg")
//                    ,Pattern.compile("as|f|as|g|we|g|sdf|gas|asdf|agsD|hsdfg|jgdfh|fgh|wrty|dkh|fghS|DFg|fdj|DjgdfgJD|gjdGFH|dfgh||SDFGsdfgsfdgsdfgsdfgsdfgsdfg|sdfgsfdgsdgfsdfg|sfdg|sdfgsdfg|SfgsFD|Gsdfg|sdfg|Sdfg|sdfg")
//                    ,Pattern.compile("as|f|as|g|we|g|sdf|gas|asdf|agsD|hsdfg|jgdfh|fgh|wrty|dkh|fghS|DFg|fdj|DjgdfgJD|gjdGFH|dfgh||SDFGsdfgsfdgsdfgsdfgsdfgsdfg|sdfgsfdgsdgfsdfg|sfdg|sdfgsdfg|SfgsFD|Gsdfg|sdfg|Sdfg|sdfg")
//                    ,Pattern.compile("as|f|as|g|we|g|sdf|gas|asdf|agsD|hsdfg|jgdfh|fgh|wrty|dkh|fghS|DFg|fdj|DjgdfgJD|gjdGFH|dfgh||SDFGsdfgsfdgsdfgsdfgsdfgsdfg|sdfgsfdgsdgfsdfg|sfdg|sdfgsdfg|SfgsFD|Gsdfg|sdfg|Sdfg|sdfg")
//
           ));

    public  String getField1() {
        return field1;
    }

    public  void setField1(String field1) {
        this.field1 = field1;
    }

    public  String getField2() {
        return field2;
    }

    public  void setField2(String field2) {
        this.field2 = field2;
    }

    public  String getField3() {
        return field3;
    }

    public  void setField3(String field3) {
        this.field3 = field3;
    }

    public  String getField4() {
        return field4;
    }

    public  void setField4(String field4) {
        this.field4 = field4;
    }

    public  void clean(){
        field1 =  apply(field1);
        field2 =  apply(field2);
        field3 =  apply(field3);
        field4 =  apply(field4);
    }

    public  String apply(String company){

        for(Pattern pattern : REGEX_PATTERNS){
            company = pattern.matcher(company).replaceFirst("ABCDEFGHIJKLM");
        }

        return company;
    }

    public String toString(Character separator) {
         return field1+separator+field2+separator+field3+separator+field4;
    }

    public void inflate(String line){
        //Split the line and process the contents
        String[] components= line.split("\t");
        this.setField1(components[0]);
        this.setField2(components[1]);
        this.setField3(components[2]);
        this.setField4(components[3]);

    }
}

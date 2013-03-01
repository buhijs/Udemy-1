package misc;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: yasha
 * Date: 2/28/13
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class EOLStockCleaner {
    private static String sourceFile = "/Users/yasha/sink/eol_stock_ticker_201302.tsv";

    public static void main (String args[]){
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(sourceFile),'\t');
            String[] nextLine;
            while((nextLine = csvReader.readNext()) != null){
                processLine(nextLine);
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void processLine(String[] line){
        String stockTicker = line[1].trim().toUpperCase();
        String stockTickerCleaned = stockTicker.replace("'", ".").replace("-", ".");
        String stockExchange = line[2];

        if (stockTickerCleaned.isEmpty()) {
            if (!stockTicker.isEmpty()) {
                stockTickerCleaned = stockTicker;
            } else {
                stockExchange = "";
            }
        }

        if ("Nasdaq Capital Marke".equals(stockExchange)) {
            stockExchange = "NASDAQ (CM)";
        } else if ("Nasdaq Global Market".equals(stockExchange)) {
            stockExchange = "NASDAQ (GM)";
        } else if ("NYSE Amex".equals(stockExchange)) {
            stockExchange = "NYSE Alternext";
        } else if ("OTCBB".equals(stockExchange)) {
            stockExchange = "OTC";
        }

        if (stockExchange.isEmpty() && !stockTickerCleaned.isEmpty()) {
            stockExchange = "OTHER";
        }

        System.out.println(line[0]+"\t"+stockTickerCleaned+"\t"+stockExchange+"\t"+line[3]);

    }
}

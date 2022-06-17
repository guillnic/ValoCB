package Reader;

import Client.Client;
import Forex.Forex;
import Portfolio.Portfolio;
import ValoCB.ValoCB;
import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static ValoCB.ValoCB.stringToFloat;

public class ReadForex {
    private ReadForex() {
        throw new AssertionError();
    }

    public static void readForex(ValoCB valoCB, String fileName) {
        /**
         * @param fileName : the name of csv file which contains the currency exchange values
         * This function sets the forex attribute of the class
         * **/

        HashMap<String, Portfolio> portfolios = valoCB.getPortfolios();
        HashMap<String, Client> clients = valoCB.getClients();
        Forex forex = valoCB.getForex();

        try (var fr = new FileReader(fileName, StandardCharsets.UTF_8);
             var reader = new CSVReader(fr)) {
            String[] nextLine;
            boolean header = true;

            while ((nextLine = reader.readNext()) != null) {
                if (header){
                    if (nextLine[2].equals("Value")){
                        header = false;
                    }
                }else{
                    if (nextLine[0].equals("EUR")){
                        forex.addCurrencyExchange(nextLine[1], 1/stringToFloat(nextLine[2]));
                    } else {
                        if (stringToFloat(nextLine[2]) == 0){
                            throw new ArithmeticException();
                        } else {
                            forex.addCurrencyExchange(nextLine[0], stringToFloat(nextLine[2]));
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

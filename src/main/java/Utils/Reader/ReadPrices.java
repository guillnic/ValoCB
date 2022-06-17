package Utils.Reader;

import Client.Client;
import Forex.Forex;
import Portfolio.Portfolio;
import Product.Underlying;
import ValoCB.ValoCB;
import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static ValoCB.ValoCB.stringToFloat;

public class ReadPrices {

    public static void readPrices(ValoCB valoCB, String fileName) {
        /**
         * @param valoCB : the instance of ValoCB
         * @param fileName : the name of csv file which contains the products, their underlyings
         *                 and the portfolio they are associated with
         * This function sets the attributes "portfolios" and "products" of the class
         * **/
        HashMap<String, Portfolio> portfolios = valoCB.getPortfolios();
        HashMap<String, Client> clients = valoCB.getClients();
        Forex forex = valoCB.getForex();
        try {
            var fr = new FileReader(fileName, StandardCharsets.UTF_8);
            var reader = new CSVReader(fr);
            String[] nextLine;
            boolean header = true;

            while ((nextLine = reader.readNext()) != null) {
                //Pass the header of the file
                if (header){
                    if (nextLine[0].equals("Portfolio")){
                        header = false;
                    }
                } else {
                    Portfolio portfolio = portfolios.get(nextLine[0]);
                    // Check whether the portfolio already exists
                    if (portfolio != null){
                        portfolio.addProduct(nextLine[1], new Underlying(nextLine[1], nextLine[2], nextLine[3], stringToFloat(nextLine[4])));
                    } else {
                        portfolios.put(nextLine[0], new Portfolio(nextLine[0]));
                        portfolio = portfolios.get(nextLine[0]);
                        // Add the new underlying to the portfolio
                        portfolio.addProduct(nextLine[1], new Underlying(nextLine[1], nextLine[2], nextLine[3], stringToFloat(nextLine[4])));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

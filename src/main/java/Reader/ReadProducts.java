package Reader;

import Client.Client;
import Forex.Forex;
import Portfolio.Portfolio;
import Product.Product;
import ValoCB.ValoCB;
import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import static ValoCB.ValoCB.stringToFloat;

public class ReadProducts {
    private ReadProducts() {
        throw new AssertionError();
    }

    public static void readProduct(ValoCB valoCB, String fileName) {
        /**
         * @param fileName : the name of csv file which contains the products, their clients and the quantity they own
         * This function sets the attribute "clients" and expands "portfolios" of the class
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
                if (header) {
                    // We are in the header part of the file
                    if (nextLine[0].equals("Product")) {
                        header = false;
                    }
                } else {
                    // We are in the data part of the file
                    Client client = clients.get(nextLine[1]);
                    // Does the client exist
                    if (client != null){
                        client.addProduct(nextLine[0], Integer.parseInt(nextLine[2]));
                    } else {
                        Client newClient = new Client(nextLine[1]);
                        newClient.addProduct(nextLine[0], Integer.parseInt(nextLine[2]));
                        clients.put(nextLine[1], newClient);
                    }
                    for (var entry : portfolios.entrySet()) {
                        // Check in each portfolio whether the product exists
                        Portfolio portfolio = entry.getValue();
                        HashMap<String, ArrayList<Product>> products = portfolio.getProducts();
                        ArrayList<Product> product = products.get(nextLine[0]);
                        // Check whether the product exists in the portfolio
                        if (product != null){
                            portfolio.addNumProduct(nextLine[0], stringToFloat(nextLine[2]));
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

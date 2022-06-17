import Client.Client;
import Forex.Forex;
import Portfolio.Portfolio;
import Product.Product;
import Product.Underlying;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class ValoCB{
    private final HashMap<String, Portfolio> portfolios;
    private final HashMap<String, Client> clients;
    private final Forex forex;

    public ValoCB(String forexFile, String pricesFile, String productsFile) {
        /**
         * @param forexFile : The name of the file that contains the currency exchange rates
         * @param pricesFile : The name of the file that contains the information on the portfolios and their products
         * @param productsFile : The name of the file that contains the information of the clients and the products they own
         * The generator of the class, it will read the files and fill all attribute
         **/
        portfolios = new HashMap<>();
        clients = new HashMap<>();
//        products = new HashMap<>();
        forex = new Forex();

        readForex(forexFile);
        readPrices(pricesFile);
        readProduct(productsFile);
    }

    public Forex getForex() {
        return forex;
    }

    public HashMap<String, Portfolio> getPortfolios() {
        return portfolios;
    }

    public HashMap<String, Client> getClients() {
        return clients;
    }

    public float stringToFloat(String stringNumber){
        /**
         * @param A float in string format
         * @return the value of the float
         * **/
        Number number = 0;
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        try {
            number = format.parse(stringNumber);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number.floatValue();
    }

    public void readForex(String fileName) {
        /**
         * @param fileName : the name of csv file which contains the currency exchange values
         * This function sets the forex attribute of the class
         * **/
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
                        this.forex.addCurrencyExchange(nextLine[1], 1/stringToFloat(nextLine[2]));
                    } else {
                        if (stringToFloat(nextLine[2]) == 0){
                            throw new ArithmeticException();
                        } else {
                            this.forex.addCurrencyExchange(nextLine[0], stringToFloat(nextLine[2]));
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readPrices(String fileName) {
        /**
         * @param fileName : the name of csv file which contains the products, their underlyings
         *                 and the portfolio they are associated with
         * This function sets the attributes "portfolios" and "products" of the class
         * **/
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

//                    ArrayList<Product.Product> product = products.get(nextLine[1]);
//                    // Check whether the product exists
//                    if (product != null){
//                        product.add(new Product.Underlying(nextLine[1], nextLine[2], nextLine[3], stringToFloat(nextLine[4])));
//                    } else {
//                        ArrayList<Product.Product> underlyings = new ArrayList<>();
//                        underlyings.add(new Product.Underlying(nextLine[1], nextLine[2], nextLine[3], stringToFloat(nextLine[4])));
//                        products.put(nextLine[1], underlyings);
//                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readProduct(String fileName) {
        /**
         * @param fileName : the name of csv file which contains the products, their clients and the quantity they own
         * This function sets the attribute "clients" and expands "portfolios" of the class
         * **/
        try (var fr = new FileReader(fileName, StandardCharsets.UTF_8);
             var reader = new CSVReader(fr)) {
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
                    for (Map.Entry<String, Portfolio> entry : portfolios.entrySet()) {
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

    public void printPortfolios(){
        /**
         * This function prints the attribute "portfolios" of the class
         * **/
        for (Map.Entry<String, Portfolio> entry : portfolios.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void printClients(){
        /**
         * This function prints the attribute "clients" of the class
         * **/
        for (Map.Entry<String, Client> entry : clients.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void writePortfolios(String fileName, HashMap<String, Float> portfolioPrice) throws IOException {
        /**
         * @param fileName : The name of the output file
         * @param portfolioPrice : The data that maps the portfolios with their price
         * @throws IOException
         * This function writes the results of the processing of the value of the portfolio in a csv file
         * **/
        CSVWriter writer = new CSVWriter(new FileWriter(fileName, false), ',', CSVWriter.NO_QUOTE_CHARACTER);
        String header = "PTF,price";
        writer.writeNext(header);

        for (Map.Entry<String, Float> entry : portfolioPrice.entrySet()) {
            String[] nextLine = (entry.getKey() + "," + entry.getValue()).split(",");
            writer.writeNext(nextLine);
        }
        writer.close();
    }

    public void writeClient(String fileName, HashMap<String, Float> clientsCapital) throws IOException {
        /**
         * @param fileName : The name of the output file
         * @param clientsCapital : The data that maps the clients with their capital
         * @throws IOException
         * This function writes the results of the processing of the capital of the clients in a csv file
         * **/
        CSVWriter writer = new CSVWriter(new FileWriter(fileName, false), ',', CSVWriter.NO_QUOTE_CHARACTER);
        String header = "Client.Client,capital";
        writer.writeNext(header);

        for (Map.Entry<String, Float> entry : clientsCapital.entrySet()) {
            String[] nextLine = (entry.getKey() + "," + entry.getValue()).split(",");
            writer.writeNext(nextLine);
        }
        writer.close();
    }

    public HashMap<String, Float> productsPrices(){
        /**
         * @return A data structure that maps the name of the products with their price
         * **/
        HashMap<String, Float> prices = new HashMap<>();
        for(var p: portfolios.entrySet()){
            for (Map.Entry<String, ArrayList<Product>> entry : p.getValue().getProducts().entrySet()) {
                float price = 0;
                for (Product pro : entry.getValue()){
                    price += ((Underlying) pro).price(forex);
                }
                prices.put(entry.getKey(), price);
            }
        }
        return prices;
    }

    public HashMap<String, Float> portfoliosPrice(Forex forex){
        /**
         * @param forex : The currency exchange values
         * @return A data structure that maps the portfolios with their price
         * **/
        HashMap<String, Float> prices = new HashMap<>();
        for (Map.Entry<String, Portfolio> entry : portfolios.entrySet()) {
            prices.put(entry.getKey(), entry.getValue().price(forex));
        }
        return prices;
    }

    public HashMap<String, Float> clientsCapital(HashMap<String, Float> productsPrices){
        /**
         * @param productsPrice : The price of each product
         * @return A data structure that maps the name of the clients with their capital
         * **/
        HashMap<String, Float> capital = new HashMap<>();
        for (Map.Entry<String, Client> entry : clients.entrySet()) {
            capital.put(entry.getKey(), entry.getValue().capital(productsPrices));
        }
        return capital;
    }

    public static void main(String[] args) throws IOException {
        ValoCB valoCB = new ValoCB(args[0], args[1], args[2]);

//        valoCB.printPortfolios();
//        valoCB.printClients();

        HashMap<String, Float> portfolioPrice = valoCB.portfoliosPrice(valoCB.forex);
        valoCB.writePortfolios("outputs/Reporting-portfolio.csv", portfolioPrice);
        HashMap<String, Float> clientsCapital = valoCB.clientsCapital(valoCB.productsPrices());
        valoCB.writeClient("outputs/Reporting-client.csv", clientsCapital);
    }
}

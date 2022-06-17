package ValoCB;

import Client.Client;
import Forex.Forex;
import Print.PrintClients;
import Print.PrintPorfolios;
import Portfolio.Portfolio;
import Product.Product;
import Product.Underlying;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import static Reader.ReadForex.readForex;
import static Reader.ReadPrices.readPrices;
import static Reader.ReadProducts.readProduct;
import static Writer.WriteClients.writeClient;
import static Writer.WritePortfolios.writePortfolios;

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

        readForex(this, forexFile);
        readPrices(this, pricesFile);
        readProduct(this, productsFile);
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

    public static float stringToFloat(String stringNumber){
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

    public HashMap<String, Float> productsPrices(){
        /**
         * @return A data structure that maps the name of the products with their price
         * **/
        HashMap<String, Float> prices = new HashMap<>();
        for(var p: portfolios.entrySet()){
            for (var entry : p.getValue().getProducts().entrySet()) {
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
        for (var entry : portfolios.entrySet()) {
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
        for (var entry : clients.entrySet()) {
            capital.put(entry.getKey(), entry.getValue().capital(productsPrices));
        }
        return capital;
    }

    public static void main(String[] args) throws IOException {
        ValoCB valoCB = new ValoCB(args[0], args[1], args[2]);

//        PrintPorfolios.printPortfolios(valoCB.getPortfolios());
//        PrintClients.printClients(valoCB.getClients());

        HashMap<String, Float> portfolioPrice = valoCB.portfoliosPrice(valoCB.forex);
        writePortfolios("outputs/Reporting-portfolio.csv", portfolioPrice);
        HashMap<String, Float> clientsCapital = valoCB.clientsCapital(valoCB.productsPrices());
        writeClient("outputs/Reporting-client.csv", clientsCapital);
    }
}

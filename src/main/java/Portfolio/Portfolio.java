package Portfolio;

import Forex.Forex;
import Product.Product;
import Product.Underlying;

import java.util.*;

public class Portfolio {
    private String name;
    // An attribute that maps the name of the products to their underlyings inside the portfolio
    private final HashMap<String, ArrayList<Product>> products;
    private final HashMap<String, Float> numProducts;
    private float price;

    public Portfolio(String name) {
        this.name = name;
        this.products = new HashMap<>();
        this.numProducts = new HashMap<>();
    }

    public HashMap<String, ArrayList<Product>> getProducts() {
        return products;
    }

    public HashMap<String, Float> getNumProducts() {
        return numProducts;
    }

    public String getName() {
        return name;
    }

    public void addProduct(String name, Product product){
        /**
         * @param name :  the name of the product to add
         * @param product : the product to add
         * This function adds the product to the attribute HashMap products
         * **/
        // Check whether the product already exists
        ArrayList<Product> underlyings = this.products.get(name);
        if (underlyings != null){
            underlyings.add(product);
        } else {
            underlyings = new ArrayList<>();
            underlyings.add(product);
            this.products.put(name, underlyings);
        }
    }

    public void addNumProduct(String name, float number){
        /**
         * @param name :  the name of the product to add
         * @param number : The quantity of the product added
         * This function adds the quantity of the product present in the portfolio
         * **/
        Float num = this.numProducts.get(name);
        if (num != null){
            num += number;
            numProducts.put(name, num);
        } else {
            numProducts.put(name, number);
        }
    }

    public float price(Forex forex){
        /**
         * @param forex : the currency exchange rates
         * @return the price of the portfolio
         * **/
        float price = 0;
        for (Map.Entry<String, ArrayList<Product>> entry : products.entrySet()) {
            float subPrice = 0;
            for (Product p : entry.getValue()){
                subPrice += ((Underlying) p).price(forex);
            }
            price += subPrice*this.numProducts.get(entry.getKey());
        }
        return price;
    }

    @Override
    public String toString() {
        StringBuffer portfolioString = new StringBuffer();
        portfolioString.append("Portfolio.Portfolio : " + name + "\n");
        for (Map.Entry<String, ArrayList<Product>> entry : products.entrySet()) {
            portfolioString.append(entry.getKey() + "\n" + entry.getValue() + "\n");
        }
        return portfolioString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Portfolio){
            Portfolio portfolio = (Portfolio) o;
            return this.name.equals(portfolio.getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

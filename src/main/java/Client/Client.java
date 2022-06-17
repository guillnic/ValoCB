package Client;

import java.util.*;

public class Client {
    private String name;
    private HashMap<String, Integer> products; ;

    public Client(String name) {
        this.name = name;
        this.products = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void addProduct(String product, int quantity){
        /**
         * @param product : the name of the product to add
         * @param product : the quantity owned by the client
         * This function adds the quantity of product owned by the client to its products.
         *                If the client already has the product, the new value is discarded
         * **/
        if (products.get(product) == null){
            products.put(product, quantity);
        }
    }

    public float capital(HashMap<String, Float> productsPrices){
        /**
         * @param productsPrices : the prices of all the products
         * @return the capital of the client
         * **/
        float capital = 0;
        for (Map.Entry<String, Integer> entry : products.entrySet()){
            Float price = productsPrices.get(entry.getKey());
            // Check whether the product is referenced with its price
            if (price != null){
                capital += price * entry.getValue();
            } else {
                throw new NoSuchElementException();
            }
        }
        return capital;
    }

    @Override
    public String toString() {
        StringBuffer clientString = new StringBuffer();
        clientString.append("Client.Client's name = " + name + "\n");
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            clientString.append("Product.Product : " + entry.getKey() + ", Quantity : " + entry.getValue() + "\n");
        }
        return clientString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Client){
               return this.name.equals(((Client) o).getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

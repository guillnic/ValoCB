import java.util.Objects;

public class Underlying extends Product{
    private String underlying;
    private String currency;
    private float price;

    public Underlying(String name, String underlying, String currency, float price) {
        super(name);
        this.underlying = underlying;
        this.currency = currency;
        this.price = price;
    }

    public String getUnderlying() {
        return underlying;
    }

    public String getCurrency() {
        return currency;
    }

    public float getPrice() {
        return price;
    }

    public float price(Forex forex){
        /**
         * @param forex : the exchange rate of currencies with euro
         * @return The price of the underlying of the product in euros
         * **/
        if (this.currency.equals("EUR")){
            return this.price;
        } else {
            return this.price * forex.getExchange(this.currency);
        }
    }

    @Override
    public String toString() {

        return  super.toString() + " " +
                "underlying='" + underlying +
                ", currency='" + currency +
                ", price=" + price + "\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), underlying);
    }
}

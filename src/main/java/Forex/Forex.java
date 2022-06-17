import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Forex {
    private HashMap<String, Float> eur;

    public Forex(){
        eur = new HashMap<>();
    }

    public HashMap<String, Float> getEur() {
        return eur;
    }

    public void addCurrencyExchange(String cur, float exchange){
        /**
         * @param cur :  the name of the currency to add
         * @param exchange : the exchange rate with euros
         * This function adds the currency to the forex
         * **/
        eur.put(cur, exchange);
    }

    public float getExchange(String cur) throws NoSuchElementException{
        /**
         * @param cur : the name of the currency for which to retrieve the exchange rate
         * @return the exchange rate of the cur currency with euros
         * **/
        if (eur.get(cur) != null){
            return eur.get(cur);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        StringBuffer forexString = new StringBuffer();
        forexString.append("Euro exchange rates :\n");
        for (Map.Entry<String, Float> entry : eur.entrySet()) {
            forexString.append("Currency : " + entry.getKey() + ", Exchange : " + entry.getValue() + "\n");
        }
        return forexString.toString();
    }
}

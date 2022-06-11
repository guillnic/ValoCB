import org.junit.Test;
import org.junit.Assert;

public class TestForex {
    /*
    * This function tests the results obtained in the read of the forex file
    * It tests :
    *   Whether there is an EUR element in the map
    *   Whether the values created in map are null
    *   If not, whether the values are the right ones
    */
    @Test
    public void testForex(){
        ValoCB  valoCB = new ValoCB("/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Forex.csv", "/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Prices.csv", "/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Product.csv");
        Assert.assertNull(valoCB.getForex().getEur().get("EUR"));

        Float jpy = valoCB.getForex().getEur().get("JPY");
        Float chf = valoCB.getForex().getEur().get("CHF");
        Float gpb = valoCB.getForex().getEur().get("GPB");
        Float tnd = valoCB.getForex().getEur().get("TND");
        Float usd = valoCB.getForex().getEur().get("USD");

        Assert.assertNotNull(jpy);
        Assert.assertNotNull(chf);
        Assert.assertNotNull(gpb);
        Assert.assertNotNull(tnd);
        Assert.assertNotNull(usd);

        Assert.assertEquals(jpy, 2.0, 0.00000001);
        Assert.assertEquals(chf, 4.0, 0.00000001);
        Assert.assertEquals(usd, 0.5, 0.00000001);
        Assert.assertEquals(gpb, 8.0, 0.00000001);
        Assert.assertEquals(tnd, 0.1, 0.00000001);
    }
}

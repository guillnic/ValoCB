import ValoCB.ValoCB;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class TestProduct {
    @Test
    public void testProduct(){
        ValoCB  valoCB = new ValoCB("/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Forex.csv", "/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Prices.csv", "/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Product.csv");
        HashMap<String, Float> productsPrice = valoCB.productsPrices();

        Float p1 = productsPrice.get("P1");
        Assert.assertNotNull(p1);
        Assert.assertEquals(p1, 35, 0.0000001);

        Float p2 = productsPrice.get("P2");
        Assert.assertNotNull(p2);
        Assert.assertEquals(p2, 0.5, 0.0000001);

        Float p3 = productsPrice.get("P3");
        Assert.assertNotNull(p3);
        Assert.assertEquals(p3, 440, 0.0000001);

        Float x1 = productsPrice.get("X1");
        Assert.assertNotNull(x1);
        Assert.assertEquals(x1, 30, 0.0000001);

        Float x2 = productsPrice.get("X2");
        Assert.assertNotNull(x2);
        Assert.assertEquals(x2, 960, 0.0000001);
    }
}

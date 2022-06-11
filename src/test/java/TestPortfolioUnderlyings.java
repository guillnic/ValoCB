import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestPortfolioUnderlyings {
    @Test
    public void testPortfolio(){
        ValoCB  valoCB = new ValoCB("/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Forex.csv", "/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Prices.csv", "/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Product.csv");

        Portfolio ptf1 = valoCB.getPortfolios().get("PTF1");

        Assert.assertNotNull(ptf1);

        HashMap<String, ArrayList<Product>> ptf1Products = ptf1.getProducts();
        HashMap<String, Float> ptf1NumProducts = ptf1.getNumProducts();

        ArrayList<Product> ptf1P1 = ptf1Products.get("P1");
        Float ptf1numP1 = ptf1NumProducts.get("P1");

        Assert.assertNotNull(ptf1P1);
        for (Product p : ptf1P1){
            Assert.assertTrue(p instanceof Underlying);
            Underlying underlying = (Underlying) p;
            if (underlying.getUnderlying().equals("U11")){
                Assert.assertEquals(underlying.getCurrency(), "EUR");
                Assert.assertEquals(underlying.getPrice(), 10, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 10, 0.0000001);
            }
            if (underlying.getUnderlying().equals("U12")){
                Assert.assertEquals(underlying.getCurrency(), "USD");
                Assert.assertEquals(underlying.getPrice(), 20, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 10, 0.0000001);
            }
            if (underlying.getUnderlying().equals("U13")){
                Assert.assertEquals(underlying.getCurrency(), "USD");
                Assert.assertEquals(underlying.getPrice(), 30, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 15, 0.0000001);
            }
        }
        Assert.assertNotNull(ptf1numP1);
        Assert.assertEquals(ptf1numP1, 90, 0.0000001);

        ArrayList<Product> ptf1P2 = ptf1Products.get("P2");
        Float ptf1numP2 = ptf1NumProducts.get("P2");

        Assert.assertNotNull(ptf1P2);
        for (Product p : ptf1P2){
            Assert.assertTrue(p instanceof Underlying);
            Underlying underlying = (Underlying) p;
            if (underlying.getUnderlying().equals("U21")){
                Assert.assertEquals(underlying.getCurrency(), "TND");
                Assert.assertEquals(underlying.getPrice(), 5, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 0.5, 0.0000001);
            }
        }
        Assert.assertNotNull(ptf1numP2);
        Assert.assertEquals(ptf1numP2, 100, 0.0000001);

        ArrayList<Product> ptf1P3 = ptf1Products.get("P3");
        Float ptf1numP3 = ptf1NumProducts.get("P3");

        Assert.assertNotNull(ptf1P3);
        for (Product p : ptf1P3){
            Assert.assertTrue(p instanceof Underlying);
            Underlying underlying = (Underlying) p;
            if (underlying.getUnderlying().equals("U31")){
                Assert.assertEquals(underlying.getCurrency(), "JPY");
                Assert.assertEquals(underlying.getPrice(), 50, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 100, 0.0000001);
            }
            if (underlying.getUnderlying().equals("U32")){
                Assert.assertEquals(underlying.getCurrency(), "JPY");
                Assert.assertEquals(underlying.getPrice(), 60, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 120, 0.0000001);
            }
            if (underlying.getUnderlying().equals("U33")){
                Assert.assertEquals(underlying.getCurrency(), "JPY");
                Assert.assertEquals(underlying.getPrice(), 20, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 40, 0.0000001);
            }
            if (underlying.getUnderlying().equals("U34")){
                Assert.assertEquals(underlying.getCurrency(), "JPY");
                Assert.assertEquals(underlying.getPrice(), 10, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 20, 0.0000001);
            }
            if (underlying.getUnderlying().equals("U35")){
                Assert.assertEquals(underlying.getCurrency(), "JPY");
                Assert.assertEquals(underlying.getPrice(), 40, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 80, 0.0000001);
            }
            if (underlying.getUnderlying().equals("U36")){
                Assert.assertEquals(underlying.getCurrency(), "CHF");
                Assert.assertEquals(underlying.getPrice(), 20, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 80, 0.0000001);
            }
        }
        Assert.assertNotNull(ptf1numP3);
        Assert.assertEquals(ptf1numP3, 310, 0.0000001);

        ArrayList<Product> ptf1X1 = ptf1Products.get("X1");
        Float ptf1numX1 = ptf1NumProducts.get("X1");

        Assert.assertNull(ptf1X1);
        Assert.assertNull(ptf1numX1);

        ArrayList<Product> ptf1X2 = ptf1Products.get("X2");
        Float ptf1numX2 = ptf1NumProducts.get("X2");

        Assert.assertNull(ptf1X2);
        Assert.assertNull(ptf1numX2);

        Portfolio ptf2 = valoCB.getPortfolios().get("PTF2");
        Assert.assertNotNull(ptf2);

        HashMap<String, ArrayList<Product>> ptf2Products = ptf2.getProducts();
        HashMap<String, Float> ptf2NumProducts = ptf2.getNumProducts();

        ArrayList<Product> ptf2P1 = ptf2Products.get("P1");
        Float ptf2numP1 = ptf2NumProducts.get("P1");

        Assert.assertNull(ptf2P1);
        Assert.assertNull(ptf2numP1);

        ArrayList<Product> ptf2P2 = ptf2Products.get("P2");
        Float ptf2numP2 = ptf2NumProducts.get("P2");

        Assert.assertNull(ptf2P2);
        Assert.assertNull(ptf2numP2);

        ArrayList<Product> ptf2P3 = ptf2Products.get("P3");
        Float ptf2numP3 = ptf2NumProducts.get("P3");

        Assert.assertNull(ptf2P3);
        Assert.assertNull(ptf2numP3);

        ArrayList<Product> ptf2X1 = ptf2Products.get("X1");
        Float ptf2numX1 = ptf2NumProducts.get("X1");

        Assert.assertNotNull(ptf2X1);
        for (Product p : ptf2X1){
            Assert.assertTrue(p instanceof Underlying);
            Underlying underlying = (Underlying) p;
            if (underlying.getUnderlying().equals("UX1")){
                Assert.assertEquals(underlying.getCurrency(), "EUR");
                Assert.assertEquals(underlying.getPrice(), 10, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 10, 0.0000001);
            }
            if (underlying.getUnderlying().equals("UX2")){
                Assert.assertEquals(underlying.getCurrency(), "EUR");
                Assert.assertEquals(underlying.getPrice(), 20, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 20, 0.0000001);
            }
        }
        Assert.assertNotNull(ptf2numX1);
        Assert.assertEquals(ptf2numX1, 70, 0.0000001);

        ArrayList<Product> ptf2X2 = ptf2Products.get("X2");
        Float ptf2numX2 = ptf2NumProducts.get("X2");

        Assert.assertNotNull(ptf2X2);
        for (Product p : ptf2X2){
            Assert.assertTrue(p instanceof Underlying);
            Underlying underlying = (Underlying) p;
            if (underlying.getUnderlying().equals("UX21")){
                Assert.assertEquals(underlying.getCurrency(), "GPB");
                Assert.assertEquals(underlying.getPrice(), 20, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 160, 0.0000001);
            }
            if (underlying.getUnderlying().equals("UX22")){
                Assert.assertEquals(underlying.getCurrency(), "GPB");
                Assert.assertEquals(underlying.getPrice(), 40, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 320, 0.0000001);
            }
            if (underlying.getUnderlying().equals("UX23")){
                Assert.assertEquals(underlying.getCurrency(), "GPB");
                Assert.assertEquals(underlying.getPrice(), 60, 0.0000001);
                Assert.assertEquals(underlying.price(valoCB.getForex()), 480, 0.0000001);
            }
        }
        Assert.assertNotNull(ptf2numX2);
        Assert.assertEquals(ptf2numX2, 160, 0.0000001);
    }
}

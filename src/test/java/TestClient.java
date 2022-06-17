import Client.Client;
import ValoCB.ValoCB;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class TestClient {
    /*
    * This test checks if the clients are created
    * It checks if their products are created and associated with the clients
    * It checks whether the capital of the clients is right
    */
    @Test
    public void testClient(){
        ValoCB valoCB = new ValoCB("/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Forex.csv", "/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Prices.csv", "/Users/guillermain/Desktop/Kata - Code Busters/ValoCB/src/main/resources/Product.csv");
        HashMap<String, Client> client = valoCB.getClients();

        Client c1 = client.get("C1");
        Client c2 = client.get("C2");
        Client c3 = client.get("C3");
        Client c4 = client.get("C4");
        Client c5 = client.get("C5");
        Client c6 = client.get("C6");
        Client c7 = client.get("C7");
        Client c8 = client.get("C8");

        Assert.assertNotNull(c1);
        Assert.assertEquals(c1.capital(valoCB.productsPrices()), 990.0, 0.0000001);
        Assert.assertNotNull(c2);
        Assert.assertEquals(c2.capital(valoCB.productsPrices()), 64600.0, 0.0000001);
        Assert.assertNotNull(c3);
        Assert.assertEquals(c3.capital(valoCB.productsPrices()), 36250.0, 0.0000001);
        Assert.assertNotNull(c4);
        Assert.assertEquals(c4.capital(valoCB.productsPrices()), 350.0, 0.0000001);
        Assert.assertNotNull(c5);
        Assert.assertEquals(c5.capital(valoCB.productsPrices()), 87610.0, 0.0000001);
        Assert.assertNotNull(c6);
        Assert.assertEquals(c6.capital(valoCB.productsPrices()), 13500.0, 0.0000001);
        Assert.assertNotNull(c7);
        Assert.assertEquals(c7.capital(valoCB.productsPrices()), 34400.0, 0.0000001);
        Assert.assertNotNull(c8);
        Assert.assertEquals(c8.capital(valoCB.productsPrices()), 57600.0, 0.0000001);

        Integer c1p1 = c1.getProducts().get("P1");
        Integer c1p2 = c1.getProducts().get("P2");
        Integer c1x1 = c1.getProducts().get("X1");

        Assert.assertNotNull(c1p1);
        Assert.assertEquals(c1p1, 10, 0);
        Assert.assertNotNull(c1p2);
        Assert.assertEquals(c1p2, 80, 0);
        Assert.assertNotNull(c1x1);
        Assert.assertEquals(c1x1, 20, 0);

        Integer c1p3 = c1.getProducts().get("P3");
        Integer c1x2 = c1.getProducts().get("X2");

        Assert.assertNull(c1p3);
        Assert.assertNull(c1x2);

        Integer c2p1 = c2.getProducts().get("P1");
        Integer c2p3 = c2.getProducts().get("P3");
        Integer c2x2 = c2.getProducts().get("X2");

        Assert.assertNotNull(c2p1);
        Assert.assertEquals(c2p1, 40, 0);
        Assert.assertNotNull(c2p3);
        Assert.assertEquals(c2p3, 100, 0);
        Assert.assertNotNull(c2x2);
        Assert.assertEquals(c2x2, 20, 0);

        Integer c2p2 = c2.getProducts().get("P2");
        Integer c2x1 = c2.getProducts().get("X1");

        Assert.assertNull(c2p2);
        Assert.assertNull(c2x1);

        Integer c3p1 = c3.getProducts().get("P1");
        Integer c3p3 = c3.getProducts().get("P3");

        Assert.assertNotNull(c3p1);
        Assert.assertEquals(c3p1, 30, 0);
        Assert.assertNotNull(c3p3);
        Assert.assertEquals(c3p3, 80, 0);

        Integer c3p2 = c3.getProducts().get("P2");
        Integer c3x1 = c3.getProducts().get("x1");
        Integer c3x2 = c3.getProducts().get("x2");

        Assert.assertNull(c3p2);
        Assert.assertNull(c3x1);
        Assert.assertNull(c3x2);

        Integer c4p1 = c4.getProducts().get("P1");

        Assert.assertNotNull(c4p1);
        Assert.assertEquals(c4p1, 10, 0);

        Integer c4p2 = c4.getProducts().get("P2");
        Integer c4p3 = c4.getProducts().get("P3");
        Integer c4x1 = c4.getProducts().get("X1");
        Integer c4x2 = c4.getProducts().get("X2");

        Assert.assertNull(c4p2);
        Assert.assertNull(c4p3);
        Assert.assertNull(c4x1);
        Assert.assertNull(c4x2);

        Integer c5p2 = c5.getProducts().get("P2");
        Integer c5p3 = c5.getProducts().get("P3");
        Integer c5x2 = c5.getProducts().get("X2");

        Assert.assertNotNull(c5p2);
        Assert.assertEquals(c5p2, 20, 0);
        Assert.assertNotNull(c5p3);
        Assert.assertEquals(c5p3, 90, 0);
        Assert.assertNotNull(c5x2);
        Assert.assertEquals(c5x2, 50, 0);

        Integer c5p1 = c5.getProducts().get("P1");
        Integer c5x1 = c5.getProducts().get("X1");

        Assert.assertNull(c5p1);
        Assert.assertNull(c5x1);

        Integer c6p3 = c6.getProducts().get("P3");
        Integer c6x1 = c6.getProducts().get("X1");

        Assert.assertNotNull(c6p3);
        Assert.assertEquals(c6p3, 30, 0);
        Assert.assertNotNull(c6x1);
        Assert.assertEquals(c6x1, 10, 0);

        Integer c6p1 = c6.getProducts().get("P1");
        Integer c6x2 = c6.getProducts().get("X2");
        Integer c6p2 = c6.getProducts().get("P2");

        Assert.assertNull(c6p1);
        Assert.assertNull(c6p2);
        Assert.assertNull(c6x2);

        Integer c7p3 = c7.getProducts().get("P3");
        Integer c7x1 = c7.getProducts().get("X1");
        Integer c7x2 = c7.getProducts().get("X2");

        Assert.assertNotNull(c7p3);
        Assert.assertEquals(c7p3, 10, 0);
        Assert.assertNotNull(c7x1);
        Assert.assertEquals(c7x1, 40, 0);
        Assert.assertNotNull(c7x2);
        Assert.assertEquals(c7x2, 30, 0);

        Integer c7p1 = c7.getProducts().get("P1");
        Integer c7p2 = c7.getProducts().get("P2");

        Assert.assertNull(c7p1);
        Assert.assertNull(c7p2);

        Integer c8x2 = c8.getProducts().get("X2");

        Assert.assertNotNull(c8x2);
        Assert.assertEquals(c8x2, 60, 0);

        Integer c8p1 = c8.getProducts().get("P1");
        Integer c8p2 = c8.getProducts().get("P2");
        Integer c8p3 = c8.getProducts().get("P3");
        Integer c8x1 = c8.getProducts().get("X1");

        Assert.assertNull(c8p1);
        Assert.assertNull(c8p2);
        Assert.assertNull(c8p3);
        Assert.assertNull(c8x1);
    }
}

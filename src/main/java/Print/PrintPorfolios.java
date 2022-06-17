package Print;

import Portfolio.Portfolio;

import java.util.HashMap;

public class PrintPorfolios {

    private PrintPorfolios(HashMap<String, Portfolio> portfolios) {
        throw new AssertionError();
    }

    public static void printPortfolios(HashMap<String, Portfolio> portfolios){
        /**
         * This function prints the attribute "portfolios" of the class
         * **/
        for (var entry : portfolios.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}

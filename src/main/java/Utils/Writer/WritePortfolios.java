package Utils.Writer;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WritePortfolios {
    // Non-instantiable class
    private WritePortfolios() {
        throw new AssertionError();
    }

    public static void writePortfolios(String fileName, HashMap<String, Float> portfolioPrice) throws IOException {
        /**
         * @param fileName : The name of the output file
         * @param portfolioPrice : The data that maps the portfolios with their price
         * @throws IOException
         * This function writes the results of the processing of the value of the portfolio in a csv file
         * **/
        CSVWriter writer = new CSVWriter(new FileWriter(fileName, false), ',', CSVWriter.NO_QUOTE_CHARACTER);
        String header = "PTF,price";
        writer.writeNext(header);

        for (var entry : portfolioPrice.entrySet()) {
            String[] nextLine = (entry.getKey() + "," + entry.getValue()).split(",");
            writer.writeNext(nextLine);
        }
        writer.close();
    }
}

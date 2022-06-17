package Writer;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WriteClients {
    private WriteClients() {
        throw new AssertionError();
    }

    public static void writeClient(String fileName, HashMap<String, Float> clientsCapital) throws IOException {
        /**
         * @param fileName : The name of the output file
         * @param clientsCapital : The data that maps the clients with their capital
         * @throws IOException
         * This function writes the results of the processing of the capital of the clients in a csv file
         * **/
        CSVWriter writer = new CSVWriter(new FileWriter(fileName, false), ',', CSVWriter.NO_QUOTE_CHARACTER);
        String header = "Client,capital";
        writer.writeNext(header);

        for (var entry : clientsCapital.entrySet()) {
            String[] nextLine = (entry.getKey() + "," + entry.getValue()).split(",");
            writer.writeNext(nextLine);
        }
        writer.close();
    }

}

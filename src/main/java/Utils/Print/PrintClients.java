package Utils.Print;

import Client.Client;

import java.util.HashMap;

public class PrintClients {
    // Non-instantiable class
    private PrintClients() {
        throw new AssertionError();
    }

    public static void printClients(HashMap<String, Client>  clients){
        /**
         * This function prints the attribute "clients" of the class
         * **/
        for (var entry : clients.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}

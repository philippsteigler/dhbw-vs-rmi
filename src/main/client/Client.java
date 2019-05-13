package main.client;

import main.server.ServerInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) {
        try {
            // Read IP of remote server
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter IP: ");
            String ip = br.readLine();

            // Retrieve registry from remote server
            Registry registry = LocateRegistry.getRegistry(ip, 1099);

            // Get reference to remote object buy looking through the registry
            ServerInterface obj = (ServerInterface) registry.lookup( "My-Server");

            // TEST: getSystemInfo()
            System.out.println(obj.getSystemInfo());

            // TEST: generateRandomNumber(min, max)
            for (int i = 0; i < 5000; i++) {
                System.out.println(obj.generateRandomNumber(0, 1000000));
                TimeUnit.MILLISECONDS.sleep(1);
            }

            // TEST: length()
            String test = "This is a test string";
            System.out.println(obj.length(test));

        } catch (Exception e) {
            System.out.println("--- Error: " + e.getMessage());
        }
    }
}

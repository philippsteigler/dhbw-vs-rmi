package main.client;

import main.server.ServerInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Client {

    private static void testGenerateRandomNumber(ServerInterface obj) throws RemoteException, InterruptedException {
        System.out.println("================================================");
        System.out.println("DEMO for generateRandomNumber(min, max):\n");

        for (int i = 0; i < 5000; i++) {
            System.out.println(obj.generateRandomNumber(0, 2000000));
            TimeUnit.NANOSECONDS.sleep(1);
        }
    }

    private static void testStrLength(ServerInterface obj) throws RemoteException {
        System.out.println("================================================");
        System.out.println("DEMO for strLength(s):\n");

        String str = "This is a test string";
        System.out.println("String: '" + str + "'");
        System.out.println("Length equals: " + obj.strLength(str));
    }

    private static void testGetSystemInfo(ServerInterface obj) throws RemoteException {
        System.out.println("================================================");
        System.out.println("DEMO for getSystemInfo():\n");

        System.out.println(obj.getSystemInfo());
    }

    public static void main(String[] args) {
        try {
            Registry registry;

            // Retrieve registry from remote server
            if (args.length == 0) {
                // Read IP of remote server
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter IP: ");
                String ip = br.readLine();

                registry = LocateRegistry.getRegistry(ip, 1099);
            } else {
                registry = LocateRegistry.getRegistry(args[0], 1099);
            }

            System.out.println("--- Java RMI registry located");

            // Get reference to remote object buy looking through the registry
            ServerInterface obj = (ServerInterface) registry.lookup( "My-Server");
            System.out.println("--- Connection to remote host established");

            // TEST SERVER FUNCTIONS
            testGenerateRandomNumber(obj);
            testStrLength(obj);
            testGetSystemInfo(obj);
        } catch (Exception e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }
}

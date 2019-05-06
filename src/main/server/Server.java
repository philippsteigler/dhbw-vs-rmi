package main.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private Server() throws RemoteException {
        super(0);
    }

    @Override
    public String getOS() {
        return System.getProperty("os.name");
    }

    public static void main(String args[]) {
        System.out.println("--- RMI server started");

        try {
            // Read IP of server network interface
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter IP: ");
            String ip = br.readLine();

            // Set hostname to server IP in order to overwrite localhost reference
            System.setProperty("java.rmi.server.hostname", ip);

            // Create Java RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("--- Java RMI registry created.");

            // Initialize server object and bind it to registry
            Server obj = new Server();
            registry.rebind("My-Server", obj);
            System.out.println("--- PeerServer bound in registry");
        } catch (Exception e) {
            System.out.println("--- Java RMI registry already exists.");
        }
    }
}

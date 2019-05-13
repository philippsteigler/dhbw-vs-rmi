package main.server;

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
        return "OS: " + System.getProperty("os.name")
                + "\nVersion: " + System.getProperty("os.version")
                + "\nArchitecture: " + System.getProperty("os.arch")
                + "\nRuntime Environment: " + System.getProperty("java.runtime.name")
                + "\nRuntime Version: " + System.getProperty("java.runtime.version")
                + "\nJava Version: " + System.getProperty("java.version")
                + "\nJava Home:" + System.getProperty("java.home")
                + "\n----------------------"
                + "\nUser: " + System.getProperty("user.name")
                + "\nHome Dir: " + System.getProperty("user.home")
                + "\nCurrent Dir: " + System.getProperty("user.dir");
    }

    public static void main(String args[]) {
        if (args[0] != null) {
            try {
                // Set hostname to server IP in order to overwrite localhost reference
                System.setProperty("java.rmi.server.hostname", args[0]);
                System.out.println("--- RMI server started");

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
}

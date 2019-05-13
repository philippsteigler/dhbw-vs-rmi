package main.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private Server() throws RemoteException {
        super(0);
    }

    @Override
    public String getSystemInfo() {
        return "-- OS --"
                + "\nOS: " + System.getProperty("os.name")
                + "\nVersion: " + System.getProperty("os.version")
                + "\nArchitecture: " + System.getProperty("os.arch")
                + "\n-- RUNTIME --"
                + "\nRuntime Environment: " + System.getProperty("java.runtime.name")
                + "\nRuntime Version: " + System.getProperty("java.runtime.version")
                + "\nJava Version: " + System.getProperty("java.version")
                + "\nJava Home:" + System.getProperty("java.home")
                + "\n-- USER --"
                + "\nUser: " + System.getProperty("user.name")
                + "\nHome Dir: " + System.getProperty("user.home")
                + "\nCurrent Dir: " + System.getProperty("user.dir");
    }

    @Override
    public int generateRandomNumber(int min, int max) {
        MersenneTwister mt = new MersenneTwister();
        return mt.nextInt(min, max);
    }

    @Override
    public int strLength(String s) {
        try {
            MersenneTwister mt = new MersenneTwister();
            int np = mt.nextInt(0, s.length()*3);

            if (np == s.length()) {
                return np;
            } else {
                TimeUnit.NANOSECONDS.sleep(1);
                return strLength(s);
            }
        } catch (Exception e) {
            System.out.println("[ERROR]: " + e.getMessage());
            return 0;
        }
    }

    public static void main(String[] args) {
        try {
            // Set hostname to server IP in order to overwrite localhost reference
            System.setProperty("java.rmi.server.hostname", args[0]);
            System.out.println("--- RMI server started");

            // Create Java RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("--- Java RMI registry created");

            // Initialize server object and bind it to registry
            registry.rebind("My-Server", new Server());
            System.out.println("--- PeerServer bound in registry");
        } catch (Exception e) {
            System.out.println("[ERROR]: " + e.getMessage());
        }
    }
}

package main.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private Server() throws RemoteException {
        super(0);
    }

    @Override
    public String getOS() {
        return System.getProperty("os.name");
    }

    public static void main(String args[]) throws Exception {
        System.out.println("--- RMI server started");

        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("--- Java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("--- Java RMI registry already exists.");
        }

        Server obj = new Server();
        Naming.rebind("My-Server", obj);
        System.out.println("--- PeerServer bound in registry");
    }
}

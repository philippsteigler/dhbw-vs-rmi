package main;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private Server() throws RemoteException {
        super(0);
    }

    @Override
    public double getPI(int precision) {
        double pi = 0;
        double denominator = 1;

        for (int x = 0; x < precision; x++) {

            if (x % 2 == 0) {
                pi = pi + (1 / denominator);
            } else {
                pi = pi - (1 / denominator);
            }
            denominator = denominator + 2;
        }

        return pi * 4;
    }

    public static void main(String args[]) throws Exception {
        System.out.println("--- RMI server started");

        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("--- java RMI registry created.");
        } catch (RemoteException e) {
            System.out.println("--- java RMI registry already exists.");
        }

        Server obj = new Server();

        Naming.rebind("rmi://192.168.2.108/PI-Server", obj);
        System.out.println("--- PeerServer bound in registry");
    }
}

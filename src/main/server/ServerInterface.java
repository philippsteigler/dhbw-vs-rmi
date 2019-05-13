package main.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    String getSystemInfo() throws RemoteException;
    int generateRandomNumber(int min, int max) throws RemoteException;
}

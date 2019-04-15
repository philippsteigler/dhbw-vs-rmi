package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    double calculatePI(int precision) throws RemoteException;
    String getOS() throws RemoteException;
}

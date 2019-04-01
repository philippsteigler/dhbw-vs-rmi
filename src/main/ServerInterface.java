package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    double getPI(int precision) throws RemoteException;
}

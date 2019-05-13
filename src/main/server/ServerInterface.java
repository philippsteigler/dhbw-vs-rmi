package main.server;

import java.rmi.Remote;

public interface ServerInterface extends Remote {
    String getSystemInfo();
    int generateRandomNumber(int min, int max);
    int length(String s);
}

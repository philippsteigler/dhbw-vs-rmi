package main;

import java.rmi.Naming;

public class Client {

    public static void main(String args[]) throws Exception {
        ServerInterface obj = (ServerInterface) Naming.lookup("rmi://192.168.2.108:1099/PI-Server");
        System.out.println(obj.getPI(100000));
    }
}

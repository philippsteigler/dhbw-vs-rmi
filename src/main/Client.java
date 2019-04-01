package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class Client {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter IP: ");
        String ip = br.readLine();

        System.out.print("Enter port (default is 1099): ");
        String port = br.readLine();

        ServerInterface obj = (ServerInterface) Naming.lookup("rmi://" + ip + ":" + port + "/PI-Server");
        for (int i = 0; i < 100; i++) {
            System.out.println(obj.getPI(10000000));
        }
    }
}

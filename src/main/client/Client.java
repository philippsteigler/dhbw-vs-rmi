package main.client;

import main.server.ServerInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class Client {

    public static void main(String args[]) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter IP: ");
            String ip = br.readLine();

            ServerInterface obj = (ServerInterface) Naming.lookup("rmi://" + ip + ":1099" + "/My-Server");

            // Test getOS
            System.out.println(obj.getOS());
        } catch (Exception e) {
            System.out.println("--- Error: " + e.getMessage());
        }
    }
}

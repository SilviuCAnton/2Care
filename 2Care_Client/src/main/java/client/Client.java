package client;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket client;

    public Client(String host,int port) {
        try {
            client = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClient() {
        return client;
    }
}

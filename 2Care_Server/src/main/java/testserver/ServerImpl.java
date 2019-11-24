package testserver;

import repositories.UserAccountDBRepository;
import serving.LoginClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerImpl {

    public static void main(String[] args) {
        UserAccountDBRepository userAccountDBRepository = new UserAccountDBRepository("jdbc:postgresql://localhost:5432/2CareDB", "postgres", "Super_paSS");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1256);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();
                Thread t = new Thread(new LoginClient(clientSocket, userAccountDBRepository));
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
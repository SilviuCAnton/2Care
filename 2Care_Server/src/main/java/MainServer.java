

import repositories.UserAccountDBRepository;
import serving.LoginClient;
import serving.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        UserAccountDBRepository userAccountDBRepository = new UserAccountDBRepository("jdbc:postgresql://localhost:5432/2CareDB", "postgres", "1234");
        try {
            server = new ServerSocket(1256);
            while(true) {
                Request request;
                Socket client = server.accept();

                new LoginClient(client, userAccountDBRepository).run();

//                switch (request) {
//                    case LOGIN: new LoginClient(client,userAccountDBRepository).run();
//                    break;
//                    case GETALL: break;
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            assert server != null;
//            server.close();
        }
    }
}


import repositories.UserAccountDBRepository;
import serving.LoginClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        UserAccountDBRepository userAccountDBRepository = new UserAccountDBRepository("jdbc:postgresql://localhost:5432/2CareDB", "postgres", "Super_paSS");
        try {
            server = new ServerSocket(1256);
            while(true) {
                Socket client = server.accept();
                new LoginClient(client,userAccountDBRepository).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert server != null;
            server.close();
        }
    }
}
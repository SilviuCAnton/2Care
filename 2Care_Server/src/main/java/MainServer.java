

import domain.UserAccount;
import repositories.UserAccountDBRepository;
import serving.ServeClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        UserAccountDBRepository userAccountDBRepository = new UserAccountDBRepository("jdbc:postgresql://localhost:5432/2CareDB", "postgres", "1234");
        userAccountDBRepository.save(new UserAccount(1,"asd","asd",true));
        try {
            server = new ServerSocket(1256);
            while(true) {
                Socket client = server.accept();
                new ServeClient(client,userAccountDBRepository).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert server != null;
            server.close();
        }
    }
}
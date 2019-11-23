

import domain.UserAccount;
import repositories.UserAccountDBRepository;

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
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                String user = (String) in.readObject();
                String password = (String) in.readObject();
                System.out.println(user + " " + password);
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                out.writeObject(1);
                client.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert server != null;
            server.close();
        }
    }
}
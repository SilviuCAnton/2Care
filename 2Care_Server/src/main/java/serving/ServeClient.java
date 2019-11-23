package serving;

import repositories.UserAccountDBRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServeClient implements Runnable {
    Socket client;
    UserAccountDBRepository userAccountDBRepository;

    public ServeClient(Socket client, UserAccountDBRepository userAccountDBRepository) {
        this.client = client;
        this.userAccountDBRepository = userAccountDBRepository;
    }
    private void respond(ObjectInputStream in,ObjectOutputStream out){
        try {
            String command = (String) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(client.getInputStream());

            String user = (String) in.readObject();
            String password = (String) in.readObject();
            System.out.println(user + " " + password);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(1);
            respond(in,out);
            client.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

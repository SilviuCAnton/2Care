package serving;

import domain.Entity;
import domain.UserAccount;
import repositories.UserAccountDBRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

public class ServeClient implements Runnable {
    private Socket client;
    private UserAccountDBRepository userAccountDBRepository;

    public ServeClient(Socket client, UserAccountDBRepository userAccountDBRepository) {
        this.client = client;
        this.userAccountDBRepository = userAccountDBRepository;
    }
    private void respond(ObjectInputStream in,ObjectOutputStream out){
        try {
            String command= "";
            while(!command.equals("x"))
            {
                command = (String) in.readObject();
                System.out.println(command);
                userAccountDBRepository.findAll();
                Method method = userAccountDBRepository.getClass().getMethod(command);
                List<Entity> all = (List<Entity>) method.invoke(userAccountDBRepository);
                out.writeObject(all);

            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
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
            out.writeObject(2);
            respond(in,out);
            client.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

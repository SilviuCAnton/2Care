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

public class LoginClient implements Runnable {
    private Socket client;
    private UserAccountDBRepository userAccountDBRepository;
    private ObjectInputStream in;

    public LoginClient(Socket client, UserAccountDBRepository userAccountDBRepository) {
        try {
            this.client = client;
            this.userAccountDBRepository = userAccountDBRepository;
            in = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void respond(ObjectInputStream in,ObjectOutputStream out){
        try {
            String command= "";
            while(!command.equals("x"))
            {
                command = (String) in.readObject();
                System.out.println(command);
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
        try {
            String user = (String) in.readObject();
            String password = (String) in.readObject();
            System.out.println(user + " " + password);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            UserAccount userAccount = userAccountDBRepository.findByUsername(user, password);
            if(userAccount == null) {
                out.writeObject(0);
            }
            else if(userAccount.isDoctor()) {
                out.writeObject(2);
            }
            else {
                out.writeObject(1);
            }
            respond(new ObjectInputStream(client.getInputStream()),out);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

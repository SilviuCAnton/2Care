package proxy;

import domain.Entity;
import domain.UserAccount;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.channels.UnresolvedAddressException;
import java.util.List;

public class UserAccountRepoProxy implements CrudRepository<Integer, UserAccount>{
    private Socket socket;
    private String command;

    public UserAccountRepoProxy() {
        try {
            socket = new Socket("127.0.0.1", 1256);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public UserAccount findOne(Integer id) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(this.getClass());
            out.writeObject("findAll");
            out.writeObject(id);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return (UserAccount) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<UserAccount> findAll() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(this.getClass());
            out.writeObject("findAll");
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return (List<UserAccount>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserAccount save(UserAccount entity) {
        return null;
    }

    @Override
    public UserAccount delete(Integer integer) {
        return null;
    }

    @Override
    public UserAccount update(UserAccount entity) {
        return null;
    }
}

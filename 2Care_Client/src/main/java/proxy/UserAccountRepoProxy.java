package proxy;

import domain.Entity;
import domain.Patient;
import domain.UserAccount;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.channels.UnresolvedAddressException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAccountRepoProxy implements CrudRepository<Integer, UserAccount>{
    private Socket socket;
    private String command;

    public UserAccountRepoProxy(Socket socket) {
        this.socket = socket;
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
    public List<UserAccount> findAll() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("findAll");
            List<UserAccount> all = readInUserAccounts();
            return all;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Patient> findAllPatients() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("findAll");
            return readInPatients();
        } catch (IOException e) {
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

    private List<UserAccount> readInUserAccounts() {
        return new ArrayList<>(Arrays.asList(new UserAccount(1,"asd","asd",true),
                new UserAccount(2,"aef","aef",false),new UserAccount(3,"tbsgr","srhsrh",true),
                new UserAccount(4,"asd","asd",true),new UserAccount(5,"ztdhzt","thHdsf",false)));
    }
    private List<Patient> readInPatients() {
        return new ArrayList<>(Arrays.asList(new Patient(1,"Gabriel","Popescu","1234"),new Patient(2,"Andrei","Ionescu","1234"),
                new Patient(3,"Vlad","Manolescu","1234"), new Patient(4,"Adrian","Manole","1234"),
                new Patient(6,"Ion","Creanga","1234"), new Patient(7,"Mihai","Mihailescu","1234")));
    }
}

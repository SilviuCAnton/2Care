package proxy;

import domain.Entity;
import domain.Patient;
import domain.Stats;
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
import java.util.Comparator;
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


    public List<Stats> getAllStatsFromLastWeek(Integer id){
        List<Stats> all = new ArrayList<>();
        for(Stats s : getAllStats()){
            if(s.getId().equals(id)){
                all.add(s);
            }
        }
        return all;
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
    public List<Stats> getAllStats(){
        List<Stats> all = new ArrayList<>(Arrays.asList(new Stats(1,7.12,"120/70",120,78.1,1),
                new Stats(1,3.21,"110/80",100,78.3,2),new Stats(1,4.19,"100/60",112,78.0,3),new Stats(1,9.1,"120/70",110,78.2,4),
                new Stats(1,2.6,"110/70",90,78.3,5),new Stats(1,7.14,"90/70",98,78.4,6),
                new Stats(1,4.12,"100/67",114,78.41,7),new Stats(2,4.19,"100/50",93,78.1,1),new Stats(2,7.12,"120/70",120,90.0,2),new Stats(2,5.4,"110/70",110,90.1,3),new Stats(2,6.16,"110/80",110,90.2,4),
                new Stats(2,8.99,"125/80",120,89.9,5),new Stats(3,7.12,"120/70",90,57.1,1),new Stats(3,3.12,"130/90",115,59.1,2)));
        all.sort(new Comparator<Stats>() {
            @Override
            public int compare(Stats o1, Stats o2) {
                return o1.getDay() - o2.getDay();
            }
        });
        return all;
    }
}

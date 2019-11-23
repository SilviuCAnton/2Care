package domain;

import java.io.Serializable;
import java.util.Objects;

public class UserAccount extends Entity<Integer> implements Serializable {

    private static final long serialVersionUID = -889976693182180703L;

    private String username, password;
    private boolean isDoctor;

    public UserAccount(int id, String username, String password, boolean isDoctor) {
        super();
        setId(id);
        this.username = username;
        this.password = password;
        this.isDoctor = isDoctor;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isDoctor=" + isDoctor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return getId().equals(that.getId()) &&
                isDoctor == that.isDoctor &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), username, password, isDoctor);
    }
}

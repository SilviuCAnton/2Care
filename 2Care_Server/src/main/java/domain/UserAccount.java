package domain;

public class UserAccount extends Entity<String> {

    private String userName;
    private String password;
    private boolean isDoctor;

    public UserAccount(String id, String userName, String password, boolean isDoctor) {
        this.userName = userName;
        this.password = password;
        this.isDoctor = isDoctor;
        setId(id);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}

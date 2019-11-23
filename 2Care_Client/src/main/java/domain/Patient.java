package domain;

public class Patient extends Entity<Integer> {

    private String firstName;
    private String lastName;
    private String cnp;

    public Patient(Integer id, String firstName, String lastName, String cnp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        setId(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
}

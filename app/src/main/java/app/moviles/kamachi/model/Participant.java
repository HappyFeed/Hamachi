package app.moviles.kamachi.model;

public class Participant {

    private String name;
    private String email;

    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Participant() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

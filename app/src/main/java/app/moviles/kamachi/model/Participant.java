package app.moviles.kamachi.model;

public class Participant {

    private String name;
    private String email;
    private String id;

    public Participant(String id,String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Participant() {
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }
}

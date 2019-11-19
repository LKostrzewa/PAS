package pl.bialekkostrzewa.model;

public abstract class User {

    private String login;
    private String name;
    private String surname;
    private boolean isActive;

    public User(){

    }

    public User(String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.isActive = true;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public abstract String toString();
}

package model;

public abstract class User {

    private String login;
    private String name;
    private String surname;

    public User(String login, String name, String surname) {
        this.login = login;
        this.name = name;
        this.surname = surname;
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

    @Override
    public abstract String toString();
}

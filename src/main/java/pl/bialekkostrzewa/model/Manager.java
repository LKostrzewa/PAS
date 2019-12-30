package pl.bialekkostrzewa.model;

public class Manager extends User {

    public Manager(String login, String password, String name, String surname) {
        super(login, password, name, surname);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "login" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                '}';
    }
}

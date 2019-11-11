package pl.bialekkostrzewa.model;

public class Manager extends User {

    public Manager(String login, String name, String surname) {
        super(login, name, surname);
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

package pl.bialekkostrzewa.model;

public class Administrator extends User {

    public Administrator(String login, String name, String surname) {
        super(login, name, surname);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "login" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                '}';
    }

}

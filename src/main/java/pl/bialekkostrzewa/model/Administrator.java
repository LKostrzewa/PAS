package pl.bialekkostrzewa.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class Administrator extends User {

    public Administrator(String login, String password, String name, String surname) {
        super(login, password, name, surname);
        setAuthorities(List.of(new SimpleGrantedAuthority("ADMIN")));
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "login" + getUsername() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                '}';
    }

}

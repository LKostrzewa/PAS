package pl.bialekkostrzewa.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class Manager extends User {

    public Manager(String login, String password, String name, String surname) {
        super(login, password, name, surname);
        setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_MANAGER")));
    }

    @Override
    public String toString() {
        return "Manager{" +
                "login" + getUsername() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                '}';
    }
}

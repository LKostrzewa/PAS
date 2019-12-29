package pl.bialekkostrzewa.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.bialekkostrzewa.model.Administrator;
import pl.bialekkostrzewa.model.Manager;
import pl.bialekkostrzewa.model.MyUserDetails;
import pl.bialekkostrzewa.model.User;

import java.util.Collections;
import java.util.List;
/*
@Component
public class UserToSecurityUserConverter implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User user) {
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUsername(user.getLogin());
        userDetails.setPassword(user.getPassword());
        userDetails.setEnabled(user.isActive());

        //TODO nw czy to nadal działa - problem jest taki, że po rejestracji/dodaniu uzytkownika przez admina
        // nie można się zalogować na nowe konto klienta :/

        // TODO z tego co wyczytałem w dokuentacji ROLE nie trzeba dodawać on sam wie ocb

        //
        if(user instanceof Administrator){
            userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        }
        else if(user instanceof Manager){
            userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("MANAGER")));
        }
        else {
            userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("USER")));
        }
        return userDetails;
    }
}*/

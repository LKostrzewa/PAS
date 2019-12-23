package pl.bialekkostrzewa.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.bialekkostrzewa.model.MyUserDetails;
import pl.bialekkostrzewa.model.User;

import java.util.Collections;

public class UserToSecurityUserConverter implements Converter<User, UserDetails> {
    @Override
    public UserDetails convert(User user) {
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUsername(user.getLogin());
        userDetails.setPassword(user.getPassword());
        userDetails.setEnabled(user.isActive());
        //TODO tu mamy problem bo trzeba ustawić role naszego usera - klient,admin,manager
        //userDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority()));
        return userDetails;
    }
}

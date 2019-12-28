package pl.bialekkostrzewa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bialekkostrzewa.converter.UserToSecurityUserConverter;
import pl.bialekkostrzewa.model.Administrator;
import pl.bialekkostrzewa.model.Manager;
import pl.bialekkostrzewa.model.User;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    private UserToSecurityUserConverter userToSecurityUserConverter;
    /*@Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }*/

    @Autowired
    public void setUserToSecurityUserConverter(UserToSecurityUserConverter userToSecurityUserConverter) {
        this.userToSecurityUserConverter = userToSecurityUserConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUser(s);
        if(user == null)
            throw new UsernameNotFoundException(String.format( "A user with username {} doesn't exist", s));
        //return userToSecurityUserConverter.convert(user);
        boolean enabled = user.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        //TODO nadal niewiadom jest czy to dziala
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user instanceof Administrator){
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else if(user instanceof Manager){
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        return  new org.springframework.security.core.userdetails.User
                (user.getLogin(),
                        user.getPassword().toLowerCase(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        authorities);
    }
}

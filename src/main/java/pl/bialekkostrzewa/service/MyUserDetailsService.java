package pl.bialekkostrzewa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import pl.bialekkostrzewa.converter.UserToSecurityUserConverter;
import pl.bialekkostrzewa.model.Administrator;
import pl.bialekkostrzewa.model.Manager;
import pl.bialekkostrzewa.model.MyUserDetails;
import pl.bialekkostrzewa.model.User;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;
    //private UserToSecurityUserConverter userToSecurityUserConverter;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    /*@Autowired
    public void setUserToSecurityUserConverter(UserToSecurityUserConverter userToSecurityUserConverter) {
        this.userToSecurityUserConverter = userToSecurityUserConverter;
    }*/

    public MyUserDetailsService(UserService userService){
        this.userService = userService;
        userService.addUser(new Administrator("admin", "password", "Jan", "Kowalski"));
        userService.addUser(new Manager("manager", "password", "Piotr", "Nowak"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUser(s);
        if(user == null)
            throw new UsernameNotFoundException(String.format( "A user with username {} doesn't exist", s));
        return new MyUserDetails(user);
    }
}

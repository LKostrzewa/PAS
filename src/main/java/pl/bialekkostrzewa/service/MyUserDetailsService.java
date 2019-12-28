package pl.bialekkostrzewa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bialekkostrzewa.converter.UserToSecurityUserConverter;
import pl.bialekkostrzewa.model.User;

import javax.transaction.Transactional;

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
        return userToSecurityUserConverter.convert(user);
    }
}

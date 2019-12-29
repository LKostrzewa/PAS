package pl.bialekkostrzewa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bialekkostrzewa.repository.AccountRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;
    //private AccountRepository accountRepository;
    //private UserToSecurityUserConverter userToSecurityUserConverter;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    /*@Autowired
    public void setUserToSecurityUserConverter(UserToSecurityUserConverter userToSecurityUserConverter) {
        this.userToSecurityUserConverter = userToSecurityUserConverter;
    }*/

    /*@Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }*/


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userService.getUser(s);
    }
}

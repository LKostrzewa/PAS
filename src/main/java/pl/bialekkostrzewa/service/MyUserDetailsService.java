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

    @Transactional
    public User addUser(pl.bialekkostrzewa.model.User user){
        User user1 = new User(user.getUsername(), user.getPassword(), true, true, true, true,
                user.getAuthorities());
        //return accountRepository.save(user1);
        return user1;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        pl.bialekkostrzewa.model.User user = userService.getUser(s);
        return new User(user.getUsername(), user.getPassword(), true, true, true, true,
                user.getAuthorities());
    }
}

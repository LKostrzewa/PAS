package pl.bialekkostrzewa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.bialekkostrzewa.model.MyUserDetails;
import pl.bialekkostrzewa.service.MyUserDetailsService;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider());
    }


    //nwm na ile to jest okej
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN")
                .and().withUser("manager").password(passwordEncoder().encode("password")).roles("MANAGER");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        //TODO
        //no dzien dobry wpierdalaam sie na grubo tutaj
        //Czy tutaj jezeli jest hasRole nie powinno byc bez refiksu ROLE_ zmienilem gdyz wyjebywalo exception jezeli mialo by byc role przed to by bylo
        // hasAuthority
        http.authorizeRequests()
                //nwm te chyba nie dzialaja jakos xd
                //bo jak jstm zalogowany jako manager to mg inne rzeczy se przegladac
                //byc moze to kwestia scope'ow
                .antMatchers("/restaurant/reservations").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/restaurant/resources").access("hasRole('MANAGER')")
                .antMatchers("/restaurant/users").access("hasRole('ADMIN')")
                .and()
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/default")
                    //.failureUrl("/loginPage?error")
                    .usernameParameter("username").passwordParameter("password");
                //.and()
                   // .logout().logoutSuccessUrl("/loginPage?logout");

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

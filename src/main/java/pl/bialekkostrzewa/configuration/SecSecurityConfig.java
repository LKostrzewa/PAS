package pl.bialekkostrzewa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider());
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        //TODO
        //no dzien dobry wpierdalaam sie na grubo tutaj
        //czy tutaj defaultSuccessUrl bedzie chyba inny dla kazdej roli
        //i sugestie takie na chlopski rozum :
        //loginPage to chyba login.html badz tez restaurant/login
        //userParameter to login
        //i nwm wariacie jak te beany tutaj bo to jakos do xml trzeba zaladowac pierdolnac z grubej rury
        http.authorizeRequests()
                .antMatchers("/restaurant/reservations").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/restaurant/resources").access("hasRole('ROLE_MANAGER')")
                .antMatchers("/restaurant/users").access("hasRole('ROLE_ADMIN')")
                .and()
                    .formLogin().loginPage("/loginPage")
                    .defaultSuccessUrl("/homePage")
                    .failureUrl("/loginPage?error")
                    .usernameParameter("username").passwordParameter("password")
                .and()
                    .logout().logoutSuccessUrl("/loginPage?logout");

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

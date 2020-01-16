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

    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecSecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        //auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN")
        //        .and().withUser("manager").password(passwordEncoder().encode("password")).roles("MANAGER");
        //auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.httpBasic()
                //.and().csrf().and()
                .and().requiresChannel().anyRequest().requiresSecure()
                .and()
                .authorizeRequests()
                //TODO matchers można chyba uprościć
                .antMatchers("/reservations", "/reservations/" , "/reservations/**", "/reservations/*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/resources", "/resources/", "/resources/**", "/resources/*").hasRole("MANAGER")
                .antMatchers("/users", "/users/", "/users/**", "/users/*").hasRole("ADMIN")
                .and()
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/main")
                    //.failureUrl("/loginPage?error")
                    .usernameParameter("username").passwordParameter("password").permitAll()
                .and()
                    .logout().permitAll();
        http
                .portMapper()
                .http(8080).mapsTo(8443);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

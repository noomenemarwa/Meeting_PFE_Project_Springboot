package com.leoni.pfe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {





    // Security: BasicAuth, JWT, oauth2.0, LDAP, ...

    // @Autowired
    // private CustomAuthenticationProvider customAuthenticationProvider;

    /**
     * Authentication: Les personnes authorises a acceder a l'application (QUI)
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // super.configure(auth);
        // auth.authenticationProvider(customAuthenticationProvider);
        auth.inMemoryAuthentication()
                .withUser("ADMIN")
                .password(passwordEncoder().encode("ADMIN"))
                .roles("ADMIN")
                .and()
                .withUser("Marwa")
                .password(passwordEncoder().encode("USER"))
                .roles("USER");

    }

    /**
     * Authorization:
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         // super.configure(http);
        // The cors() method will add the Spring-provided CorsFilter to the application context which in turn bypasses the authorization checks for OPTIONS requests.
        // http.cors();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/personne/login").permitAll()
                .antMatchers("*/*").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and().cors().
                .and()
                .httpBasic();

//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/homePage").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/personne/login", "/Membre/membreIsPresent", "/Membre/findByIdMembre").access("hasRole('USER')")
//                .antMatchers("*/*").access("hasRole('ADMIN')")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();



                // .antMatchers("/dep/*", "/Reunion/*", "/stat")
                // .antMatchers("*/*").hasRole("ADMIN")
                // .antMatchers("/personne/login", "/Membre/membreIsPresent", "/Membre/findByIdMembre").hasRole("USER")

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

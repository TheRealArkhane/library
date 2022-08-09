package com.libtask.library2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("amirlox@mail.ru")
                .password(passwordEncoder().encode("1234"))
                .authorities("USER").build());

        manager.createUser(User.withUsername("business_genius@belprodo.com")
                .password(passwordEncoder().encode("123456"))
                .authorities("USER").build());

        manager.createUser(User.withUsername("typo_albanec228@gmail.com")
                .password(passwordEncoder().encode("123qwe"))
                .authorities("USER").build());

        manager.createUser(User.withUsername("fedyanin.v.v@yandex.ru")
                .password(passwordEncoder().encode("qwertY123"))
                .authorities("USER").build());

        manager.createUser(User.withUsername("pr0-r0ck-sunb0y@mail.ru")
                .password(passwordEncoder().encode("sunboy1992"))
                .authorities("ADMIN").build());

        return manager;
    }
}

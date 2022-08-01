package com.libtask.library2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
    public UserDetailsService userDetailsService() {
        UserDetails user0 =
                User.withDefaultPasswordEncoder()
                        .username("amirlox@mail.ru")
                        .password("1234")
                        .roles("USER")
                        .build();

        UserDetails user1 =
                User.withDefaultPasswordEncoder()
                        .username("business_genius@belprodo.com")
                        .password("1234")
                        .roles("USER")
                        .build();

        UserDetails user2 =
                User.withDefaultPasswordEncoder()
                        .username("typo_albanec228@gmail.com")
                        .password("1234")
                        .roles("USER")
                        .build();

        UserDetails user3 =
                User.withDefaultPasswordEncoder()
                        .username("fedyanin.v.v@yandex.ru")
                        .password("1234")
                        .roles("USER")
                        .build();

        UserDetails admin0 =
                User.withDefaultPasswordEncoder()
                        .username("pr0-r0ck-sunb0y@mail.ru")
                        .password("1234")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user0, user1, user2, user3, admin0);
    }
}

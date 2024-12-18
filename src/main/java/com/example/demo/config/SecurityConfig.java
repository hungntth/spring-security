package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.formLogin((formLogin) -> formLogin.loginProcessingUrl("/login"));

            http.authorizeRequests(req -> req.requestMatchers("/api/v1/auth/login", "/app/v1/auth/register").permitAll()
                    .requestMatchers("/v1/admin/vip").hasRole("ADMIN")
                    .requestMatchers("/v1/admin/normal").hasAnyRole("ADMIN", "USER")
                    .anyRequest().authenticated());

            return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123")
//                .roles("admin", "user")
//                .build();
//
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("222")
//                .roles( "user")
//                .build();


        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("123"))
//                .roles("admin", "user")
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("123"))
//                .roles("admin", "user")
                .authorities("ROLE_USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



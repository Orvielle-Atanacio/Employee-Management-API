package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


import javax.sql.DataSource;

// Marks this class as a configuration class for Spring Security.
@Configuration
public class SecurityConfig {

    // Defines the security filter chain, the core of Spring Security configuration.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                // Define URL-based authorization rules.
                // EMPLOYEE role can view lists and individual employees.
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                // MANAGER role can create and update employees.
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                // Only ADMIN role can delete employees.
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                // All other requests must be authenticated (common best practice).
                .anyRequest().authenticated()
        );

        // Enables HTTP Basic Authentication (pop-up login in browsers).
        http.httpBasic(Customizer.withDefaults());

        // Disables CSRF protection. This is common and safe for stateless REST APIs.
        http.csrf(csrf -> csrf.disable());

        // Makes the API stateless (no sessions). Each request must be authenticated.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    // Configures a custom JDBC-based user detail service to fetch users/roles from your database.
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Defines the custom SQL query to load a user by username.
        // The query must return: username, password, enabled/active status.
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id = ?");

        // Defines the custom SQL query to load authorities/roles by username.
        // The query must return: username, authority/role (e.g., "ROLE_EMPLOYEE").
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id = ?");

        return jdbcUserDetailsManager;
    }
}
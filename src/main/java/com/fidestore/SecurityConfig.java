package com.fidestore; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // En com.fidestore.SecurityConfig.java
    public static final String[] PUBLIC_URL = {
        "/", 
        "/index", 
        "/js/**", 
        "/css/**", 
        "/webjars/**", 
        "/login", 
        "/acceso_denegado",
        "/static/images/**", 
        "/images/**"         
    };
    
    // Rutas para el carrito (Cliente)
    public static final String[] USUARIO_URL = {"/carrito/**", "/facturar/**"};
    
    // Rutas para gestión (Admin y Vendedor)
    public static final String[] GESTION_URL = {"/producto/listado", "/categoria/listado"};
    
    // Rutas exclusivas de Admin
    public static final String[] ADMIN_URL = {"/usuario/**", "/producto/**", "/categoria/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers(PUBLIC_URL).permitAll()
                .requestMatchers(USUARIO_URL).hasRole("USUARIO")
                .requestMatchers(GESTION_URL).hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers(ADMIN_URL).hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
        ).exceptionHandling(exceptions -> exceptions
                .accessDeniedPage("/acceso_denegado")
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        // Los mismos 3 usuarios de prueba que definimos para 'tienda'
        UserDetails juan = User.builder()
                .username("juan").password(passwordEncoder.encode("123")).roles("ADMIN").build();
        UserDetails rebeca = User.builder()
                .username("rebeca").password(passwordEncoder.encode("456")).roles("VENDEDOR").build();
        UserDetails pedro = User.builder()
                .username("pedro").password(passwordEncoder.encode("789")).roles("USUARIO").build();

        return new InMemoryUserDetailsManager(juan, rebeca, pedro);
    }
}
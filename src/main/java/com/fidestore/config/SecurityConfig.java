package com.fidestore.config;

import static org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.disable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //* 1. Definición de Usuarios en Memoria
    @Bean
    public UserDetailsService users() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        UserDetails admin = User.builder()
                .username("juan")
                .password(encoder.encode("123"))
                .roles("ADMIN", "VENDEDOR", "USER")
                .build();
        
        UserDetails vendedor = User.builder()
                .username("rebeca")
                .password(encoder.encode("456"))
                .roles("VENDEDOR", "USER")
                .build();
        
        UserDetails usuario = User.builder()
                .username("pedro")
                .password(encoder.encode("789"))
                .roles("USER")
                .build();
        
        return new InMemoryUserDetailsManager(admin, vendedor, usuario);
    }

    // 2. Configuración de URLs y Permisos
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // PUBLIC_URLS
                .requestMatchers("/", "/index", "/js/**", "/css/**", "/images/**", "/estilos.css", "/webjars/**", "/login").permitAll()
                // USUARIO_URLS
                .requestMatchers("/facturar/carrito").hasRole("USER")
                // ADMIN_OR_VENDEDOR_URLS
                .requestMatchers("/producto/listado", "/usuario/listado").hasAnyRole("ADMIN", "VENDEDOR")
                // ADMIN_URLS
                .requestMatchers("/usuario/nuevo", "/usuario/guardar", "/usuario/modificar/**", "/usuario/eliminar/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login") // Formulario personalizado
                .permitAll()
                .defaultSuccessUrl("/", true)
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling((ex) -> ex
                .accessDeniedPage("/acceso_denegado") // Redirección por falta de permisos
            );
        
        return http.build();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
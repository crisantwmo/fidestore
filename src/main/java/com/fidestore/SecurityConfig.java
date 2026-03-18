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

    public static final String[] PUBLIC_URL = {
        "/",
        "/index",
        "/registro",
        "/js/**",
        "/css/**",
        "/webjars/**",
        "/login",
        "/acceso_denegado",
        "/static/images/**",
        "/images/**",
        "/producto/listado"
    };

    public static final String[] USUARIO_URL = {"/carrito/**", "/facturar/**", "/perfil"};

    public static final String[] GESTION_URL = {"/producto/**", "/categoria/listado"};

    public static final String[] ADMIN_URL = {"/usuario/**", "/categoria/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers(PUBLIC_URL).permitAll()
                .requestMatchers(USUARIO_URL).hasAnyRole("USUARIO", "VENDEDOR", "ADMIN")
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
        UserDetails juan = User.builder()
                .username("juan").password(passwordEncoder.encode("123")).roles("ADMIN").build();
        UserDetails rebeca = User.builder()
                .username("rebeca").password(passwordEncoder.encode("456")).roles("VENDEDOR").build();
        UserDetails pedro = User.builder()
                .username("pedro").password(passwordEncoder.encode("789")).roles("USUARIO").build();

        return new InMemoryUserDetailsManager(juan, rebeca, pedro);
    }
}

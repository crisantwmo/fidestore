package com.gameshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // Clave única para todos: 1111
        UserDetails admin1 = User.builder().username("kendall").password("{noop}1111").roles("ADMIN").build();
        UserDetails admin2 = User.builder().username("brandon").password("{noop}1111").roles("ADMIN").build();
        UserDetails admin3 = User.builder().username("deyrron").password("{noop}1111").roles("ADMIN").build();
        UserDetails seller = User.builder().username("vendedor").password("{noop}1111").roles("SELLER").build();
        UserDetails client = User.builder().username("cliente").password("{noop}1111").roles("CLIENT").build();

        return new InMemoryUserDetailsManager(admin1, admin2, admin3, seller, client);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/index", "/css/**", "/js/**", "/images/**", "/login", "/dragonstone_overview.html").permitAll()
                .requestMatchers("/gameshop/listado", "/categoria/listado").permitAll()
                .requestMatchers("/gameshop/nuevo", "/gameshop/guardar").hasAnyRole("ADMIN", "SELLER")
                .requestMatchers("/gameshop/eliminar/**").hasRole("ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/registro/**").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/").permitAll());

        return http.build();
    }
}

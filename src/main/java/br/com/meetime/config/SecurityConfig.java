package br.com.meetime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/contacts/**").permitAll()
                        .requestMatchers("/webhook/**").permitAll()// Permite acesso sem login
                        .anyRequest().authenticated() // Bloqueia outros endpoints
                )
                .csrf(csrf -> csrf.disable()); // Desabilita CSRF para testes

        return http.build();
    }
}

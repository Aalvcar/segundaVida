package com.miTienda.SegundaVida.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Antonio Álvarez
 */
@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SeguridadConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(auth -> auth
                        // estáticos y páginas públicas
                        .requestMatchers("/css/**", "/js/**", "/imagenes/**", "/", "/files/**", "/auth/**", "/h2-console/**", "/public/**", "/webjars/**")
                        .permitAll()
                        // cualquier otra URL requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/auth/login") // tu plantilla de login
                        .defaultSuccessUrl("/public/index", true)
                        .loginProcessingUrl("/auth/login-post")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/public/index").logoutUrl("/auth/logout")
                );

        http
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                );

        http
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}

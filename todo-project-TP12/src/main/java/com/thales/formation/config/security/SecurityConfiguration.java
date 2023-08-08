package com.thales.formation.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thales.formation.dto.SessionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    /**
     * Kept available for playing with security configuration
     */
    private static final String[] URL_RESOURCES = { "/", "/**/*.js.map", "/**/*.js", "/**/*.html", "/**/*.css",
            "/**/*.jpg","/todos/", "/**/glyphicons*.*", "/**/favicon.ico", "/**/*.png", "/**/*.ttf",
            "/swagger-resources/**","/swagger-ui.html","/v2/api-docs","/webjars/**","/swagger-ui" };
    private static final String URL_LOGIN = "/login";
    private static final String URL_LOGOUT = "/logout";

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("alice")
                .password(passwordEncoder().encode("user"))
                .authorities("user", "add", "update", "delete")
                .build();
        UserDetails user2 = User.withUsername("bob")
                .password(passwordEncoder().encode("user"))
                .authorities("user", "add", "update", "delete")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities("admin", "user", "add", "update", "delete", "deleteAll")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF disabled because of incompatibility with spring security 6 and angular
        // See https://stackoverflow.com/q/74447118/65681
        // and https://docs.spring.io/spring-security/reference/5.8/migration/servlet/exploits.html
        // Filter chain
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(formLogin -> formLogin
                .loginPage("/")
                .loginProcessingUrl(URL_LOGIN)
                .successHandler(getSuccessHandler())
                .failureHandler(getFailureHandler()))
            .logout(logout -> logout
                .logoutUrl(URL_LOGOUT)
                .logoutSuccessHandler(getLogOutHandler()))
            .exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(getAuthenticationEntryPoint()));
        return http.build();
    }

    private AuthenticationSuccessHandler getSuccessHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            SessionDto sessionDto = new SessionDto();
            sessionDto.setLogin(authentication.getName());
            objectMapper.writeValue(response.getOutputStream(), sessionDto);
        };
    }

    private AuthenticationFailureHandler getFailureHandler() {
        return (HttpServletRequest request, HttpServletResponse response,
                AuthenticationException exception) -> fillResponse(response, exception);
    }

    private LogoutSuccessHandler getLogOutHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            fillResponse(response, null);
        };
    }

    private AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) -> {
            if (authException != null) {
                fillResponse(response, authException);
            }
        };
    }

    private void fillResponse(HttpServletResponse response, AuthenticationException exception) throws IOException {
        if (exception == null) {
            response.getOutputStream().write("Access OK".getBytes());
        } else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().print(exception.getMessage());
        }
    }
}

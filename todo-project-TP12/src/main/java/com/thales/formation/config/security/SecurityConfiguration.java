package com.thales.formation.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thales.formation.dto.SessionDto;

/**
 * Application WebMvc resources security configuration
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  private ObjectMapper objectMapper;

  /**
   * Allows to enable or disable csrf protection. By default the configuration is
   * enable. The protection is disable using the <i>public</i> profile. <br>
   * The proection is also by default disable on integration test.
   */
  @Value("${security.enable-csrf:true}")
  private boolean enableCsrf;

  private static final String[] URL_RESOURCES = { "/", "/**/*.js.map", "/**/*.js", "/**/*.html", "/**/*.css",
      "/**/*.jpg", "/**/glyphicons*.*", "/**/favicon.ico", "/**/*.png", "/**/*.ttf" };

  private static final String URL_LOGIN = "/login";

  private static final String URL_LOGOUT = "/logout";

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("bob").password(passwordEncoder().encode("user")).roles("USER").authorities("user", "add", "update", "delete")
        .and().withUser("alice").password(passwordEncoder().encode("user")).roles("USER").authorities("user", "add", "update", "delete")
        .and().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")/*.authorities("user", "add", "update", "delete", "deleteAll")*/;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Spring security configuration. <br>
   * By default the application use : <br>
   * <ul>
   * <li>{@link #URL_LOGIN} as login page URL</li>
   * <li>{@link #URL_LOGOUT} as logout page URL</li>
   * <li>By default all resources are protected excepted urls defined in
   * {@link #configure(WebSecurity)}</li>
   * <li>the csrf protection</li>
   * </ul>
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .formLogin().loginProcessingUrl(URL_LOGIN).failureHandler(getFailureHandler())
        .successHandler(getSuccessHandler()).and().logout().logoutUrl(URL_LOGOUT)
        .logoutSuccessHandler(getLogOutHandler()).and()

        // COMMON

        .authorizeRequests()
        .antMatchers(URL_RESOURCES).permitAll()
        .antMatchers(URL_LOGIN).permitAll()
        .antMatchers(URL_LOGOUT).permitAll()
        .antMatchers("/api/**").permitAll()
        //        .antMatchers("/api/**").hasAnyRole("ADMIN")
        //        .antMatchers("/api/**").hasAnyAuthority("update")
        //        .anyRequest().authenticated()
        //        .antMatchers("/").permitAll()
        //        .anyRequest().authenticated()
        .and().exceptionHandling().authenticationEntryPoint(getAuthenticationEntryPoint()).and()
        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

  }

  /**
   * Configuration of the open web urls. <br>
   * The configuration depend on the application profiles used.
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    List<String> openUrls = new ArrayList<>();
    openUrls.addAll(Arrays.asList(URL_RESOURCES));
    web.ignoring().antMatchers(openUrls.toArray(new String[] {}));
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

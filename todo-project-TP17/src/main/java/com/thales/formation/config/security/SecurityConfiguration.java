package com.thales.formation.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

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

	@Autowired
	private SecurityAuthenticationProvider authenticationProvider;

	@Autowired
	private CsrfTokenRepository csrfTokenRepository;

	@Autowired
	private OncePerRequestFilter csrfFilter;

	/**
	 * Allows to enable or disable csrf protection. By default the configuration is
	 * enable. The protection is disable using the <i>public</i> profile. <br>
	 * The proection is also by default disable on integration test.
	 */
	@Value("${security.enable-csrf:true}")
	private boolean enableCsrf;

	  private static final String[] URL_RESOURCES = { "/", "/**/*.js.map", "/**/*.js", "/**/*.html", "/**/*.css",
		      "/**/*.jpg", "/**/glyphicons*.*","/**/favicon.ico", "/**/*.png", "/**/*.ttf","/swagger-resources/**","/swagger-ui.html","/v2/api-docs","/webjars/**","/swagger-ui" };

	
	private static final String[] SWAGGER_RESOURCES = { "/apidocs/**" };

	private static final String URL_LOGIN = "/login";
	private static final String URL_LOGOUT = "/logout";

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

		CsrfConfigurer<HttpSecurity> c = http

				// DEV MODE

				.formLogin().loginProcessingUrl(URL_LOGIN).failureHandler(getFailureHandler())
				.successHandler(getSuccessHandler()).and().logout().logoutUrl(URL_LOGOUT)
				.logoutSuccessHandler(getLogOutHandler()).and()

				// COMMON

				.authorizeRequests().antMatchers("/api/**").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().authenticationEntryPoint(getAuthenticationEntryPoint()).and()
				.csrf();

		configureCsrf(c);
	}

	/***
	 * Allows to configure the csrf protection depending on the application profile
	 * and the application properties.
	 */
	private void configureCsrf(CsrfConfigurer<HttpSecurity> csrf) {
		if (enableCsrf) {
			csrf.ignoringAntMatchers(URL_LOGIN).ignoringAntMatchers(URL_LOGOUT).csrfTokenRepository(csrfTokenRepository)
					.and().addFilterAfter(csrfFilter, CsrfFilter.class);
		} else {
			csrf.disable();
		}
	}

	/**
	 * Configuration of the open web urls. <br>
	 * The configuration depend on the application profiles used.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		List<String> openUrls = new ArrayList<>();
		openUrls.addAll(Arrays.asList(URL_RESOURCES));
		openUrls.addAll(Arrays.asList(SWAGGER_RESOURCES));
		web.ignoring().antMatchers(URL_RESOURCES);
	}

	/**
	 * Configuration of the authentication provider. By default it use the Skeeper
	 * authentication provider. <br>
	 * The provider uses Skeeper rest api for user authentication.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	private AuthenticationSuccessHandler getSuccessHandler() {
		return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
			SecurityCsrfConfiguration.handleCsrf(request, response);

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
			SecurityCsrfConfiguration.handleCsrf(request, response);
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

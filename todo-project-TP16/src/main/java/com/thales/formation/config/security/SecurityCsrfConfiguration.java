package com.thales.formation.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * Management of the CSRF security protection.
 */
@Configuration
public class SecurityCsrfConfiguration {
	/**
	 * Name of the cookie sent to client
	 */
	private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
	/**
	 * Name of the request header parameter expected on request send by client
	 * application for post/put request
	 */
	private static final String CSRF_HEADER_NAME = "X-XSRF-TOKEN";

	/**
	 * Filter added to spring security configuration. It allows to add cookie on
	 * response for all resources.
	 */
	@Bean
	public OncePerRequestFilter csrfFilter() {
		return new OncePerRequestFilter() {

			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				handleCsrf(request, response);
				filterChain.doFilter(request, response);
			}
		};
	}

	/**
	 * Repository that allows to store expected header value for client
	 * sessions.
	 */
	@Bean
	public CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName(CSRF_HEADER_NAME);
		return repository;
	}
	
	public static void handleCsrf(HttpServletRequest request, HttpServletResponse response) {
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		if (csrf != null) {
			Cookie cookie = WebUtils.getCookie(request, CSRF_COOKIE_NAME);
			String token = csrf.getToken();
			if (cookie == null || token != null && !token.equals(cookie.getValue())) {
				cookie = new Cookie(CSRF_COOKIE_NAME, token);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
	}
}
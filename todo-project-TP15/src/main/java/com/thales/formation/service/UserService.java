package com.thales.formation.service;

import com.thales.formation.service.domain.AuthData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    public AuthData getCurrentAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new AuthData(
                authentication.getName(),
                authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
        );
    }
}

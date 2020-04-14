package com.example.demo.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("AuthorizedSecurity")
public class AuthorizedSecurity {

    public boolean hasRules(String ... roles){
        for (String role : roles) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            boolean hasRole = authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
            if(hasRole){
                return true;
            }
        }
        return false;
    }

}

package com.example.demo.security;

import com.example.demo.entities.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SecurityUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRol()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPasswordEncoder();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public SecurityUser getUser() {
        return user;
    }

    public void setUser(SecurityUser user) {
        this.user = user;
    }
}

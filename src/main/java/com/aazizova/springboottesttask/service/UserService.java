package com.aazizova.springboottesttask.service;

import com.aazizova.springboottesttask.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * User service.
 */
public interface UserService extends UserDetailsService {
    /**
     * Granted authorities.
     *
     * @param roles List<Role>
     *
     * @return List<GrantedAuthority>
     */
    List<GrantedAuthority> grantedAuthorities(List<Role> roles);
}

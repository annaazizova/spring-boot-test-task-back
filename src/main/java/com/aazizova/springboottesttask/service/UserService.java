package com.aazizova.springboottesttask.service;

import com.aazizova.springboottesttask.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<GrantedAuthority> getGrantedAuthorities(List<Role> roles);
}

package com.aazizova.springboottesttask.service.impl;

import com.aazizova.springboottesttask.model.dao.UserRepository;
import com.aazizova.springboottesttask.model.entity.CustomUser;
import com.aazizova.springboottesttask.model.entity.Role;
import com.aazizova.springboottesttask.model.entity.User;
import com.aazizova.springboottesttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUser(user);
    }

    @Override
    public List<GrantedAuthority> getGrantedAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}

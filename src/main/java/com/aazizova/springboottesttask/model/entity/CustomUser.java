package com.aazizova.springboottesttask.model.entity;

import com.aazizova.springboottesttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class CustomUser extends User {
    @Autowired
    static UserService userService;

    public CustomUser(com.aazizova.springboottesttask.model.entity.User user) {
        //super(user.getUsername(), user.getPassword(), userService.getGrantedAuthorities(user.getRoles()));
        super(user.getUsername(), user.getPassword(), new ArrayList<GrantedAuthority>(){{
            add(new SimpleGrantedAuthority("ROLE_USER"));
        }});
    }
}

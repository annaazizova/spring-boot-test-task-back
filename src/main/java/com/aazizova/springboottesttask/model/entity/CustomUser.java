package com.aazizova.springboottesttask.model.entity;

import com.aazizova.springboottesttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
    @Autowired
    static UserService userService;

    public CustomUser(com.aazizova.springboottesttask.model.entity.User user) {
        super(user.getUsername(), user.getPassword(), userService.getGrantedAuthorities(user.getRoles()));
    }
}

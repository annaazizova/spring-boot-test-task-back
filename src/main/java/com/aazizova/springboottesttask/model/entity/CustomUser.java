package com.aazizova.springboottesttask.model.entity;

import com.aazizova.springboottesttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

/**
 * Custom user.
 */
public class CustomUser extends User {
    /**
     * User service.
     *
     * @return UserService
     */
    @Autowired
    private static UserService userService;

    /**
     * Custom user.
     *
     */
    public CustomUser(final com.aazizova.springboottesttask.model.entity.User user) {
        //super(user.getUsername(),
        // user.getPassword(),
        // userService.grantedAuthorities(user.getRoles()));
        super(user.getUsername(),
                user.getPassword(),
                new ArrayList<GrantedAuthority>() { {
            add(new SimpleGrantedAuthority("ROLE_USER"));
        }});
    }
}

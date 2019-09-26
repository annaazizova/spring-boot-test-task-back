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

/**
 * User service impl.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * User repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Load user by name.
     *
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        User user = userRepository.userWithUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUser(user);
    }

    /**
     * Get granted authorities.
     *
     * @return List<GrantedAuthority>
     */
    @Override
    public List<GrantedAuthority> grantedAuthorities(final List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}

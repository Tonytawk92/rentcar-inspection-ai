package com.rentcar.app.security;

import com.rentcar.app.model.User;
import com.rentcar.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Implements Spring Security's {@link UserDetailsService} to load user-specific data.
 * This service is used by the authentication manager to retrieve user details from the database.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user by their username (in this case, email) from the database.
     * @param username The username (email) of the user to load.
     * @return A {@link UserDetails} object containing the user's information.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword_hash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}

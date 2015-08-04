package com.cykeromens.service.user.jpaImpl;

import com.cykeromens.model.user.CurrentUser;
import com.cykeromens.model.user.User;
import com.cykeromens.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by omens on 7/21/15.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService{


    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));

//        User user = userService.getUserByEmail("cykeromens@gmail.com").get();
//        System.out.println("User info: "+user.toString());

        return new CurrentUser(user);
    }

}

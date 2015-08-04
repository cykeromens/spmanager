package com.cykeromens.model.user;


import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by omens on 8/1/15.
 */

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getContactDetails().getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Enum getRole() {
        return user.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" + "user=" + user + "} " + super.toString();
    }
}

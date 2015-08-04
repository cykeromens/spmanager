package com.cykeromens.service.user;

import com.cykeromens.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by omens on 7/18/15.
 */
public interface UserService {

    Optional<User> findUserById(Long id);;
    void deleteUser(User user);
    Optional<User> findByUsername(String username);
    Optional<User> getUserByEmail(String email);

}

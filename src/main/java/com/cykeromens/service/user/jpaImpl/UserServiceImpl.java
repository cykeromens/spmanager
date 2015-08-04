package com.cykeromens.service.user.jpaImpl;

import com.cykeromens.model.user.User;
import com.cykeromens.repository.user.UserRepository;
import com.cykeromens.service.user.UserService;
import com.cykeromens.web.form.UserCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Optional;

/**
 * Created by omens on 7/18/15.
 */
@Service("userService")
public abstract class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    @Override @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


}

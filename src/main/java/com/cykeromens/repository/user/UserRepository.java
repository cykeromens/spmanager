package com.cykeromens.repository.user;

import com.cykeromens.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by omens on 8/1/15.
 */
public interface UserRepository extends JpaRepository<User,Long>{

    @Query("SELECT c FROM  User c WHERE c.contactDetails.email=:email")
    public Optional<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT c FROM  User c WHERE c.username=:username")
    public Optional<User> findUserByUsername(@Param("username") String username);
}

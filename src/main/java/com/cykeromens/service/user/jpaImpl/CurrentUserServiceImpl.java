package com.cykeromens.service.user.jpaImpl;

import com.cykeromens.model.user.CurrentUser;
import com.cykeromens.service.user.CurrentUserService;
import org.springframework.stereotype.Service;

/**
 * Created by omens on 7/21/15.
 */

@Service
public class CurrentUserServiceImpl implements CurrentUserService{

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        //LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == currentUser.getRole() || currentUser.getId().equals(userId));
    }
}
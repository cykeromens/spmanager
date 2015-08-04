package com.cykeromens.service.user;

import com.cykeromens.model.user.CurrentUser;

/**
 * Created by omens on 7/21/15.
 */
public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
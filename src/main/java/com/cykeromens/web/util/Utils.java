package com.cykeromens.web.util;

import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by omens on 7/18/15.
 */
public class Utils {
    private static final java.util.Random RANDOM = new java.util.Random();

    // Returns random integer number from 1 to max

    public static int random(int max) {
        int randomInt = (int) (RANDOM.nextDouble() * max);
        return randomInt + 1;
    }

    public static String getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }else{
            return null;
        }
    }

    public static Sort sortBy(String sort){
        return new Sort(Sort.Direction.DESC,sort );
    }


}

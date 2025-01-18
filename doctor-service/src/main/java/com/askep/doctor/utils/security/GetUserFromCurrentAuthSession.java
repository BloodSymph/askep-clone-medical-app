package com.askep.doctor.utils.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class GetUserFromCurrentAuthSession {

    public static String getUserEmailFromCurrentSession() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }

        return null;

    }

}

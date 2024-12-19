package com.askep.doctor.utils.security;


import io.jsonwebtoken.Claims;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class AuthoritiesConverter {

    public static Collection<? extends GrantedAuthority> getAuthorities(Collection<Claims> claims) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Claims role: claims ) {
            authorities.add(
                    new SimpleGrantedAuthority((role.get("roles").toString()))
            );
        }

        return authorities;
    }

}

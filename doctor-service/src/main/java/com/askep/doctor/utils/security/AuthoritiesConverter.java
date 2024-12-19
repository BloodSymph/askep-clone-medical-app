package com.askep.auth.utils.security;

import com.askep.auth.entity.RoleEntity;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class AuthoritiesConverter {

    public static Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleEntity> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role: roles) {
            authorities.add(
                    new SimpleGrantedAuthority(role.getName())
            );
        }

        return authorities;
    }

}

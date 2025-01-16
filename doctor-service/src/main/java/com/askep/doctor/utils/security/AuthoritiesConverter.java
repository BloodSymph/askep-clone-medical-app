package com.askep.doctor.utils.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsMutator;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class AuthoritiesConverter {

    private List<GrantedAuthority> grantedAuthorities;

    public static Collection<? extends GrantedAuthority> getAuthorities(Claims claims) {

        Object claim = claims.get("roles");

        List<?> listAuthorities = (ArrayList<?>) claim;


        return grantedAuthorities;

    }

}

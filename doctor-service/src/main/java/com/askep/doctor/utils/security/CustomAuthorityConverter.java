package com.askep.doctor.utils.security;

import io.jsonwebtoken.Claims;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@UtilityClass
public class CustomAuthorityConverter {

    public static List<? extends GrantedAuthority> customAuthorityConverter(Claims claims) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Object objectClaim = claims.get("roles");
        List<?> claimList = ((ArrayList<?>) objectClaim);
        for (int i = 0; i < claimList.size(); i++) {
            var linkedHashMap = (LinkedHashMap<String, String>) claimList.get(i);
            grantedAuthorities.add(
                    new SimpleGrantedAuthority(linkedHashMap.get("authority"))
            );
        }
        return grantedAuthorities;
    }

}

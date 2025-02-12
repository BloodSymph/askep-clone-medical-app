package com.askep.medpersonal.utils.security;

import io.jsonwebtoken.Claims;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class CustomAuthorityConverter {

    public static List<? extends GrantedAuthority> customAuthorityConverter(Claims claims) {

        return ((List<?>) claims.get("roles")).stream()
                .map(claim -> (LinkedHashMap<String, String>) claim)
                .map(linkedHashMap -> new SimpleGrantedAuthority(
                        linkedHashMap.get("authority"))
                )
                .collect(Collectors.toList());
    }

}

package fr.doodoodle.server.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    //TODO plug creation of a JWT user using User object
    public static JwtUser create() {
        return new JwtUser(
                1l,
                "Glunt",
                "Thiebaud",
                "Schwindenhammer",
                "ett",
                mapToGrantedAuthorities(),
                true,
                new Date()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authList;
    }
}

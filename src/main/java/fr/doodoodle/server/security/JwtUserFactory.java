package fr.doodoodle.server.security;

import fr.doodoodle.server.db.model.UserPE;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserPE aUser) {
        return new JwtUser(
                aUser.getId(),
                aUser.getUsername(),
                aUser.getFirstName(),
                aUser.getLastName(),
                aUser.getPassword(),
                mapToGrantedAuthorities(),
                true,
                aUser.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authList;
    }
}

package fr.doodoodle.server.security.service;

import fr.doodoodle.server.security.JwtUser;
import lombok.Getter;

import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
@Getter
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final JwtUser user;

    public JwtAuthenticationResponse(String token, JwtUser user) {
        this.token = token;
        this.user = user;
    }
}

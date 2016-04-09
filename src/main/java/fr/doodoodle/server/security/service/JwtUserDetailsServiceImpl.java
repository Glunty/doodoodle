package fr.doodoodle.server.security.service;

import fr.doodoodle.server.security.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO plug search user DAO and call JwtUserFactory with it

        //if (user == null) {
          //  throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        //} else {
            return JwtUserFactory.create();
        //}
    }
}

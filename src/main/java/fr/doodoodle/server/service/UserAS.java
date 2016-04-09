package fr.doodoodle.server.service;

import fr.doodoodle.server.db.business.UserRepository;
import fr.doodoodle.server.db.model.UserPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Florent on 09/04/2016.
 */
@Service
public class UserAS {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserPE createUser(String email, String password){
        UserPE user = new UserPE();
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}

package fr.doodoodle.server.service;

import fr.doodoodle.server.db.business.UserRepository;
import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Florent on 09/04/2016.
 */
@Service
public class UserAS {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserPE createUser(UserPE user){
        //Search if an existing user has the same email
        UserPE existing = userRepository.findFirstByEmail(user.getEmail());
        if (existing != null){
            throw new UserAlreadyExistsException("User with email "+user.getEmail() + " already exists");
        }

        //We create a new object, taking only authorized fields
        UserPE newUser = new UserPE();
        newUser.setUsername(user.getEmail());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setLastPasswordResetDate(new Date());
        newUser.setAccountNotExpired(true);

        return userRepository.save(newUser);
    }
}

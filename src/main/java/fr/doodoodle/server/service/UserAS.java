package fr.doodoodle.server.service;

import com.google.api.client.util.Lists;
import fr.doodoodle.server.db.business.UserRepository;
import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.service.exception.EntityNotFoundException;
import fr.doodoodle.server.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserAS {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAS(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserPE createUser(UserPE user) {
        //Search if an existing user has the same email
        UserPE existing = userRepository.findFirstByEmail(user.getEmail());
        if (existing != null) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
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

    public List<UserPE> findByExample(UserPE example) {
        if (example.getEmail() != null) {
            return userRepository.findWithMailLike(example.getEmail());
        }
        if (example.getFirstName() != null) {
            return userRepository.listByFirstAndLastName(example.getFirstName(), example.getLastName());
        } else {
            return Lists.newArrayList();
        }
    }

    public UserPE findByUsername(String username) {
        UserPE foundUser = userRepository.findFirstByUsername(username);
        if (foundUser == null) {
            throw new EntityNotFoundException("User with username " + username + " not found");
        }
        return foundUser;
    }
}

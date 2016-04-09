package service;

import db.business.UserRepository;
import db.model.UserPE;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Florent on 09/04/2016.
 */
public class UserAS {
    @Autowired
    private UserRepository userRepository;

    public UserPE createUser(String name){
        UserPE user = new UserPE();
        user.setName(name);
        return userRepository.save(user);
    }
}

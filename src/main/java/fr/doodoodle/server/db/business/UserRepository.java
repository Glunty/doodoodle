package fr.doodoodle.server.db.business;

import fr.doodoodle.server.db.model.UserPE;
import org.springframework.data.repository.Repository;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Florent on 09/04/2016.
 */
public interface UserRepository extends Repository<UserPE, String> {
        UserPE save(UserPE user);

        UserPE findFirstByEmail(String email);
}

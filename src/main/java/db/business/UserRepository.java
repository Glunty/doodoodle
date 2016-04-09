package db.business;

import db.model.UserPE;
import org.springframework.data.repository.Repository;

/**
 * Created by Florent on 09/04/2016.
 */
public interface UserRepository extends Repository<UserPE, Long> {
        UserPE save(UserPE user);
}

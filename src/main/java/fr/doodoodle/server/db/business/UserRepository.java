package fr.doodoodle.server.db.business;

import fr.doodoodle.server.db.model.UserPE;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Florent on 09/04/2016.
 */
@Repository
public interface UserRepository extends CrudRepository<UserPE, String> {
    UserPE findFirstByEmail(String email);
    UserPE findFirstByUsername(String username);

    @Query("{ 'email' : { '$regex' : '.*?0.*' , '$options' : 'i'}}")
    List<UserPE> findWithMailLike(String mail);

    @Query("{ 'firstName' : ?0, 'lastName' : ?1 }")
    List<UserPE> listByFirstAndLastName(String firstName, String lastName);
}

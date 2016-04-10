package fr.doodoodle.server.db.business;

import fr.doodoodle.server.db.model.GroupPE;
import fr.doodoodle.server.db.model.UserPE;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Florent on 09/04/2016.
 */
public interface GroupRepository extends CrudRepository<GroupPE, String> {
    //

    @Query("{members:?0}")
    List<GroupPE> listByUserId(String userId);
}

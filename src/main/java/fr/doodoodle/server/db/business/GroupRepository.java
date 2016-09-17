package fr.doodoodle.server.db.business;

import fr.doodoodle.server.db.model.GroupPE;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Florent on 09/04/2016.
 */
@Repository
public interface GroupRepository extends CrudRepository<GroupPE, String> {
    //

    @Query("{memberIds:?0}")
    List<GroupPE> listByUserId(String userId);
}

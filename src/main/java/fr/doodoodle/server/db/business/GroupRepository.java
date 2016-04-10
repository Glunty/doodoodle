package fr.doodoodle.server.db.business;

import fr.doodoodle.server.db.model.GroupPE;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Florent on 09/04/2016.
 */
public interface GroupRepository extends CrudRepository<GroupPE, String> {
        //
}

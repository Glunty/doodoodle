package fr.doodoodle.server.db.business;

import fr.doodoodle.server.db.model.GroupPE;
import fr.doodoodle.server.db.model.UserPE;
import org.springframework.data.repository.Repository;

/**
 * Created by Florent on 09/04/2016.
 */
public interface GroupRepository extends Repository<GroupPE, String> {
        GroupPE save(GroupPE groupPE);

        GroupPE delete(GroupPE groupPE);

        GroupPE findUniqueById(String id);
}

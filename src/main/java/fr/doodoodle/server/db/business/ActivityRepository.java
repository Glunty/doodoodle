package fr.doodoodle.server.db.business;

import fr.doodoodle.server.db.model.ActivityPE;
import org.springframework.data.repository.Repository;

/**
 * Created by Florent on 10/04/2016.
 */
public interface ActivityRepository extends Repository<ActivityPE, String> {
    void save(ActivityPE activityPE);
}

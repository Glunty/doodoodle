package fr.doodoodle.server.service;

import com.google.api.client.util.Strings;
import fr.doodoodle.server.db.business.ActivityRepository;
import fr.doodoodle.server.db.model.ActivityPE;
import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Florent on 09/04/2016.
 */
@Service
public class ActivityAS {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserAS userAS;

    public void createActivity(ActivityPE activityPE){
        //if activity does not have an activity group id, we generate one
        if(Strings.isNullOrEmpty(activityPE.getActivityGroupUuid())) {
            activityPE.setActivityGroupUuid(UUID.randomUUID().toString());
        }
        UserPE user = userAS.findByID(activityPE.getOwnerID());
        user.getActivities().add(activityPE.getId());

        //we check that the user creating this activity exist, and we add to it this activity
        if(userAS.updateUser(user) != null) {
            activityRepository.save(activityPE);
        } else {
            throw new EntityNotFoundException("User with id :"+activityPE.getOwnerID()+" not found");
        }
    }
}

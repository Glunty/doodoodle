package fr.doodoodle.server.service;

import fr.doodoodle.server.db.business.ActivityRepository;
import fr.doodoodle.server.db.model.ActivityPE;
import fr.doodoodle.server.util.StringUtils;
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
        if(StringUtils.isNullOrEmpty(activityPE.getActivityGroupUuid())) {
            activityPE.setActivityGroupUuid(UUID.randomUUID().toString());
        }
        activityRepository.save(activityPE);
    }
}

package fr.doodoodle.server.service;

import fr.doodoodle.server.db.business.ActivityRepository;
import fr.doodoodle.server.db.business.UserRepository;
import fr.doodoodle.server.db.model.ActivityPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Florent on 09/04/2016.
 */
@Service
public class ActivityAS {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserAS userAS;

    public void createActivity(){
        ActivityPE getAllActivitiesForUser(String email){
            //userAS.
        }
    }
}

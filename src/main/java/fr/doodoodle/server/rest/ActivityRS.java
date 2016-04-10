package fr.doodoodle.server.rest;

import fr.doodoodle.server.db.model.ActivityPE;
import fr.doodoodle.server.service.ActivityAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Florent on 10/04/2016.
 */
@RestController
@RequestMapping("/activity")
public class ActivityRS {
    @Autowired
    ActivityAS activityAS;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void create(@RequestBody ActivityPE activity){
        activityAS.createActivity(activity);
    }
}

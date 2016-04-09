package fr.doodoodle.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import fr.doodoodle.server.service.UserAS;

/**
 * Created by Florent on 09/04/2016.
 */
@RestController
@RequestMapping("/user")
public class UserRS {
    @Autowired
    private UserAS userAS;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam(value="name") String name) {
        userAS.createUser(name);
    }
}

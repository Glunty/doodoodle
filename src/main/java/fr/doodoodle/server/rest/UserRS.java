package fr.doodoodle.server.rest;

import org.apache.http.HttpResponse;
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

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam(value="email") String email, @RequestParam(value="password") String password) {
        userAS.createUser(email, password);
    }

}

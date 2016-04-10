package fr.doodoodle.server.rest;

import fr.doodoodle.server.db.model.UserPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public
    @ResponseBody
    void create(@RequestBody UserPE user) {
        userAS.createUser(user);
    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    List<UserPE> findUser(@RequestBody UserPE example) {
        return userAS.findByExample(example);
    }

}

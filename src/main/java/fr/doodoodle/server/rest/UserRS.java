package fr.doodoodle.server.rest;

import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.service.UserAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRS {

    private final UserAS userAS;

    @Autowired
    public UserRS(UserAS userAS) {
        this.userAS = userAS;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public
    @ResponseBody
    void create(@RequestBody UserPE user) {
        userAS.createUser(user);
    }

    @RequestMapping(path = "/find", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    List<UserPE> find(@RequestBody UserPE example) {
        return userAS.findByExample(example);
    }

}

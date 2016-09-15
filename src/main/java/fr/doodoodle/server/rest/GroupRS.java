package fr.doodoodle.server.rest;

import fr.doodoodle.server.db.business.GroupRepository;
import fr.doodoodle.server.db.model.GroupPE;
import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.security.JwtTokenUtil;
import fr.doodoodle.server.service.GroupAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Thiebaud on 10/04/2016.
 */
@RestController
@RequestMapping("/group")
public class GroupRS {

    @Autowired
    private GroupAS groupAS;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    void create(@RequestBody GroupPE groupPE, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        groupAS.createGroup(groupPE, username);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<GroupPE> listAll() {
        return groupAS.listAll();
    }

    @RequestMapping(path = "/{groupId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    GroupPE findByGroupId(@PathVariable String groupId) {
        return groupAS.findById(groupId);
    }

    @RequestMapping(path = "/findByUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<GroupPE> findByUserId(@RequestBody UserPE user) {
        return groupAS.listByUserId(user.getId());
    }


    @RequestMapping(path = "/{groupId}/addUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    void addUserToGroup(@PathVariable String groupId, @RequestBody UserPE user) {
        groupAS.addUserToGroup(groupId, user.getId());
    }

    @RequestMapping(path = "/{groupId}/removeUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    //authorize only a given user to remove itself
    //@PreAuthorize("authentication.principal.equals(#user.getEmail())")
    public
    @ResponseBody
    void removeUserFromGroup(@PathVariable String groupId, @RequestBody UserPE user) {
        groupAS.removeUserFromGroup(groupId, user.getId());
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeCurrentUserFromGroup(@PathVariable String groupId, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //FIXME TSR Username provided where userId is expected => cannot work
        groupAS.removeUserFromGroup(groupId, username);
    }
}

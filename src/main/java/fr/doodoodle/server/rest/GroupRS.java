package fr.doodoodle.server.rest;

import fr.doodoodle.server.db.business.GroupRepository;
import fr.doodoodle.server.db.model.GroupPE;
import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.security.JwtTokenUtil;
import fr.doodoodle.server.service.GroupAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.acl.Group;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupRS {

    private final GroupAS groupAS;

    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    public GroupRS(GroupRepository groupRepository, GroupAS groupAS, JwtTokenUtil jwtTokenUtil) {
        this.groupAS = groupAS;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void create(@RequestBody GroupPE groupPE, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        groupAS.createUserGroup(username, groupPE);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<GroupPE> find(@RequestParam(value = "username", required = false) String username,
                                 HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        return groupAS.listByUsername(username == null ? jwtTokenUtil.getUsernameFromToken(token) : username);
    }

    @RequestMapping(path = "/{groupId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GroupPE findById(@PathVariable String groupId) {
        return groupAS.findById(groupId);
    }

    @RequestMapping(path = "/{groupId}/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GroupPE addUser(@PathVariable String groupId, @RequestBody UserPE user) {
        return groupAS.addUserToGroup(groupId, user.getUsername());
    }

    @RequestMapping(path = "/{groupId}/user/{username:.+}", method = RequestMethod.DELETE)
    //authorize only a given user to remove itself
    //@PreAuthorize("authentication.principal.equals(#user.getEmail())")
    public ResponseEntity<GroupPE> removeUser(@PathVariable String groupId, @PathVariable String username) {
        final Optional<GroupPE> group = groupAS.removeUserFromGroup(groupId, username);
        if(group.isPresent()) {
            return new ResponseEntity<>(group.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCurrentUser(@PathVariable String groupId, HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        groupAS.removeUserFromGroup(groupId, username);
    }
}

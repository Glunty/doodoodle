package fr.doodoodle.server.rest;

import fr.doodoodle.server.db.business.GroupRepository;
import fr.doodoodle.server.db.model.GroupPE;
import fr.doodoodle.server.db.model.UserPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Thiebaud on 10/04/2016.
 */
@RestController
@RequestMapping("/group")
public class GroupRS {

    @Autowired
    private GroupRepository groupRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    void create(@RequestBody GroupPE groupPE) {
        groupRepository.save(groupPE);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void delete(@RequestBody GroupPE groupPE) {
        groupRepository.delete(groupPE);
    }

    @RequestMapping(path = "/{groupId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    GroupPE findByGroupId(@PathVariable String groupId) {
        return groupRepository.findUniqueById(groupId);
    }

    @RequestMapping(path = "/{groupId}/addUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void addUserToGroup(@PathVariable String groupId, @RequestBody UserPE user) {
        GroupPE group = groupRepository.findUniqueById(groupId);
        if (group != null){
            group.getMembers().add(user.getId());
        }
        groupRepository.save(group);
    }
}

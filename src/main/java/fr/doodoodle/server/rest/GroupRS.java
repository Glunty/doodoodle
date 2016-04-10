package fr.doodoodle.server.rest;

import fr.doodoodle.server.db.business.GroupRepository;
import fr.doodoodle.server.db.business.UserRepository;
import fr.doodoodle.server.db.model.GroupPE;
import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.service.exception.EntityNotFoundException;
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

    @Autowired
    private UserRepository userRepository;

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
        return groupRepository.findOneById(groupId);
    }

    @RequestMapping(path = "/{groupId}/addUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void addUserToGroup(@PathVariable String groupId, @RequestBody UserPE user) {
        GroupPE foundGroup = groupRepository.findOneById(groupId);
        UserPE foundUser = userRepository.findOneById(user.getId());
        if (foundGroup == null){
            throw new EntityNotFoundException("Group with id "+ groupId+ " not found");
        }
        if (foundUser == null){
            throw new EntityNotFoundException("User with id "+ user.getId()+ " not found");
        }
        if (foundGroup != null && foundUser!= null){
            foundGroup.getMembers().add(foundUser.getId());
            foundUser.getGroups().add(foundGroup.getId());
            groupRepository.save(foundGroup);
            userRepository.save(foundUser);
        }
    }

    @RequestMapping(path = "/{groupId}/removeUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void removeUserFromGroup(@PathVariable String groupId, @RequestBody UserPE user) {
        GroupPE foundGroup = groupRepository.findOneById(groupId);
        UserPE foundUser = userRepository.findOneById(user.getId());
        if (foundGroup == null){
            throw new EntityNotFoundException("Group with id "+ groupId+ " not found");
        }
        if (foundUser == null){
            throw new EntityNotFoundException("User with id "+ user.getId()+ " not found");
        }
        if (foundGroup != null && foundUser!= null){
            foundGroup.getMembers().remove(foundUser.getId());
            foundUser.getGroups().remove(foundGroup.getId());
            groupRepository.save(foundGroup);
            userRepository.save(foundUser);
        }
    }
}

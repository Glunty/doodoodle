package fr.doodoodle.server.service;

import com.google.api.client.util.Lists;
import fr.doodoodle.server.db.business.GroupRepository;
import fr.doodoodle.server.db.business.UserRepository;
import fr.doodoodle.server.db.model.GroupPE;
import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupAS {

    private final GroupRepository groupRepository;

    private final UserRepository userRepository;

    private final UserAS userService;

    @Autowired
    public GroupAS(GroupRepository groupRepository, UserRepository userRepository, UserAS userService) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void createUserGroup(String username, GroupPE groupPE) {
        UserPE foundUser = userService.findByUsername(username);
        groupPE.getMemberIds().add(foundUser.getId());
        groupRepository.save(groupPE);
    }

    public void removeUserFromGroup(String groupId, String username) {
        GroupPE foundGroup = groupRepository.findOne(groupId);
        UserPE foundUser = userService.findByUsername(username);
        if (foundGroup == null) {
            throw new EntityNotFoundException("Group with id " + groupId + " not found");
        }
        if (foundUser == null) {
            throw new EntityNotFoundException("User with id " + username + " not found");
        }
        foundGroup.getMemberIds().remove(foundUser.getId());
        foundUser.getGroups().remove(foundGroup.getId());

        userRepository.save(foundUser);
        if (foundGroup.getMemberIds().isEmpty()) {
            //Delete group if last member has quit
            groupRepository.delete(foundGroup);
        } else {
            groupRepository.save(foundGroup);
        }
    }

    public void addUserToGroup(String groupId, String username) {
        GroupPE foundGroup = findGroup(groupId);
        UserPE foundUser = userService.findByUsername(username);
        foundGroup.getMemberIds().add(foundUser.getId());
        foundUser.getGroups().add(foundGroup.getId());
        groupRepository.save(foundGroup);
        userRepository.save(foundUser);
    }


    public GroupPE findById(String groupId) {
        GroupPE group = groupRepository.findOne(groupId);
        populateMembers(group);
        return group;
    }

    public List<GroupPE> listByUsername(String username) {
        UserPE foundUser = userService.findByUsername(username);
        List<GroupPE> groups = groupRepository.listByUserId(foundUser.getId());
        groups.forEach(this::populateMembers);
        return groups;
    }

    public List<GroupPE> listAll() {
        Iterable<GroupPE> groups = groupRepository.findAll();
        groups.forEach(this::populateMembers);
        return Lists.newArrayList(groups);
    }

    private GroupPE findGroup(String groupId) {
        GroupPE foundGroup = groupRepository.findOne(groupId);
        if (foundGroup == null) {
            throw new EntityNotFoundException("Group with id " + groupId + " not found");
        }
        return foundGroup;
    }

    private void populateMembers(GroupPE groupPE) {
        groupPE.getMemberIds().forEach(s -> groupPE.getMembers().add(userRepository.findOne(s)));
    }
}

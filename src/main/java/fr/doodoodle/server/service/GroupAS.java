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


/**
 * Created by Thiebaud on 10/04/2016.
 */
@Service
public class GroupAS {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public void removeUserFromGroup(String groupId, String userId) {
        GroupPE foundGroup = groupRepository.findOne(groupId);
        UserPE foundUser = userRepository.findOne(userId);
        if (foundGroup == null) {
            throw new EntityNotFoundException("Group with id " + groupId + " not found");
        }
        if (foundUser == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
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

    public void addUserToGroup(String groupId, String userId) {
        GroupPE foundGroup = findGroup(groupId);
        UserPE foundUser = findUser(userId);
        foundGroup.getMemberIds().add(foundUser.getId());
        foundUser.getGroups().add(foundGroup.getId());
        groupRepository.save(foundGroup);
        userRepository.save(foundUser);
    }
    
	/**
	 * A new group is created by a user
	 * User is added to the group
	 * @param groupPE
	 * @param username
	 */
	public void createGroup(GroupPE groupPE, String username) {
		UserPE user = userRepository.findByUsername(username);
		groupPE.getMemberIds().add(user.getId());
		groupRepository.save(groupPE);
	}


    public GroupPE findById(String groupId) {
        GroupPE group = groupRepository.findOne(groupId);
        populateMembers(group);
        return group;
    }

    public List<GroupPE> listByUserId(String id) {
        List<GroupPE> groups = groupRepository.listByUserId(id);
        groups.forEach(s -> populateMembers(s));
        return groups;
    }

    public List<GroupPE> listAll() {
        Iterable<GroupPE> groups = groupRepository.findAll();
        groups.forEach(s -> populateMembers(s));
        return Lists.newArrayList(groups);
    }

    private UserPE findUser(String userId) {
        UserPE foundUser = userRepository.findOne(userId);
        if (foundUser == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }
        return foundUser;
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

package fr.doodoodle.server.service;

import com.google.api.client.util.Lists;
import fr.doodoodle.server.db.business.GroupRepository;
import fr.doodoodle.server.db.business.UserRepository;
import fr.doodoodle.server.db.model.GroupPE;
import fr.doodoodle.server.db.model.UserPE;
import fr.doodoodle.server.rest.to.GroupTO;
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

    @Autowired
    private UserAS userAS;

    public void removeUserFromGroup(String groupId, String userId){
        GroupPE foundGroup = groupRepository.findOne(groupId);
        UserPE foundUser = userRepository.findOne(userId);
        if (foundGroup == null) {
            throw new EntityNotFoundException("Group with id " + groupId + " not found");
        }
        if (foundUser == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }
            foundGroup.getMembers().remove(foundUser.getId());
            foundUser.getGroups().remove(foundGroup.getId());

            userRepository.save(foundUser);
            if (foundGroup.getMembers().isEmpty()){
                //Delete group if last member has quit
                groupRepository.delete(foundGroup);
            } else {
                groupRepository.save(foundGroup);
            }
    }

    public void addUserToGroup(String groupId, String userId){
        GroupPE foundGroup = findGroup(groupId);
        UserPE foundUser = findUser(userId);
            foundGroup.getMembers().add(foundUser.getId());
            foundUser.getGroups().add(foundGroup.getId());
            groupRepository.save(foundGroup);
            userRepository.save(foundUser);
    }


    public GroupTO findById(String groupId) {
        return mapToTO(groupRepository.findOne(groupId));
    }

    public List<GroupTO> listByUserId(String id) {
        return mapToTO(groupRepository.listByUserId(id));
    }

    public List<GroupTO> listAll() {
        return mapToTO(groupRepository.findAll());
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

    private List<GroupTO> mapToTO(Iterable<GroupPE> groupPEs){
        List<GroupTO> tos = Lists.newArrayList();
        groupPEs.forEach(s-> tos.add(mapToTO(s)));
        return tos;
    }

    private List<GroupTO> mapToTO(List<GroupPE> groupPEs){
        List<GroupTO> tos = Lists.newArrayList();
        groupPEs.stream().forEach(s-> tos.add(mapToTO(s)));
        return tos;
    }
    private GroupTO mapToTO(GroupPE groupPE){
        GroupTO groupTO = new GroupTO();
        groupTO.setName(groupPE.getName());
        groupTO.setId(groupPE.getId());
        populateMembers(groupPE, groupTO);
        return groupTO;
    }

    private void populateMembers(GroupPE groupPE, GroupTO groupTO){
        groupPE.getMembers().forEach(s->groupTO.getMembers().add(userAS.mapToTO(userRepository.findOne(s))));
    }
}

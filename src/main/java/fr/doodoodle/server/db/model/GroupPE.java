package fr.doodoodle.server.db.model;

import com.google.api.client.util.Lists;
import com.google.api.client.util.Sets;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Florent on 09/04/2016.
 */
@Document(collection = "groups")
@Data
public class GroupPE implements Serializable {
    @Id
    private String id;
    //Name of user group
    private String name;
    //Reference a list of user id
    private Set<String> memberIds;

    @Transient
    private List<UserPE> members;

    public Set<String> getMemberIds() {
        if (memberIds == null) {
            memberIds = Sets.newHashSet();
        }
        return memberIds;
    }

    @Transient
    public List<UserPE> getMembers() {
        if (members == null) {
            members = Lists.newArrayList();
        }
        return members;
    }
}

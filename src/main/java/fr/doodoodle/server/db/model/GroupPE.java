package fr.doodoodle.server.db.model;

import com.google.api.client.util.Lists;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

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
    private List<String> members;
    private List<ActivityTypePE> activityTypes;

    public List<ActivityTypePE> getActivityTypes(){
        if(activityTypes == null){
            activityTypes = Lists.newArrayList();
        }
        return activityTypes;
    }

    public List<String> getMembers() {
        if (members == null) {
            members = Lists.newArrayList();
        }
        return members;
    }
}

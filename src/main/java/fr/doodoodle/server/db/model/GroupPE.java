package fr.doodoodle.server.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Florent on 09/04/2016.
 */
@Document(collection = "groups")
@Data
public class GroupPE {
    @Id
    private String id;
    //Reference a list of user id
    private List<String> members;
}

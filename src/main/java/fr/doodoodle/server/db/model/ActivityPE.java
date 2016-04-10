package fr.doodoodle.server.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Period;
import java.util.List;
import java.util.UUID;

/**
 * Created by Florent on 09/04/2016.
 */
@Document(collection = "activities")
@Data
public class ActivityPE implements Serializable{
    private static final long serialVersionUID = -8053917972482025845L;
    @Id
    String id;
    private Period period;
    private String name;
    private ActivityTypePE activityType;
    private String ownerID;
    private List<String> subscribersID;
    private String activityGroupUuid;
}

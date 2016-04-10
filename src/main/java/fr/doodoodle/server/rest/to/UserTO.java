package fr.doodoodle.server.rest.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.Lists;
import fr.doodoodle.server.db.model.ActivityPE;
import fr.doodoodle.server.db.model.DisponibilityPE;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Thiebaud on 10/04/2016.
 */
@Data
public class UserTO implements Serializable {
    private String id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean accountNotExpired;
    private Date lastPasswordResetDate;

    private List<GroupTO> groups;

}

package fr.doodoodle.server.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.Lists;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Florent on 09/04/2016.
 * Collection representing users
 */
@Document(collection = "users")
@Data
public class UserPE implements Serializable {
    @Id
    private String id;

    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean accountNotExpired;
    private Date lastPasswordResetDate;

    private List<String> groups;
    private List<ActivityPE> activities;
    private List<DisponibilityPE> disponibilities;


    public List<String> getGroups() {
        if (groups == null) {
            groups = Lists.newArrayList();
        }
        return groups;
    }
}

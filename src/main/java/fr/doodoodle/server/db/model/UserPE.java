package fr.doodoodle.server.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * Created by Florent on 09/04/2016.
 * Collection representing users
 */
@Document(collection = "users")
@Data
public class UserPE  {
    @Id
    private String id;
    private String name;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean accountNotExpired;
    private Date lastPasswordResetDate;

    private List<Long> groups;
    private List<Long> activities;
}

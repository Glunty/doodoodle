package db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Florent on 09/04/2016.
 * Collection representing users
 */
@Document(collection = "users")
@Data
public class UserPE {
    @Id
    private Long id;
    private String name;
    private List<Long> groups;
    private List<Long> activities;
}

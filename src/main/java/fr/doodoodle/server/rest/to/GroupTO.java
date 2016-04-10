package fr.doodoodle.server.rest.to;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Thiebaud on 10/04/2016.
 */
@Data
public class GroupTO implements Serializable{
    private String id;
    //Name of user group
    private String name;
    //Reference a list of user id
    private List<UserTO> members;
}

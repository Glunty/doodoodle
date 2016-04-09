package fr.doodoodle.server.db.model;

import lombok.Data;

import java.time.Period;

/**
 * Created by Florent on 09/04/2016.
 */
@Data
public class ActivityPE {
    private Period period;
    private String name;
    private String Type;
}

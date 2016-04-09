package fr.doodoodle.server.db.model;

import lombok.Data;

import java.time.Period;
import java.util.List;

/**
 * Created by Florent on 09/04/2016.
 */
@Data
public class DisponibilityPE {
    Period period;
    List<ActivityTypePE> activityTypes;
}

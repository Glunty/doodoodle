package fr.doodoodle.server.db.model;

import lombok.Data;

import java.time.Period;
import java.util.List;

@Data
public class DisponibilityPE {
    Period period;
    List<ActivityTypePE> activityTypes;
}

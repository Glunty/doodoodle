package fr.doodoodle.server.db.model;

import lombok.Data;

import java.time.Period;

@Data
public class ActivityPE {
    private Period period;
    private String name;
    private ActivityTypePE activityType;
}

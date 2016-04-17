package de.jehn.sarah.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by sarahjehn on 05.03.16.
 */

public class Goal {

    private final UUID uuid;

    private final String name;

    private final String user;

    private final LocalDateTime createdAt;

    private final int goalWeek;

    public Goal (UUID uuid, String name, String user, LocalDateTime createdAt, int goalWeek){
        this.uuid = uuid;
        this.name = name;
        this.user = user;
        this.createdAt = createdAt;
        this.goalWeek = goalWeek;
    }

}


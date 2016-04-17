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

    private final String createdAt;

    private final int goalWeek;

    private final Boolean done;

    public Goal (UUID uuid, String name, String user, String createdAt, int goalWeek, Boolean done){
        this.uuid = uuid;
        this.name = name;
        this.user = user;
        this.createdAt = createdAt;
        this.goalWeek = goalWeek;
        this.done = done;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getGoalWeek() {
        return goalWeek;
    }

    public Boolean getDone() {return done;}
}


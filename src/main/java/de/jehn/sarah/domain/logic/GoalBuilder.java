package de.jehn.sarah.domain.logic;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.model.Goal;
import flowctrl.integration.slack.type.Message;
import flowctrl.integration.slack.type.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Created by sarahjehn on 19.03.16.
 */
public class GoalBuilder {

    public static Goal buildGoal(Message message){

        UUID uuid = UUID.randomUUID();
        String name = message.getText();
        String user = message.getUser();
        LocalDateTime createdAt = LocalDateTime.now();
        int goalWeek = Integer.parseInt(createdAt.format(DateTimeFormatter.ofPattern("ww")));

        return new Goal(uuid, name, user, createdAt, goalWeek);
    }

    public static Goal buildGoal(JsonNode event){
        UUID uuid = UUID.randomUUID();
        String name = event.findValue("text").textValue();
        String user = event.findValue("user").textValue();
        LocalDateTime createdAt = LocalDateTime.now();
        int goalWeek = Integer.parseInt(createdAt.format(DateTimeFormatter.ofPattern("ww")));

        return new Goal(uuid, name, user, createdAt, goalWeek);
    }
}

package de.jehn.sarah.domain.logic;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.model.Goal;
import flowctrl.integration.slack.type.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Created by sarahjehn on 19.03.16.
 */
public class GoalBuilder {

    public static Goal buildGoal(JsonNode event){
        UUID uuid = UUID.randomUUID();
        String name = prepareGoalNameFormat(event);
        String user = event.findValue("user").textValue();
        String createdAt = event.findValue("ts").textValue();
        int goalWeek = Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("ww")));

        return new Goal(uuid, name, user, createdAt, goalWeek, false);
    }

    private static String prepareGoalNameFormat(JsonNode event){
        return event.findValue("text").textValue().replaceAll(">", "");
    }
}

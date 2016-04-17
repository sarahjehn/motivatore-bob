package de.jehn.sarah.domain.logic.eventBehaviour.actions;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.ActionBehaviour;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.Goal;
import de.jehn.sarah.domain.persistence.GoalRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarahjehn on 17.04.16.
 */
public class RetrieveGoalActionBehaviour implements ActionBehaviour {
    @Override
    public ActionResult action(JsonNode event, GoalRepository repository) {
        String user = event.findValue("user").textValue();
        return new ActionResult<Goal>(repository.getRemainingGoals(LocalDateTime.now(), user));
    }
}

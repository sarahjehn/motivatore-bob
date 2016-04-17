package de.jehn.sarah.domain.logic.eventBehaviour.actions;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.ActionBehaviour;
import de.jehn.sarah.domain.logic.GoalBuilder;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.Goal;
import de.jehn.sarah.domain.persistence.GoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Created by sarahjehn on 16.04.16.
 */

@Service
public class NewGoalActionBehaviour implements ActionBehaviour {

    @Override
    public ActionResult action(JsonNode event, GoalRepository repository) {
        Goal newGoal = GoalBuilder.buildGoal(event);
        ActionResult<String> result = null;

        if (repository.getGoalCountOfCurrentWeek(LocalDateTime.now(), newGoal.getUser()) >= 20) {
            result = new ActionResult<String>(singletonList("You added already 5 goals - " +
                    "that's quite ambituos. I wont save this one for your best"));
        } else {
            repository.saveGoal(newGoal);
            result = new ActionResult<String>(singletonList(newGoal.getName()));
        }

        return result;
    }
}

package de.jehn.sarah.domain.logic.eventBehaviour.actions;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.ActionBehaviour;
import de.jehn.sarah.domain.logic.GoalBuilder;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.persistence.GoalRepository;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * Created by sarahjehn on 16.04.16.
 */

@Service
public class NewGoalActionBehaviour implements ActionBehaviour {

    @Override
    public ActionResult action(JsonNode event, GoalRepository repository) {
        repository.saveGoal(GoalBuilder.buildGoal(event));
        return new ActionResult<Object>(emptyList());
    }
}

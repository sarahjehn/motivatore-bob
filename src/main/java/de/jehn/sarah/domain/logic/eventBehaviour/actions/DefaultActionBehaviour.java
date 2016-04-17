package de.jehn.sarah.domain.logic.eventBehaviour.actions;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.ActionBehaviour;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.persistence.GoalRepository;

/**
 * Created by sarahjehn on 17.04.16.
 */
public class DefaultActionBehaviour implements ActionBehaviour {
    @Override
    public ActionResult action(JsonNode event, GoalRepository repository) {
        return null;
    }
}

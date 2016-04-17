package de.jehn.sarah.domain.logic;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.persistence.GoalRepository;

/**
 * Created by sarahjehn on 16.04.16.
 */
public interface ActionBehaviour<T> {

    public ActionResult<T> action(JsonNode event, GoalRepository repository);
}

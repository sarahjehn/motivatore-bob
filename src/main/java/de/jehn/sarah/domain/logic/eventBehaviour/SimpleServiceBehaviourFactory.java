package de.jehn.sarah.domain.logic.eventBehaviour;

import de.jehn.sarah.domain.logic.ActionBehaviour;
import de.jehn.sarah.domain.logic.ResponseBehaviour;
import de.jehn.sarah.domain.logic.eventBehaviour.actions.DefaultActionBehaviour;
import de.jehn.sarah.domain.logic.eventBehaviour.actions.NewGoalActionBehaviour;
import de.jehn.sarah.domain.logic.eventBehaviour.actions.RemoveGoalActionBehaviour;
import de.jehn.sarah.domain.logic.eventBehaviour.actions.RetrieveGoalActionBehaviour;
import de.jehn.sarah.domain.logic.eventBehaviour.responses.*;
import de.jehn.sarah.domain.model.MessageType;
import flowctrl.integration.slack.webapi.SlackWebApiClient;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import static de.jehn.sarah.domain.model.MessageType.NEW_GOAL;
import static de.jehn.sarah.domain.model.MessageType.REMOVE_GOAL;
import static de.jehn.sarah.domain.model.MessageType.RETRIEVE_RECENT_GOALS;
import static de.jehn.sarah.domain.model.MessageType.NON_RELEVANT;

/**
 * Created by sarahjehn on 16.04.16.
 */
@Component
public class SimpleServiceBehaviourFactory {

    private static final Logger l = Logger.getLogger(SimpleServiceBehaviourFactory.class.getName());


    public ActionBehaviour createActionBehaviour(MessageType type) {
        ActionBehaviour action = new DefaultActionBehaviour();
        if (type.equals(NEW_GOAL)){
            action = new NewGoalActionBehaviour();
        } if (type.equals(RETRIEVE_RECENT_GOALS)){
            action = new RetrieveGoalActionBehaviour();
        } if (type.equals(REMOVE_GOAL)){
            action = new RemoveGoalActionBehaviour();
        }
        l.log(Level.INFO, "Instantiated Action Behaviour: " + action.getClass().getName());
        return action;
    }

    public ResponseBehaviour createResponseBehaviour(MessageType type, SlackWebApiClient client) {
        ResponseBehaviour responseBehaviour = new DefaultResponseBehaviour(client, type);
        if (type.equals(NEW_GOAL)) {
            responseBehaviour = new NewGoalResponseBehaviour(client);
        } if(type.equals(RETRIEVE_RECENT_GOALS)){
            responseBehaviour = new RetrieveGoalResponseBehaviour(client);
        } if (type.equals(NON_RELEVANT)){
            responseBehaviour = new NoResponseRequiredResponseBehaviour(client);
        } if (type.equals(REMOVE_GOAL)){
            responseBehaviour = new RemoveGoalResponseBehaviour(client);
        }
        l.log(Level.INFO, "Instantiated Action Behaviour: " + responseBehaviour.getClass().getName());
        return responseBehaviour;
    }
}

package de.jehn.sarah.domain.logic;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.eventBehaviour.SimpleServiceBehaviourFactory;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.MessageType;
import de.jehn.sarah.domain.persistence.GoalRepository;
import flowctrl.integration.slack.webapi.SlackWebApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sarahjehn on 16.04.16.
 */

@Service
public class SlackEventService {

    private ActionBehaviour actionBehaviour;
    private ResponseBehaviour responseBehaviour;

    @Autowired
    private GoalRepository repository;
    @Autowired
    private SimpleServiceBehaviourFactory behaviourFactory;

    @Autowired
    public SlackEventService(GoalRepository repository, SimpleServiceBehaviourFactory behaviourFactory){
        this.repository = repository;
        this.behaviourFactory = behaviourFactory;
    }

    public ActionResult startAction(MessageType type, JsonNode event){
        actionBehaviour = behaviourFactory.createActionBehaviour(type);
        return actionBehaviour.action(event, repository);
    }

    public void startResponse(MessageType type, JsonNode event, ActionResult result, SlackWebApiClient client){
        responseBehaviour = behaviourFactory.createResponseBehaviour(type, client);
        responseBehaviour.respond(event, result);
    }
}

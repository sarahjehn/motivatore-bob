package de.jehn.sarah.domain.logic.eventBehaviour.responses;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.MessageType;
import de.jehn.sarah.domain.model.ReactionMessage;
import flowctrl.integration.slack.webapi.SlackWebApiClient;

import java.util.List;

/**
 * Created by sarahjehn on 16.04.16.
 */
public class NewGoalResponseBehaviour extends AbstractResponseBehaviour {

    public NewGoalResponseBehaviour(SlackWebApiClient slackWebApiClient) {
        super(slackWebApiClient);
    }

    @Override
    protected MessageType getResponseType() {
        return MessageType.NEW_GOAL;
    }

    @Override
    protected boolean requiresReaction() {
        return false;
    }

    @Override
    protected boolean requiresResponse() {
        return true;
    }

    @Override
    protected String constructResponse(JsonNode event, ActionResult result, String predefinedText) {
        return predefinedText;
    }

    @Override
    protected List<ReactionMessage> constructReaction(JsonNode event, ActionResult result) {
        return null;
    }
}

package de.jehn.sarah.domain.logic.eventBehaviour.responses;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.MessageType;
import de.jehn.sarah.domain.model.ReactionMessage;
import flowctrl.integration.slack.webapi.SlackWebApiClient;

import java.util.List;

/**
 * Created by sarahjehn on 17.04.16.
 */
public class NoResponseRequiredResponseBehaviour extends AbstractResponseBehaviour {

    public NoResponseRequiredResponseBehaviour(SlackWebApiClient slackWebApiClient) {
        super(slackWebApiClient);
    }

    @Override
    protected MessageType getResponseType() {
        return null;
    }

    @Override
    protected boolean requiresReaction() {
        return false;
    }

    @Override
    protected boolean requiresResponse() {
        return false;
    }

    @Override
    protected String constructResponse(JsonNode event, ActionResult result, String predefinedText) {
        return null;
    }

    @Override
    protected List<ReactionMessage> constructReaction(JsonNode event, ActionResult result) {
        return null;
    }
}

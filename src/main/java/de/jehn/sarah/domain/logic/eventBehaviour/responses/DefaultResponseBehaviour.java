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
public class DefaultResponseBehaviour extends AbstractResponseBehaviour {

    private static String DEFAULT_RESPONSE = "Ooops, I didn't understand.";

    private MessageType type;

    public DefaultResponseBehaviour(SlackWebApiClient slackWebApiClient, MessageType type) {
        super(slackWebApiClient);
        this.type = type;
    }

    @Override
    protected MessageType getResponseType() {
        return type;
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
        if (predefinedText != null) {
            return predefinedText;
        } else
        return DEFAULT_RESPONSE;
    }

    @Override
    protected List<ReactionMessage> constructReaction(JsonNode event, ActionResult result) {
        return null;
    }

}

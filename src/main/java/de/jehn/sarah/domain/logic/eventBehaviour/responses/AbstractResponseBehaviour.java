package de.jehn.sarah.domain.logic.eventBehaviour.responses;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.ResponseBehaviour;
import de.jehn.sarah.domain.model.*;
import de.jehn.sarah.util.ResponseReader;
import flowctrl.integration.slack.webapi.SlackWebApiClient;
import flowctrl.integration.slack.webapi.method.chats.ChatPostMessageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sarahjehn on 16.04.16.
 */

@Service
public abstract class AbstractResponseBehaviour implements ResponseBehaviour {

    protected final SlackWebApiClient slackWebApiClient;
    private static final String BOT = "U0PCPHFU4";

    protected final Logger l = Logger.getLogger(ResponseBehaviour.class.getName());


    @Autowired
    public AbstractResponseBehaviour(SlackWebApiClient slackWebApiClient){
        this.slackWebApiClient = slackWebApiClient;
    }

    public final void respond(JsonNode event, ActionResult result) {

        if(requiresResponse()) {
            String predefinedText = selectPredefinedText(getResponseType());
            String response = constructResponse(event, result, predefinedText);
            postMessage(event, response);
        } if (requiresReaction()) {
            List<ReactionMessage> reactions = constructReaction(event, result);
            addReaction(reactions);
        }
    }

    protected abstract MessageType getResponseType();

    protected abstract boolean requiresReaction();

    protected abstract boolean requiresResponse();

    protected abstract String constructResponse(JsonNode event, ActionResult result, String predefinedText);

    protected abstract List<ReactionMessage> constructReaction(JsonNode event, ActionResult result);

    private void postMessage(JsonNode event, String response){
        l.log(Level.INFO, "postMessage: event: " + event + "| response: " + response);
        ChatPostMessageMethod postMessageMethod = new ChatPostMessageMethod(getChannel(event), response);
        postMessageMethod.setAs_user(true);
        postMessageMethod.setUsername(getBotUser());

        slackWebApiClient.postMessage(postMessageMethod);
    }

    private String getBotUser() {
        return BOT;
    }

    private void addReaction(List<ReactionMessage> reactions){
        for(ReactionMessage reaction : reactions){
            l.log(Level.INFO, "add a reaction: emoji: "
                    + reaction.getEmojiName() + "| reaction timestamp: " + reaction.getMessageTimestamp());

            slackWebApiClient.addReactionToMessage(reaction.getEmojiName(),
                    reaction.getChannel(), reaction.getMessageTimestamp());
        }
    }

    protected final String getChannel(JsonNode event){
        return event.findValue("channel").textValue();
    }

    private String selectPredefinedText(MessageType type) {
        Responses responses = new ResponseReader().getResponses();
        String response = null;
        for (Response r : responses.getResponses()) {
            if (r.getType() == type) {
                response = r.getText();
            }
        }
        return response;
    }

}

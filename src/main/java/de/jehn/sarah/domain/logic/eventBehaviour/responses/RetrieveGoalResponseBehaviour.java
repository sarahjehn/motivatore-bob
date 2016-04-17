package de.jehn.sarah.domain.logic.eventBehaviour.responses;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.ResponseBehaviour;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.Goal;
import de.jehn.sarah.domain.model.MessageType;
import de.jehn.sarah.domain.model.ReactionMessage;
import de.jehn.sarah.util.EmojiMapper;
import flowctrl.integration.slack.type.History;
import flowctrl.integration.slack.webapi.SlackWebApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sarahjehn on 17.04.16.
 */
public class RetrieveGoalResponseBehaviour extends AbstractResponseBehaviour {

    public RetrieveGoalResponseBehaviour(SlackWebApiClient slackWebApiClient) {
        super(slackWebApiClient);
    }

    @Override
    protected MessageType getResponseType() {
        return MessageType.RETRIEVE_RECENT_GOALS;
    }

    @Override
    protected boolean requiresReaction() {
        return true;
    }

    @Override
    protected boolean requiresResponse() {
        return true;
    }

    @Override
    protected String constructResponse(JsonNode event, ActionResult result, String predefinedText) {
        String response = predefinedText;

        l.log(Level.INFO, "constructResponse: current response: " + response + "result size = " + result.size());

        for (int i = 0; i < result.size(); i ++){
            response = response.concat("    \n");
            response = response.concat(":"+EmojiMapper.getEmojiForNumber(i + 1)+":");
            response = response.replaceAll(">", "");
            String text = ((Goal)result.getActionResult().get(i)).getName();
            response = response.concat(text);
        }
        return response;
    }

    @Override
    protected List<ReactionMessage> constructReaction(JsonNode event, ActionResult result) {
        int numberOfGoals = result.size();
        String channel = getChannel(event);
        History lastMessage = slackWebApiClient.getDirectMessageChannelHistory(channel, 1);
        String timestampOfLastMessage = lastMessage.getMessages().get(0).getTs();

        l.log(Level.INFO, "timestamp of channel history: " + timestampOfLastMessage +
                "channel history" + slackWebApiClient.getDirectMessageChannelHistory(channel).toString());

        List<ReactionMessage> reactions = new ArrayList<>();
        for(int i = 1; i <= numberOfGoals; i++){
            reactions.add(new ReactionMessage(EmojiMapper.getEmojiForNumber(i), channel, timestampOfLastMessage));
        }
        return reactions;
    }
}

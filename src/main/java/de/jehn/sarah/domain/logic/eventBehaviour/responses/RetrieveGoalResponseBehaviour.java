package de.jehn.sarah.domain.logic.eventBehaviour.responses;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.MessageType;
import de.jehn.sarah.domain.model.ReactionMessage;
import de.jehn.sarah.util.EmojiMapper;
import flowctrl.integration.slack.webapi.SlackWebApiClient;

import java.util.ArrayList;
import java.util.List;

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
        String response = "" + predefinedText;
            if(result != null){
                while(result.hasNext()){
                    response = response.concat("    \n");
                    response = response.concat((String) result.next());
                }
            } else response = "You haven't set goals for the current week";
        return response;    }

    @Override
    protected List<ReactionMessage> constructReaction(JsonNode event, ActionResult result) {
        int numberOfGoals = result.size();
        String channel = getChannel(event);
        String timestampOfLastMessage = slackWebApiClient.getDirectMessageChannelHistory(channel).getLatest();
        List<ReactionMessage> reactions = new ArrayList<>();
        for(int i = 1; i <= numberOfGoals; i++){
            reactions.add(new ReactionMessage(EmojiMapper.getEmojiForNumber(i), channel, timestampOfLastMessage));
        }
        return reactions;
    }
}

package de.jehn.sarah.domain.logic;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.model.MessageType;
import de.jehn.sarah.util.KeywordReader;
import flowctrl.integration.slack.type.Message;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sarahjehn on 05.03.16.
 */

@Service
public class MessageClassificationService {


    private static String BOT = "U0PCPHFU4";

    public MessageType getEventClassification(JsonNode event) {
        MessageType classification = null;
        if (isUserMessage(event)) {
            String text = event.findValue("text").textValue();
            classification = KeyWordMatcher.isMatching(new KeywordReader().getKeywords(), text);
        } else if (isUserReaction(event)) {
            classification = MessageType.REMOVE_GOAL;
        } else classification = MessageType.NON_RELEVANT;
        return classification;
    }

    private boolean isUserReaction(JsonNode event) {
        return !getEventPoster(event).equals(BOT)
        && getEventType(event).equals("reaction_added");
    }

    private String getEventType(JsonNode event) {
        return event.findValue("type").textValue();
    }

    private boolean isUserMessage(JsonNode event){
        return !getEventPoster(event).equals(BOT)
                && getEventType(event).equals("message");
    }

    private String getEventPoster(JsonNode event) {
        return event.findValue("user").textValue();
    }

}



package de.jehn.sarah.resource.eventlistener;

import com.fasterxml.jackson.databind.JsonNode;
import flowctrl.integration.slack.rtm.EventListener;

/**
 * Created by sarahjehn on 28.02.16.
 */
public class MessageEventListener implements EventListener {
    @Override
    public void handleMessage(JsonNode jsonNode) {
        //to do handle events
    }
}

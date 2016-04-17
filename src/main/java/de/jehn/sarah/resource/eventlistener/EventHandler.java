package de.jehn.sarah.resource.eventlistener;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.MessageClassificationService;
import de.jehn.sarah.domain.logic.SlackEventService;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.MessageType;
import flowctrl.integration.slack.rtm.EventListener;
import flowctrl.integration.slack.webapi.SlackWebApiClient;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sarahjehn on 16.04.16.
 */
public class EventHandler implements EventListener {

    private static final Logger l = Logger.getLogger(EventHandler.class.getName());

    private final SlackWebApiClient slackWebApiClient;
    private final SlackEventService service;
    private final MessageClassificationService classifier;

    public EventHandler (MessageClassificationService classifier, SlackEventService service, SlackWebApiClient slackWebApiClient){
        this.classifier = classifier;
        this.service = service;
        this.slackWebApiClient = slackWebApiClient;
    }


    @Override
    public void handleMessage(JsonNode event) {
        l.log(Level.INFO, "Message received: " + event);
        MessageType classification = classifier.getEventClassification(event);
        l.log(Level.INFO, "Classified Type = " + classification);
        ActionResult result = service.startAction(classification, event);
        service.startResponse(classification, event, result, slackWebApiClient);
     }
}

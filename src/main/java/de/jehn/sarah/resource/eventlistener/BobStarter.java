package de.jehn.sarah.resource.eventlistener;

import de.jehn.sarah.domain.logic.ResponseBehaviour;
import de.jehn.sarah.domain.logic.eventBehaviour.responses.DefaultResponseBehaviour;
import de.jehn.sarah.domain.model.MessageType;
import flowctrl.integration.slack.SlackClientFactory;
import flowctrl.integration.slack.webapi.SlackWebApiClient;
import flowctrl.integration.slack.webapi.method.chats.ChatPostMessageMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sarahjehn on 18.04.16.
 */

@RestController
@RequestMapping(BobStarter.CONTROLLER_MAPPING)
public class BobStarter {

    public static final String CONTROLLER_MAPPING = "/bob";
    private static final Logger l = Logger.getLogger(EventHandler.class.getName());

    @RequestMapping (value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void startBob(){
        l.log(Level.INFO, "Bob is awake now");
    }
}

package de.jehn.sarah;

import de.jehn.sarah.resource.eventlistener.MessageEventListener;
import flowctrl.integration.slack.SlackClientFactory;
import flowctrl.integration.slack.rtm.SlackRealTimeMessagingClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(final String [] args){

        String SLACKTOKEN = System.getenv("SLACKTOKEN");

        SpringApplication.run(Application.class, args);

       // Start listening to events
        SlackRealTimeMessagingClient realTimeMessagingClient = SlackClientFactory.createSlackRealTimeMessagingClient(SLACKTOKEN);
        realTimeMessagingClient.addListener("message", new MessageEventListener());
        realTimeMessagingClient.connect();

    }
}

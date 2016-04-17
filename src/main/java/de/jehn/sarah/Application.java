package de.jehn.sarah;

import de.jehn.sarah.domain.logic.MessageClassificationService;
import de.jehn.sarah.domain.logic.SlackEventService;
import de.jehn.sarah.resource.eventlistener.EventHandler;
import flowctrl.integration.slack.SlackClientFactory;
import flowctrl.integration.slack.rtm.Event;
import flowctrl.integration.slack.rtm.SlackRealTimeMessagingClient;
import flowctrl.integration.slack.webapi.SlackWebApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

    String SLACKTOKEN = System.getenv("SLACKTOKEN");

    @Autowired
    MessageClassificationService messageClassificationService;

    @Autowired
    SlackEventService service;

    @Bean
    SlackWebApiClient slackWebApiClient(){
        return SlackClientFactory.createWebApiClient(SLACKTOKEN);
    }

    @Bean
    SlackRealTimeMessagingClient realTimeMessagingClient(){
        return SlackClientFactory.createSlackRealTimeMessagingClient(SLACKTOKEN);
    }

    @Bean
    EventHandler eventHandler(){
        return new EventHandler(messageClassificationService, service, slackWebApiClient());
    }

    public static void main(final String [] args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        SlackRealTimeMessagingClient realTimeMessagingClient = realTimeMessagingClient();
        realTimeMessagingClient.addListener(Event.MESSAGE, eventHandler());
        realTimeMessagingClient.addListener(Event.REACTION_ADDED, eventHandler());
        realTimeMessagingClient.connect();
    }
}

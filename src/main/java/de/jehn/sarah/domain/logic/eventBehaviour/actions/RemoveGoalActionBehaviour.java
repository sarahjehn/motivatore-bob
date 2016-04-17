package de.jehn.sarah.domain.logic.eventBehaviour.actions;

import com.fasterxml.jackson.databind.JsonNode;
import de.jehn.sarah.domain.logic.ActionBehaviour;
import de.jehn.sarah.domain.model.ActionResult;
import de.jehn.sarah.domain.model.Goal;
import de.jehn.sarah.domain.persistence.GoalRepository;
import de.jehn.sarah.util.EmojiMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Collections.singletonList;

/**
 * Created by sarahjehn on 17.04.16.
 */
public class RemoveGoalActionBehaviour implements ActionBehaviour {

    private final Logger l = Logger.getLogger(RemoveGoalActionBehaviour.class.getName());

    @Override
    public ActionResult action(JsonNode event, GoalRepository repository) {
        String user = getUser(event);
        int indexOfGoalToBeMarkedAsDone = getReactionNumber(event);

        l.log(Level.INFO, "goal to be removed: " + indexOfGoalToBeMarkedAsDone);


        List<Goal> goalsToBeMarkedAsDone = repository.getRemainingGoals(LocalDateTime.now(), user);

        l.log(Level.INFO, "size of remaining goals: " + goalsToBeMarkedAsDone.size());

        ActionResult action = null;

        if(goalsAvailableForMarkAsDone(indexOfGoalToBeMarkedAsDone, goalsToBeMarkedAsDone.size())){
            l.log(Level.INFO, "it is in mark to be done");
            repository.markAsDone(goalsToBeMarkedAsDone.get(indexOfGoalToBeMarkedAsDone).getUuid(), user);
            action = new ActionResult<Goal> (repository.getRemainingGoals(LocalDateTime.now(), user));
        } else {
            action = new ActionResult<String>(singletonList("Oops, this goal was already marked as done"));
        }

        l.log(Level.INFO, "this is the action: " + action.toString());

        return action;
    }

    private boolean goalsAvailableForMarkAsDone(int indexOfGoalToBeMarkedAsDone, int numberOfRemainingGoals) {
        return indexOfGoalToBeMarkedAsDone < numberOfRemainingGoals
                && indexOfGoalToBeMarkedAsDone >= 0
                && numberOfRemainingGoals > 0;
    }

    private String getUser(JsonNode event){
        return event.findValue("user").textValue();
    }

    private int getReactionNumber(JsonNode event){
        String recationEmoji = event.findValue("reaction").textValue();
        return EmojiMapper.getNumberForEmoji(recationEmoji);
    }
}




/*
{"type":"reaction_added","user":"U0PCRNLRH","item":{"type":"message","channel":"D0PCV54DN","ts":"1460900678.000016"},"reaction":"one","item_user":"U0PCPHFU4","event_ts":"1460901065.200910","ts":"1460901065.000023"}
 */
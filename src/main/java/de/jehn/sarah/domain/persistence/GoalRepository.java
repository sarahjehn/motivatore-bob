package de.jehn.sarah.domain.persistence;

import de.jehn.sarah.domain.model.Goal;
import org.springframework.stereotype.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by sarahjehn on 05.03.16.
 */

@org.springframework.stereotype.Repository
public interface GoalRepository {
    void saveGoal(Goal goal);

    List<String> getGoals(LocalDateTime date, String user);

    int getGoalCountOfCurrentWeek(LocalDateTime date, String user);

    Goal getGoal(String messageTimestamp, String user);

    void markAsDone(UUID uuid, String user);

    public List<Goal> getRemainingGoals(LocalDateTime date, String user);
}


package de.jehn.sarah.domain.persistence;

import de.jehn.sarah.domain.model.Goal;
import org.springframework.stereotype.*;

/**
 * Created by sarahjehn on 05.03.16.
 */

@org.springframework.stereotype.Repository
public interface GoalRepository {
    void saveGoal(Goal goal);
}


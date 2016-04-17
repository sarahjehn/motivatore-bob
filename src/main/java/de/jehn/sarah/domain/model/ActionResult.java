package de.jehn.sarah.domain.model;

import java.util.Iterator;
import java.util.List;

/**
 * Created by sarahjehn on 16.04.16.
 */
public class ActionResult<T> {

    private final List<T> actionResult;

    public ActionResult (List<T> actionResult){
        this.actionResult=actionResult;
    }

    public List<T> getActionResult() {
        return actionResult;
    }

    public int size(){
        return actionResult.size();
    }
}

package de.jehn.sarah.util;

import de.jehn.sarah.domain.model.Responses;

/**
 * Created by sarahjehn on 20.03.16.
 */
public class ResponseReader extends Reader {

    static String FILE_PATH = "./src/main/java/de/jehn/sarah/util/response.json";

    public Responses getResponses() {
        return (Responses) readFromJSON(FILE_PATH, Responses.class);
    }
}

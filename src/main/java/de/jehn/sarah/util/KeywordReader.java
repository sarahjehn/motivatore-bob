package de.jehn.sarah.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jehn.sarah.domain.model.KeywordClusters;

import java.io.File;
import java.io.IOException;

/**
 * Created by sarahjehn on 19.03.16.
 */
public class KeywordReader extends Reader {

    static String FILE_PATH = "./src/main/java/de/jehn/sarah/util/keywords.json";

    public KeywordClusters getKeywords() {
        return (KeywordClusters) readFromJSON(FILE_PATH, KeywordClusters.class);
    }
}
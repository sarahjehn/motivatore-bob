package de.jehn.sarah.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jehn.sarah.domain.model.KeywordClusters;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by sarahjehn on 20.03.16.
 */
public class Reader<T> {

    public T readFromJSON(String filePath, Class<T> valuetype) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), valuetype);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

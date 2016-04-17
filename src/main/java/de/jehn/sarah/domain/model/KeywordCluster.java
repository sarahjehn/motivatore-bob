package de.jehn.sarah.domain.model;

/**
 * Created by sarahjehn on 19.03.16.
 */
public class KeywordCluster {

    private MessageType type;
    private String[] keywords;

    public KeywordCluster(MessageType type, String[] keywords){
        this.type = type;
        this.keywords = keywords;
    }

    public KeywordCluster(){
    }

    public MessageType getType() {
        return type;
    }

    public String[] getKeywords() {
        return keywords;
    }
}

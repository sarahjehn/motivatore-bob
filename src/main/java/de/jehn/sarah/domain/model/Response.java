package de.jehn.sarah.domain.model;

/**
 * Created by sarahjehn on 20.03.16.
 */
public class Response {

    private MessageType type;
    private String text;

    public Response (MessageType type, String text){
        this.type = type;
        this.text = text;
    }

    public Response (){
    }


    public MessageType getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}

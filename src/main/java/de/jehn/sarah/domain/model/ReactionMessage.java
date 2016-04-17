package de.jehn.sarah.domain.model;

/**
 * Created by sarahjehn on 16.04.16.
 */
public class ReactionMessage {

    private final String emojiName;
    private final String channel;
    private final String messageTimestamp;

    public ReactionMessage (String emojiName, String channel, String messageTimestamp){
        this.channel = channel;
        this.emojiName = emojiName;
        this.messageTimestamp = messageTimestamp;
    }

    public String getChannel() {
        return channel;
    }

    public String getEmojiName() {
        return emojiName;
    }

    public String getMessageTimestamp() {
        return messageTimestamp;
    }
}

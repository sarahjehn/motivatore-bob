package de.jehn.sarah.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarahjehn on 17.04.16.
 */
public class EmojiMapper {

    static String[] NUMBER_EMOJIES = {"one", "two", "three", "four", "five"};


    public static String getEmojiForNumber(int number){
        String emoji = ":thumpsup:";
        if(number <= 5){
            emoji = NUMBER_EMOJIES[number-1];
        }
        return emoji;
    }

    public static int getNumberForEmoji(String emoji) {
        int number = -1;
        for(int i = 0; i < NUMBER_EMOJIES.length; i++){
            if(NUMBER_EMOJIES[i].equals(emoji)){
                number = i;
            }
        }
        return number;
    }
}

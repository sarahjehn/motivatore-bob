package de.jehn.sarah.domain.logic;

import de.jehn.sarah.domain.model.KeywordCluster;
import de.jehn.sarah.domain.model.KeywordClusters;
import de.jehn.sarah.domain.model.MessageType;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by sarahjehn on 19.03.16.
 */
public class KeyWordMatcher {

    private static final Logger l = Logger.getLogger(KeyWordMatcher.class.getName());

    public static MessageType isMatching(KeywordClusters keywordClusters, String messageText){
        MessageType classifiaction = findMatchingClusters(prepareText(messageText), keywordClusters);
        if(classifiaction == null){
            classifiaction = MessageType.NO_MATCH;
        }
        return classifiaction;
    }

    private static String[] prepareText(String messageText) {
        String goalShortcut = "&gt;";
        String punctuation = "[^\\w]";
        String space = "\\s";
        String preparedText = messageText
                .replaceAll(goalShortcut, "NEW_GOAL")
                .replaceAll(punctuation, ";")
                .replaceAll(space, ";")
                .replaceAll("NEW_GOAL", ">")
                .toLowerCase();
        String [] wordsInMessage = preparedText.split(";");
        String [] preparedWordsInMessage = new String [wordsInMessage.length];
        for(int i = 0; i < wordsInMessage.length; i++){
            String s = wordsInMessage[i].replace(";", "");
            preparedWordsInMessage[i] = s;
        }
        return preparedWordsInMessage;
    }

    private static MessageType findMatchingClusters(String[] wordsInMessage, KeywordClusters keywordClusters) {
        MessageType matchingCluster = MessageType.NO_MATCH;

        for(int i = 0; i < keywordClusters.getClusters().length; i++){

            KeywordCluster cluster = keywordClusters.getClusters()[i];
            String[] keywords = cluster.getKeywords();
            MessageType type = cluster.getType();

            int numberOfMatchingKeywordsInCluster = 0;

            for (String keyword : keywords) {
                if (messageContainsKeyword(wordsInMessage, keyword)) {
                    numberOfMatchingKeywordsInCluster += 1;
                }
            }

            if(numberOfMatchingKeywordsInCluster == keywords.length){
                matchingCluster = type;
                break;
            }
        }
        return matchingCluster;
    }

    private static boolean messageContainsKeyword(String[] wordsInMessage, String keyword) {
        boolean containsKeyword = false;
        for (String wordInMessage : wordsInMessage) {
            if (keyword.equals(wordInMessage)) {
                containsKeyword = true;
            }
        }
        return containsKeyword;
    }

}

package de.jehn.sarah.domain.model;

/**
 * Created by sarahjehn on 19.03.16.
 */
public class KeywordClusters {

    private KeywordCluster [] clusters;

    public KeywordClusters(KeywordCluster [] clusters){
        this.clusters = clusters;
    }

    public KeywordClusters(){

    }

    public KeywordCluster[] getClusters(){
        return this.clusters;
    }
}

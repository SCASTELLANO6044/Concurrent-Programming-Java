package org.textprocessing;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class WordFrequencies {
    private volatile Map<String, Integer> wordFrecuenciesMap = new HashMap<>();

    public synchronized void addFrequencies(Map<String,Integer> f){
        Set<String> keySet = f.keySet();
        for (String key : keySet){
            if (wordFrecuenciesMap.containsKey(key)){
                wordFrecuenciesMap.put(key, wordFrecuenciesMap.get(key) + 1);
            }else {
                wordFrecuenciesMap.put(key, 1);
            }
        }
        notifyAll();
    }
    public synchronized Map<String,Integer> getFrequencies(){
        notifyAll();
        return wordFrecuenciesMap;
    }
}

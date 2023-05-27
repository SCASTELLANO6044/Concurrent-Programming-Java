package org.textprocessing;
import java.util.Map;
import java.util.HashMap;

public class WordFrequencies {
    private volatile Map<String, Integer> wordFrecuenciesMap = new HashMap<>();

    public synchronized void addFrequencies(Map<String,Integer> f){

        for (Map.Entry<String, Integer> entry : f.entrySet()){
            if (wordFrecuenciesMap.containsKey(entry.getKey())){
                wordFrecuenciesMap.put(entry.getKey(), entry.getValue()+ wordFrecuenciesMap.get(entry.getKey()));
            }else {
                wordFrecuenciesMap.put(entry.getKey(), 1);
            }
        }
        notifyAll();
    }
    public synchronized Map<String,Integer> getFrequencies(){
        notifyAll();
        return wordFrecuenciesMap;
    }
}

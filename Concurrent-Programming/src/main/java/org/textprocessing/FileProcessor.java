package org.textprocessing;

import java.util.HashMap;
import java.util.Map;

public class FileProcessor extends Thread{

    private FileContents fileContents;
    private WordFrequencies wordFrequencies;

    public FileProcessor(FileContents fileContents, WordFrequencies wordFrequencies){
        this.fileContents = fileContents;
        this.wordFrequencies = wordFrequencies;
    }

    @Override
    public void run() {
        String fileContent = this.fileContents.getContents();
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        while (fileContent != null){

            String[] wordsList = fileContent.split(" ");
            for (String word : wordsList){
                if (wordFrequencyMap.containsKey(word)){
                    wordFrequencyMap.put(word, wordFrequencyMap.get(word) + 1);
                }else {
                    wordFrequencyMap.put(word, 0);
                }
            }
            fileContent = this.fileContents.getContents();
        }
        this.wordFrequencies.addFrequencies(wordFrequencyMap);
    }
}

package org.textprocessing;
import java.util.Queue;
public class FileNames {
    private Queue<String> queue;
    public void addName(String fileName) {
        queue.add(fileName);
    }
    public String getName() {
        return null;
    }
    public void noMoreNames() {

    }

    @Override
    public String toString() {
        return "FileNames{" +
                "queue=" + queue +
                '}';
    }
}
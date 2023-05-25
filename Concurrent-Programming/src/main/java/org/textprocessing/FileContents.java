package org.textprocessing;
import java.util.LinkedList;
import java.util.Queue;
public class FileContents {
    private volatile Queue<String> queue;
    private volatile int registerCount = 0;
    private final int maxFiles;
    private final int maxChars;
    public FileContents(int maxFiles, int maxChars) {
        this.maxFiles = maxFiles;
        this.maxChars = maxChars;
        this.queue = new LinkedList<>();
    }
    public synchronized void registerWriter() {
        registerCount++;
    }
    public synchronized void unregisterWriter() {
        registerCount--;
    }
    public synchronized void addContents(String contents) {
        while (queue.size() >= maxFiles){
            try {
                wait();
            }catch (Exception e){
                System.out.println(e.getStackTrace());
            }
        }
        if (queue.isEmpty()){
            queue.add(contents);
        } else if (queue.isEmpty() && contents.length() < maxChars) {
            queue.add(contents);
        }
    }
    public synchronized String getContents() {
        while (queue.isEmpty()){
            try {
                wait();
            }catch (Exception e){
                System.out.println(e.getStackTrace());
            }
        }
        if(this.registerCount <= 0){
            return null;
        }else {
            return queue.poll();
        }
    }
}

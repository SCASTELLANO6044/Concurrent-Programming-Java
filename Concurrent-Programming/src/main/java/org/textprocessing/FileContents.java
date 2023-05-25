package org.textprocessing;
import java.util.Arrays;
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
        notifyAll();
    }
    public synchronized void unregisterWriter() {
        registerCount--;
        notifyAll();
    }
    public synchronized void addContents(String contents) {
        while (queue.size() >= maxFiles){
            try {
                wait();
            }catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
                Thread.currentThread().interrupt();
            }
        }
        if ((queue.isEmpty() || (contents.length() < maxChars)) && (registerCount > 0)) {
            queue.add(contents);
        }
        notifyAll();
    }
    public synchronized String getContents() {
        while (queue.isEmpty()){
            try {
                wait();
            }catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
                Thread.currentThread().interrupt();
            }
        }
        if(this.registerCount <= 0){
            notifyAll();
            return null;
        }else {
            notifyAll();
            return queue.poll();
        }
    }
}

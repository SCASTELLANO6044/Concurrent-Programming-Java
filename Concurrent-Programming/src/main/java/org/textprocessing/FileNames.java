package org.textprocessing;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
public class FileNames {
    private volatile Queue<String> queue;
    private volatile boolean isClosed;
    private volatile int count = 0;

    public FileNames(){
        this.isClosed = false;
        this.queue = new LinkedList<>();
    }
    public synchronized void addName(String fileName) {
        if (!isClosed){
            count++;
            queue.add(fileName);
        }
    }
    public synchronized String getName() {
        while (count <= 0){
            if (isClosed){
                notifyAll();
                return null;
            }
            try {
                wait();
            }catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
                Thread.currentThread().interrupt();
            }
        }
        count--;
        notifyAll();
        return queue.poll();
    }
    public synchronized void noMoreNames() {
        this.isClosed = true;
        notifyAll();
    }
}
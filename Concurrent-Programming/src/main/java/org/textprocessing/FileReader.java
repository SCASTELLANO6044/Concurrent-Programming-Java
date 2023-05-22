package org.textprocessing;
public class FileReader extends Thread{
    private FileNames fileNames;
    private FileContents fileContents;
    public FileReader(FileNames fileNames, FileContents fileContents){
        this.fileNames = fileNames;
        this.fileContents = fileContents;
    }

    @Override
    public void run (){
        String fileName = fileNames.getName();
        while (fileName != null){
            String fileContent = Tools.getContents(fileName);
            this.fileContents.addContents(fileContent);
            fileName = fileNames.getName();
        }
    }
}

package org.textprocessing;
public class FileReader extends Thread{
    private final FileNames fileNames;
    private final FileContents fileContents;
    public FileReader(FileNames fileNames, FileContents fileContents){
        this.fileNames = fileNames;
        this.fileContents = fileContents;
    }

    @Override
    public void run (){
        String fileName = fileNames.getName();
        while (fileName != null){
            String fileContent = Tools.getContents(fileName);

            this.fileContents.registerWriter();
            this.fileContents.addContents(fileContent);
            this.fileContents.unregisterWriter();

            fileName = fileNames.getName();
        }
    }
}

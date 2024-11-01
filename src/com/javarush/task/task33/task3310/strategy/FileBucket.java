package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    Path path;

    public FileBucket() {
        try {
            path= Files.createTempFile("temp",null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        path.toFile().deleteOnExit();

    }

    public long getFileSize(){
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void putEntry(Entry entry){
        try {
            ObjectOutputStream outputStream=new ObjectOutputStream(Files.newOutputStream(path));
            outputStream.writeObject(entry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Entry getEntry(){
        if (getFileSize() == 0) {
            return null;
        }else {
            try {
                ObjectInputStream inputStream=new ObjectInputStream(Files.newInputStream(path));
                return (Entry) inputStream.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

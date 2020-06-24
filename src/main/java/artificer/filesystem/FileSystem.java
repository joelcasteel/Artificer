package main.java.artificer.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Hashtable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class FileSystem {
    
    private static FileSystem instance = null;
    
    public static final String saveDir = System.getProperty("user.home") +  "\\.artificer\\";
    
    public static final String MONSTER_FILE = "Monster";
    
    public Hashtable<String, File> files = new Hashtable<>();
    
    
    private FileSystem() {
        File file = new File(saveDir);
        if(!file.exists()) {
            file.mkdir();
        }
        
        files.put(MONSTER_FILE,  new File(saveDir + "monster.json"));
        
        try {
            if(!files.get(MONSTER_FILE).exists()) {
                files.get(MONSTER_FILE).createNewFile();
                System.out.println("Hello");
                writeToSaveFile(MONSTER_FILE, "{}");
            }
            
        } catch(Exception ex) {
            System.out.println("Error creating monster.json" + ex.getMessage());
        }
        
        
        
    }
    
    public static FileSystem getInstance() {
        if(instance == null) {
            instance = new FileSystem();
        }
        
        return instance;
    }
    
    public boolean writeToSaveFile(String fileName,String output) {
        try {
            FileWriter writer = new FileWriter(files.get(fileName));
            writer.write(output);
            writer.flush();
            writer.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Error writing to files: "+ ex.getMessage());
            return false;
        }
    }
    
    public FileReader getFileReader(String fileName) throws FileNotFoundException {
        return new FileReader(files.get(fileName));
    }
    
    public String readFileAsString(String fileName) throws FileNotFoundException, IOException {
        byte[]  encoded = Files.readAllBytes(
                files.get(MONSTER_FILE).toPath());
        
        return new String(encoded, StandardCharsets.UTF_8);
    }
    
   
    
    
}

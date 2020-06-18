package main.java.artificer.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class FileSystem {
    
    private static FileSystem instance = null;
    
    public static final String saveDir = System.getProperty("user.home") +  "\\.artificer\\";
    
    File monsterData;
    FileWriter monsterWrite;
    FileReader monsterRead;
    
    
    private FileSystem() {
        File file = new File(saveDir);
        if(!file.exists()) {
            file.mkdir();
        }
        
        monsterData = new File(saveDir + "monster.json");
        try {
            monsterData.createNewFile();
            
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
    
    public boolean writeMonster(String output) {
        try {
            FileWriter writer = new FileWriter(monsterData);
            writer.write(output);
            writer.flush();
            writer.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Error writing to monster.json: "+ ex.getMessage());
            return false;
        }
    }
    
    public JsonObject readMonster() {
        return null;
    }
   
    
    
}

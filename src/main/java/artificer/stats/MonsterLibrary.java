package main.java.artificer.stats;

import java.util.Hashtable;

import com.google.gson.stream.JsonReader;

import main.java.artificer.Main;

/**
 * Store the entire local collection of monsters
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class MonsterLibrary {
    
    Hashtable<String, Monster> monsters;
    
    /**
     * Construct a new MonsterLibrary. Initiates the HashTable and reads library from file.
     */
    public MonsterLibrary() {
        
        monsters = new Hashtable<>();
        
        
    }
    
    
    public void readLibraryFromFile() {
        try {
            
            JsonReader reader = new JsonReader(Main.fileSystem.readMonster());
            
            
            
        } catch (Exception ex) {
            System.out.println("Error Reading Monster Library from File: " + ex.getMessage());
        }
    }
    
    
}

package main.java.artificer.stats;

import java.util.Hashtable;
import java.util.Iterator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.java.artificer.filesystem.FileSystem;

/**
 * Store the entire local collection of monsters
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class MonsterLibrary {
    
    private static MonsterLibrary instance = null;
    
    Hashtable<String, Monster> monsters;
    
    /**
     * Construct a new MonsterLibrary. Initiates the HashTable and reads library from file.
     */
    private MonsterLibrary() {
        monsters = new Hashtable<>();
        readLibraryFromFile();
    }
    
    public static MonsterLibrary getInstance() {
        if (instance == null) {
            instance = new MonsterLibrary();
        }
        
        return instance;
    }
    
    
    public void readLibraryFromFile() {
        try {
            String libString =
                    FileSystem.getInstance().readFileAsString(FileSystem.MONSTER_FILE); 
            
            if(!JsonParser.parseString(libString).isJsonNull()) {
                JsonObject source = JsonParser.parseString(libString).getAsJsonObject();
                for(String key: source.keySet()) {
                    System.out.println("> " + key);
                    System.out.println("> " + source.getAsJsonObject(key).toString());
                    monsters.put(key, new Monster(source.getAsJsonObject(key)));
                }
            }
            
            
        } catch (Exception ex) {
            System.out.println("Error Reading Monster Library from File: " + ex.getMessage());
        }
    }
    
    public void add(Monster m) {
        monsters.put(m.getName(), m);
    }
    
    public Monster get(String name) {
        return monsters.get(name);
    }
    
    public void remove(String name) {
        monsters.remove(name);
    }
    
    public void saveLibraryToFile() {
        try {
            JsonObject library = new JsonObject();
            Iterator<Monster> m = monsters.elements().asIterator();
            while(m.hasNext()) {
                Monster monster = m.next();
                System.out.println(monster.getName());
                library.add(monster.getName(), monster.toJson());
                System.out.println(library.toString());
            }
            FileSystem.getInstance().writeToSaveFile(FileSystem.MONSTER_FILE, library.toString());
        } catch (Exception ex) {
            System.out.println("Exception Saving Monster Library: " + ex.getMessage());
        }
    }
    
    
}

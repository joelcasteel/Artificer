package main.java.artificer.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * A management class for all of a monsters Stats, and proficiencies.
 * 
 * Stats are kept in a Map according to their names while proficiencies are kept in an array-list.
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class StatBlock {
    
    //Storage for Stats and profs.
    Map<String, Stat> stats;
    ArrayList<Proficiency> profs;
    
    /**
     * Construct a new Statblock from a JSON Source.
     * This automatically creates all the stats and proficiencies that go along with it.
     * 
     * @param source The JsonObject source
     */
    public StatBlock(JsonObject source) {
        
        //De-Serializae the stats one by one.
        stats = new HashMap<>();
        
        stats.put(Stat.STR, new Stat(source.get("strength").getAsInt()));
        stats.put(Stat.DEX, new Stat(source.get("dexterity").getAsInt()));
        stats.put(Stat.CON, new Stat(source.get("constitution").getAsInt()));
        stats.put(Stat.INT, new Stat(source.get("intelligence").getAsInt()));
        stats.put(Stat.WIS, new Stat(source.get("wisdom").getAsInt()));
        stats.put(Stat.CHA, new Stat(source.get("charisma").getAsInt()));
        
        
        //De-Serialize the Proficiencies one by one.
        profs = new ArrayList<>();
        
        JsonArray profArr = source.get("proficiencies").getAsJsonArray();
        for(int i = 0; i < profArr.size(); i++) {
            profs.add(new Proficiency(profArr.get(i).getAsJsonObject()));
        }
        
        
    }
    
    private StatBlock() {
        stats = new HashMap<>();
        
        profs = new ArrayList<>();
    }
    
    public StatBlock copyStatBlock() {
        StatBlock copy = new StatBlock();
        copy.setStat(Stat.STR, stats.get(Stat.STR).copyStat());
        copy.setStat(Stat.DEX, stats.get(Stat.DEX).copyStat());
        copy.setStat(Stat.CON, stats.get(Stat.CON).copyStat());
        copy.setStat(Stat.INT, stats.get(Stat.INT).copyStat());
        copy.setStat(Stat.WIS, stats.get(Stat.WIS).copyStat());
        copy.setStat(Stat.CHA, stats.get(Stat.CHA).copyStat());
        
        for(Proficiency p : profs) {
            copy.addProf(p.copyProficiency());
        }
        
        return copy;
        
    }
    
    /**
     * Get the stat under a certain name.
     * Generally this is called by using the strings inside of stat.
     * Ex: getStat(Stat.STR)
     * 
     * @param key The name of the stat.
     * @return The stat under a key. Null if not found.
     */
    public Stat getStat(String key) {
        if(stats.containsKey(key)) {
            return stats.get(key);
        }
        return null;
    }
    
    /**
     * Get all the stats as an Iterable List
     * 
     * @return Iterable of stats.
     */
    public Iterable<Stat> getAllStats(){
        return stats.values();
    }
    
    /**
     * Get a specific Proficiency by listing the index.
     * 
     * @param i index to look under.
     * @return Prof under index. Null if OOB.
     */
    public Proficiency getProf(int i) {
        try {
            return profs.get(i);
        } catch (Exception ex) {
            System.out.println("Error retrieving Proficiency." + ex.getMessage());
            return null;
        }
    }
    
    /**
     * Get all profs as an Iterable list.
     * Since profs are kept in an arrayList, we just get the list.
     * 
     * This is probably Dangerous... Someone could fuck our statblock.
     * @Todo Re-Evaluate the way proficiencies are stored/accessed.
     * This will happen for our save, restore functionality.
     * 
     * @return Iterable of Proficiencies
     */
    public Iterable<Proficiency> getAllProfs() {
        return profs;
    }
    
    public void addProf(Proficiency prof) {
        profs.add(prof);
    }

    /**
     * Change a score in the Statblock.
     * This is used by the UI for editing a monster. It's pretty straightforward.
     * I like it.
     * 
     * @param key The stat to change.
     * @param score The score to assign it.
     * @return True if changed. False if didn't exist.
     */
    public boolean changeScore(String key, int score) {
        if(stats.containsKey(key)) {
            stats.remove(key);
            stats.put(key, new Stat(score));
            return true;
        }
        return false;
    }
    
    public void setStat(String key, Stat stat) {
        if(!stats.containsKey(key)) {
            stats.put(key, stat.copyStat());
        }
    }
    
    public JsonArray getProfJson() {
        JsonArray jsonProfs = new JsonArray();
        for(Proficiency p:profs) {
            jsonProfs.add(p.toJson());
        }
        return jsonProfs;
    }
    
   
    

}

package main.java.artificer.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class StatBlock {
    
    Map<String, Stat> stats;
    ArrayList<Proficiency> profs;
    
    
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

    public Stat getStat(String key) {
        if(stats.containsKey(key)) {
            return stats.get(key);
        }
        return null;
    }
    
    public Iterable<Stat> getAllStats(){
        return stats.values();
    }
    
    public Proficiency getProf(int i) {
        return profs.get(i);
    }
    
    public Iterable<Proficiency> getAllProfs() {
        return profs;
    }

    public boolean changeScore(String key, int score) {
        if(stats.containsKey(key)) {
            stats.remove(key);
            stats.put(key, new Stat(score));
            System.out.println(stats);
            return true;
        }
        return false;
    }
    

}

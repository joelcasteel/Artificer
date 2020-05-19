package main.java.artificer.stats;

import java.awt.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class StatBlock {
    
    Map<String, Stat> stats;
    
    public StatBlock(int str, int dex, int con, int intl, int wis, int cha) {
        stats = new HashMap<>();
        stats.put(Stat.STR, new Stat(str));
        stats.put(Stat.DEX, new Stat(dex));
        stats.put(Stat.CON, new Stat(con));
        stats.put(Stat.INT, new Stat(intl));
        stats.put(Stat.WIS, new Stat(wis));
        stats.put(Stat.CHA, new Stat(cha));
    }
    
    public StatBlock(JsonObject source) {
        stats = new HashMap<>();
        stats.put(Stat.STR, new Stat(source.get("strength").getAsInt()));
        stats.put(Stat.DEX, new Stat(source.get("dexterity").getAsInt()));
        stats.put(Stat.CON, new Stat(source.get("constitution").getAsInt()));
        stats.put(Stat.INT, new Stat(source.get("intelligence").getAsInt()));
        stats.put(Stat.WIS, new Stat(source.get("wisdom").getAsInt()));
        stats.put(Stat.CHA, new Stat(source.get("charisma").getAsInt()));
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

    
    

}

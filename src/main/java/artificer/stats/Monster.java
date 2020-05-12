package main.java.artificer.stats;

import java.util.HashMap;

import com.google.gson.JsonObject;

public class Monster {
    
    String name;
    String type;
    String size;
    String alignment;
    String language;
    
    int AC;
    int HP;
    int CR;
    int passivePerc;
    
    String hitDice;
    
    
    String speed[];
    String sense[];
    
    StatBlock stats;
    
    String dmgVulner[];
    String dmgResist[];
    String dmgImmune[];
    String condImmune[];
    
    public Monster(JsonObject source) {
        name = source.get("name").getAsString();
        type = source.get("type").getAsString();
        size = source.get("size").getAsString();
        alignment = source.get("alignment").getAsString();
    }
    
   
}

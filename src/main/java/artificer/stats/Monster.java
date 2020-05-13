package main.java.artificer.stats;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
        
        try {
        name = source.get("name").getAsString();
        type = source.get("type").getAsString();
        size = source.get("size").getAsString();
        alignment = source.get("alignment").getAsString();
        language = source.get("languages").getAsString();
        
        AC = source.get("armor_class").getAsInt();
        HP = source.get("hit_points").getAsInt();
        CR = source.get("challenge_rating").getAsInt();
        passivePerc = source.get("senses").getAsJsonObject().get("passive_perception").getAsInt();
        hitDice = source.get("hit_dice").getAsString();
        
        Set<Map.Entry<String, JsonElement>> entrySet 
            = source.get("speed").getAsJsonObject().entrySet();
        
        int i = 0;
        speed = new String[entrySet.size()];
        for(Map.Entry<String, JsonElement> entry : entrySet) {
            speed[i] = entry.getKey() + ": " + entry.getValue().getAsString();
            i++;
        }
        
        
        entrySet 
            = source.get("senses").getAsJsonObject().entrySet();
    
        sense = new String[entrySet.size()];
        i = 0;
        for(Map.Entry<String, JsonElement> entry : entrySet) {
            speed[i] = entry.getKey() + ": " + entry.getValue().getAsString();
            i++;
        }
        
        stats = new StatBlock(source);
        
        dmgVulner = copyIntoStr(source.get("damage_vulnerabilities").getAsJsonArray());
        dmgResist = copyIntoStr(source.get("damage_resistances").getAsJsonArray());
        dmgImmune = copyIntoStr(source.get("damage_immunities").getAsJsonArray());
        condImmune = copyIntoStr(source.get("condition_immunities").getAsJsonArray());
        } catch (Exception ex) {
            System.out.println("Error Parsing Monster JSON: " + ex.getMessage());
        }
    }
    
    private String[] copyIntoStr(JsonArray jArray) {
        String arr[] = new String[jArray.size()];
        
        for(int i = 0; i < jArray.size(); i++) {
            arr[i] = jArray.get(i).getAsString();
        }
        
        return arr;
    }
    
   
}

package main.java.artificer.stats;

import java.util.Arrays;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class StatBlock {
    private Stat strength;
    private Stat dexterity;
    private Stat constitution;
    private Stat intelligence;
    private Stat wisdom;
    private Stat charisma;
    
    private Proficiency prof[];
    
    public StatBlock(JsonObject source) {
        //Physical Stats
        strength = new Stat(source.get("strength").getAsInt());
        dexterity = new Stat(source.get("dexterity").getAsInt());
        constitution = new Stat(source.get("constitution").getAsInt());
        //Mental Stats
        intelligence = new Stat(source.get("intelligence").getAsInt());
        wisdom = new Stat(source.get("wisdom").getAsInt());
        charisma = new Stat(source.get("charisma").getAsInt());
        
        JsonArray profArray = source.getAsJsonArray("proficiencies");
        prof = new Proficiency[profArray.size()];
        
        for(int i = 0; i < profArray.size(); i++) {
            prof[i] = new Proficiency(
                    profArray.get(i).getAsJsonObject().get("name").getAsString(),
                    profArray.get(i).getAsJsonObject().get("value").getAsInt()
                    );
        }
        
    }

    public Stat getSTR() {
        return strength;
    }


    public Stat getDEX() {
        return dexterity;
    }


    public Stat getCON() {
        return constitution;
    }


    public Stat getINT() {
        return intelligence;
    }


    public Stat getWIS() {
        return wisdom;
    }

    public Stat getCHA() {
        return charisma;
    }


    public Iterable<Proficiency> getProfs() {
        return Arrays.asList(prof);
    }

    
    

}

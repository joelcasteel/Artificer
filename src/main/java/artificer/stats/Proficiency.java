package main.java.artificer.stats;

import com.google.gson.JsonObject;

public class Proficiency {
    private String name = "";
    private String url = "";
    private int value = 0;
    private boolean isSkill = false;
    
    public Proficiency(JsonObject source) {
        String splits[] = source.get("name").getAsString().split(": ");
        try {
            isSkill = splits[0].contentEquals("Skill");
            name = splits[1];
        } catch (Exception ex ) {
            System.out.println("Delimeter Missing from API Source (Proficiency CTOR)");
            name = splits[0];
        }
        url = source.get("url").getAsString();
        value = source.get("value").getAsInt();
    }
    
    public Proficiency(String pName, int val, boolean skill) {
        name = pName;
        value = val;
        isSkill = skill;
    }
    
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public int getValue() {
        return value;
    }
    public boolean isSkill() {
        return isSkill;
    }
    
    
    
}

package main.java.artificer.stats;

import com.google.gson.JsonObject;

public class Proficiency {
    private String name = "";
    private String url = "";
    private int value = 0;
    
    public Proficiency(JsonObject source) {
        name = source.get("name").getAsString();
        url = source.get("url").getAsString();
        value = source.get("value").getAsInt();
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
    
    
}

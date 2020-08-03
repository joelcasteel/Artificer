package main.java.artificer.stats;

import com.google.gson.JsonObject;


/**
 * Proficiency is a class that contains all the necessary information to describe a skill or save
 * proficiency.
 * 
 * Rather than creating a modifier to add and calculate, a proficiency provides a new score to
 * override the old. 
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class Proficiency {
    
    //The basic fields to describe a proficiency. They are initiallly empty.
    private String name = "";
    private String url = "";
    private int value = 0;
    private boolean isSkill = false;
    
    /**
     * Construct a proficiency from a JsonObject source.
     * 
     * @param source The JsonObject to build off of.
     */
    public Proficiency(JsonObject source) {
        //The name must be split up so that we can determine the type.
        String splits[] = source.get("name").getAsString().split(": ");
        
        try {
            //The split string is saved into two different fields.
            isSkill = splits[0].contentEquals("Skill");
            name = splits[1];
            
        } catch (Exception ex ) {
            //If something goes wrong, we just take the name and assume it's a saving throw.
            System.out.println("Delimeter Missing from API Source (Proficiency CTOR)");
            name = splits[0];
        }
        
        url = source.get("url").getAsString();
        value = source.get("value").getAsInt();
    }
    
    /**
     * Construct a proficiency from a name, value, and skill (T/F)
     * 
     * @param pName Name of the prof.
     * @param val The value to use
     * @param skill T = Skill, F = Save
     */
    public Proficiency(String pName, int val, boolean skill) {
        name = pName;
        value = val;
        isSkill = skill;
    }
    
    public Proficiency() {
        name = "";
        value = 0;
        isSkill = true;
    }

    public Proficiency copyProficiency() {
        return new Proficiency(name, value, isSkill);
    }
    
    /**
     * 
     * @return Name of Prof
     */
    public String getName() {
        return name;
    }
    
    public void setName(String pName) {
        name = pName;
    }
    
    /**
     * 
     * @return URL of Prof in API
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * 
     * @return Value of Prof
     */
    public int getValue() {
        return value;
    }
    
    public void setValue(int pVal) {
        value = pVal;
    }
    
    /**
     *
     * @return T = Skill, F = Save
     */
    public boolean isSkill() {
        return isSkill;
    }
    
    public void setSkill(boolean pSkill) {
        isSkill = pSkill;
    }
    
    public JsonObject toJson() {
        JsonObject p = new JsonObject();
        
        
        if(isSkill) {
            p.addProperty("name", "Skill: " + name);
        } else {
            p.addProperty("name", "Saving Throw: " + name);
        }
        p.addProperty("value", value);
        p.addProperty("url", url);
        
        
        return p;
    }
    
    
    
}

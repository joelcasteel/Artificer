package main.java.artificer.stats;

import com.google.gson.JsonObject;

/**
 * The main data class for Monsters.
 * 
 * Has to be serialized from the JSON source via a method.
 * The GSON automatic method doesn't really work.
 * 
 * @author Joel Casteel
 * @version June 2020
 */
public class Monster {
    
    //Primitive Fields
    private String name;
    private String size;
    private String type;
    private String alignment;
    
    private int AC;
    private int HP;
    
    //A Hitdice Object
    private HitDice hitDice;
    
    //A statblock Object
    private StatBlock stats;
    
    /**
     * We have to deserialize the object manually due to the many custom objects within.
     * Pretty straight-forward, just kind of labor intensive.
     * 
     * @param source The JsonObject this Monster will be constructed from
     */
    public Monster(JsonObject source) {
        name = source.get("name").getAsString();
        size = source.get("size").getAsString();
        type = source.get("type").getAsString();
        alignment = source.get("alignment").getAsString();
        
        AC = source.get("armor_class").getAsInt();
        HP = source.get("hit_points").getAsInt();
        hitDice = new HitDice(source.get("hit_dice").getAsString());
        
        stats = new StatBlock(source);
    }
    
    /**
     * Returns the Statblock object belonging to a monster
     * 
     * This method will be used a lot. Anytime a stat or proficiency has to be retrived,
     * getStats() is the gatekeeper.
     * 
     * @return Statblock 
     */
    public StatBlock getStats() {
        return stats;
    }
    
    /**
     * Returns the HitDice object belonging to a monster
     * @return
     */
    public HitDice getHitDice() {
        return hitDice;
    }
    
    /**
     * 
     * @return Name of Monster
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return Size Class of the Monster
     */
    public String getSize() {
        return size;
        
    }
    
    /**
     * 
     * @return Type of the Monster
     */
    public String getType() {
        return type;
        
    }
    
    /**
     * 
     * @return Alignment of the Monster
     */
    public String getAlignment() {
        return alignment;
        
    }
    
    /**
     * 
     * @return Armor class of the Monster
     */
    public int getAC() {
        return AC;
        
    }
    
    /**
     * 
     * @return HP of the Monster
     */
    public int getHP() {
        return HP;
        
    }
    
    
    
   
}

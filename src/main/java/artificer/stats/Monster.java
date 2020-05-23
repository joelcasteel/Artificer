package main.java.artificer.stats;

import com.google.gson.JsonObject;

public class Monster {
    
    private String name;
    private String size;
    private String type;
    private String alignment;
    
    private int AC;
    private int HP;
    
    private HitDice hitDice;
    private StatBlock stats;
    
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
    
    public StatBlock getStats() {
        return stats;
    }
    
    public HitDice getHitDice() {
        return hitDice;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSize() {
        return size;
        
    }
    
    public String getType() {
        return type;
        
    }
    
    public String getAlignment() {
        return alignment;
        
    }
    
    public int getAC() {
        return AC;
        
    }
    
    public int getHP() {
        return HP;
        
    }
    
    
    
   
}

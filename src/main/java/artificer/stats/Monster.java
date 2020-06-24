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
    
    public Monster() {
        name = "";
        size = "";
        type = "";
        alignment = "";
        
        AC = 0;
        HP = 0;
        
        hitDice = null;
        stats = null;
    }
    
    public Monster copyMonster() {
        Monster copy = new Monster();
        copy.setName(name);
        copy.setType(type);
        copy.setSize(size);
        copy.setAlignment(alignment);
        
        copy.setAC(AC);
        copy.setHP(HP);
        
        copy.setStats(stats.copyStatBlock());
        copy.setHitDice(hitDice.copyHitDice());
        
        
        
        return copy;
        
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
    
    public void setStats(StatBlock pStats) {
        stats = pStats;
    }
    
    
    /**
     * Returns the HitDice object belonging to a monster
     * @return
     */
    public HitDice getHitDice() {
        return hitDice;
    }
    
    
    public void setHitDice(HitDice pDice) {
        hitDice = pDice;
    }
    
    /**
     * 
     * @return Name of Monster
     */
    public String getName() {
        return name;
    }
    
    public void setName(String pName) {
        name = pName;
    }
    
    /**
     * 
     * @return Size Class of the Monster
     */
    public String getSize() {
        return size;
        
    }
    
    public void setSize(String pSize) {
        size = pSize;
    }
    
    /**
     * 
     * @return Type of the Monster
     */
    public String getType() {
        return type;
        
    }
    
    public void setType(String pType) {
        type = pType;
    }
    
    
    /**
     * 
     * @return Alignment of the Monster
     */
    public String getAlignment() {
        return alignment;
        
    }
    
    public void setAlignment(String pAlign) {
        alignment = pAlign;
    }
    
    
    /**
     * 
     * @return Armor class of the Monster
     */
    public int getAC() {
        return AC;
        
    }
    
    public void setAC(int pAC) {
        AC = pAC;
    }
    
    
    /**
     * 
     * @return HP of the Monster
     */
    public int getHP() {
        return HP;
        
    }
    
    public void setHP(int pHP) {
        HP = pHP;
    }
    
    public JsonObject toJson() {
        try {
        JsonObject monster = new JsonObject();
        monster.addProperty("name", name);
        monster.addProperty("type", type);
        monster.addProperty("alignment", alignment);
        monster.addProperty("armor_class", AC);
        monster.addProperty("hit_points", HP);
        monster.addProperty("hit_dice", hitDice.toString());
        
        for(String s: Stat.STATNAME) {
            monster.addProperty(s, stats.getStat(s).getScore());
        }
        
        monster.add("proficiencies", stats.getProfJson());
        
        return monster;
                
        } catch (Exception ex) {
            System.out.println("Error Serializing Monster: " + ex.getMessage());
            return null;
        }
        
    }
   
}

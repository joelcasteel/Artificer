package main.java.artificer.stats;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * The Factory class for new Monster Objects.
 * 
 * I'm not sure if this class is obsolete yet.
 * Seems like it could be more useful as I add in more objects to deserialize.
 * 
 * @author Joel Casteel
 * @version May 2020
 *
 */
public class MonsterFactory {
    /**
     * Create a new Monster Object from a String of JSON content
     * @param response JSON String
     * @return The new Monster Instance
     */
    public static Monster createMonster(String response) {
        JsonObject source = JsonParser.parseString(response).getAsJsonObject();
        Monster monster = new Monster(source);
        return monster;
    }
    
    public static Monster copyInstance(Monster monster) {
        Monster copy = new Monster();
        
        copy.setName(monster.getName());
        copy.setType(monster.getType());
        copy.setSize(monster.getSize());
        copy.setAlignment(monster.getAlignment());
        
        copy.setAC(monster.getAC());
        copy.setHP(monster.getHP());
        
        
        return copy;
    }
}

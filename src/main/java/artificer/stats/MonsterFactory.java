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
    public static Monster createMonster(String response) {
        JsonObject source = JsonParser.parseString(response).getAsJsonObject();
        Monster monster = new Monster(source);
        return monster;
    }
}

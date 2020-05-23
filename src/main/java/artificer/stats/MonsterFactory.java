package main.java.artificer.stats;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MonsterFactory {
    public static Monster createMonster(String response) {
        JsonObject source = JsonParser.parseString(response).getAsJsonObject();
        Monster monster = new Monster(source);
        return monster;
    }
}

package main.java.artificer.stats;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MonsterFactory {
    public static Monster createMonster(String response) {
        JsonObject source = JsonParser.parseString(response).getAsJsonObject();
        Gson gson = new Gson();
        Monster monster = gson.fromJson(source, Monster.class);
        //monster.setStats(new StatBlock(source));
        return monster;
    }
}

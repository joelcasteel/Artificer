package main.java.artificer.ui.elements;


import com.google.gson.JsonObject;

import javafx.application.Platform;
import main.java.artificer.stats.MonsterFactory;
import main.java.artificier.request.APIClient;
import main.java.artificier.request.Request;

public class MonsterCache implements Request {
    String name = "Empty";
    String index = "Empty";
    String url = "";
    String pre = "https://www.dnd5eapi.co";
    
    SearchBox parentSearch;
    
    public MonsterCache(JsonObject source, SearchBox parent) {

        name = source.get("name").getAsString();
        index = source.get("index").getAsString();
        url = source.get("url").getAsString();
        parentSearch = parent;

    }
    
    
    @Override
    public void handleResponse(String response) {
        System.out.println(response);
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                parentSearch.getParentRibbon()
                .getDetail()
                .getMonsterDetail()
                .setContent(
                        MonsterFactory.createMonster(response));
            }
            
        });
        
    }
    
    public void selected() {
        APIClient.asynchURLRequest(APIClient.createURL(url), this, 30);
        
    }
    
    public String getName() {
        return name;
    }
}


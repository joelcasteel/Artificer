package main.java.artificer.ui.elements;


import com.google.gson.JsonObject;

import javafx.application.Platform;
import main.java.artificer.stats.MonsterFactory;
import main.java.artificier.request.APIClient;
import main.java.artificier.request.Request;

/**
 * Hold one of the search results for a monser from the API
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class MonsterCache implements Request {
    String name = "Empty";
    String index = "Empty";
    String url = "";
    String pre = "https://www.dnd5eapi.co";
    
    SearchBox parentSearch;
    
    /**
     * Construct a new Monster Cache
     * 
     * @param source The JSON search result
     * @param parent The Parent (Search-Box) this belong to.
     */
    public MonsterCache(JsonObject source, SearchBox parent) {

        name = source.get("name").getAsString();
        index = source.get("index").getAsString();
        url = source.get("url").getAsString();
        parentSearch = parent;

    }
    
    /**
     * Handle the response by opening the detail view with the JSON returned from the API
     */
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
    
    /**
     * Make a response to the API.
     */
    public void selected() {
        APIClient.asynchURLRequest(APIClient.createURL(url), this, 30);
        
    }
    
    /**
     * 
     * @return The name of this result
     */
    public String getName() {
        return name;
    }
}


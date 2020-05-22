package main.java.artificer.ui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.google.gson.JsonObject;

import main.java.artificer.stats.Monster;
import main.java.artificer.stats.MonsterFactory;

public class MonsterCache {
    String name = "Empty";
    String index = "Empty";
    String url = "";
    String pre = "https://www.dnd5eapi.co";
    
    SearchBox parentSearch;
    private String responseString = null;
    
    public MonsterCache(JsonObject source, SearchBox parent) {

        name = source.get("name").getAsString();
        index = source.get("index").getAsString();
        url = source.get("url").getAsString();
        parentSearch = parent;

    }
    
    private void asynchURLRequest(String url) {
        System.out.println("Making Request: " + url);
        try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(1))
                .build();
       client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::handleResponse)
                .join();
        } catch (Exception ex) {
            System.out.println("Error making request: " + ex.getMessage());
        }
        
    }
    
    private void handleResponse(String response) {
        System.out.println(response);
        responseString = response;
        
    }
    
    public void selected() {
        asynchURLRequest(pre + url);
        Monster monster = MonsterFactory.createMonster(responseString);
        parentSearch.getParentRibbon().getDetail().getMonsterDetail().setContent(monster);
    }
    
    public String getName() {
        return name;
    }
}


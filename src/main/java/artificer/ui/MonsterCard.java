package main.java.artificer.ui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.MonsterFactory;

public class MonsterCard extends VBox {
    String name = "Empty";
    String index = "Empty";
    String url = "";
    String pre = "https://www.dnd5eapi.co";
    
    static Font cardFont = new Font("sans-serif", 12);
    private Label label = new Label();
    
    SearchBox parentSearch;
    
    private String responseString = null;
    
    public MonsterCard(SearchBox parent) {
        parentSearch = parent;
        label.setFont(cardFont);
        label.setText(name);
        getChildren().add(label);
        setOnMouseClicked(mouseHandler);
    }
    
    public MonsterCard(JsonObject source, SearchBox parent) {
        this(parent);
        name = source.get("name").getAsString();
        index = source.get("index").getAsString();
        url = source.get("url").getAsString();
        
        label.setText(name);
        
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
    
    EventHandler<MouseEvent> mouseHandler = new EventHandler<>() {

        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked on: " + name);
            selected();
            
        }
        
    };
    
}

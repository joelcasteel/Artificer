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
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.MonsterFactory;

public class MonsterCard extends ListCell<MonsterCache> {
    
    

    
    @Override
    public void updateItem(MonsterCache cache, boolean empty) {
        super.updateItem(cache, empty);
        if(empty) {
            setText(null);
        } else {
            setText(cache.getName());
            setOnMouseClicked(mouseHandler);
        }
        
    }
    
    
    
    EventHandler<MouseEvent> mouseHandler = new EventHandler<>() {

        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked on: " + getItem().getName());
            getItem().selected();
            
        }
        
    };
    
}

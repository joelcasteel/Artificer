package main.java.artificer.ui;

import com.google.gson.JsonObject;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MonsterCard extends VBox {
    String name = "Empty";
    String index = "Empty";
    String url = "";
    
    static Font cardFont = new Font("sans-serif", 12);
    private Label label = new Label();
    
    public MonsterCard() {
        label.setFont(cardFont);
        label.setText(name);
        getChildren().add(label);
        setOnMouseClicked(mouseHandler);
    }
    
    public MonsterCard(JsonObject source) {
        this();
        name = source.get("name").getAsString();
        index = source.get("index").getAsString();
        url = source.get("url").getAsString();
        
        label.setText(name);
        
    }
    
    EventHandler<MouseEvent> mouseHandler = new EventHandler<>() {

        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked on: " + name);
            
        }
        
    };
    
}

package main.java.artificer.ui.detail;

import com.google.gson.JsonObject;

import javafx.scene.control.Label;
import main.java.artificer.stats.Monster;

public class MonsterDetail extends Detail {
    public MonsterDetail() {
        getChildren().add(new Label("MONSTER DETAILS"));
    }
    
    public void setContent(Monster source) {
        
    }
}

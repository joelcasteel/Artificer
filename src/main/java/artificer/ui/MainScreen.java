package main.java.artificer.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainScreen extends BorderPane {
    HBox topBox = new HBox();
    
    public MainScreen() {
        buildTopBox();
        setTop(topBox);
    }
    
    public void buildTopBox() {
        topBox.getChildren().add(new Button("HEllo"));
    }
    
    
}

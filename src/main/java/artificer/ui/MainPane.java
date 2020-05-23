package main.java.artificer.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainPane extends BorderPane {
    
    HBox workbox;
   
    SideRibbon sideRibbon;
    
    ImageView icon;
    
    Button search;
    Button monsters;
    Button items;
    Button encounters;
    Button add;
    
    
    BorderPane content;
    
    StackPane sidePane;
    VBox searchMenu;
    VBox encounterMenu;
    VBox monsterMenu;
    VBox itemMenu;
    
    Group searchGroup;
    Group encounterGroup;
    Group monsterGroup;
    Group itemGroup;
    
    StackPane workspace;
    
    
    public MainPane() {
        
        workbox = new HBox();
        
        sideRibbon = new SideRibbon();
        
        
        workspace = new StackPane();
        HBox.setHgrow(workspace, Priority.ALWAYS);
        workspace.getChildren().add(new Label("CONTENT"));
        workspace.setAlignment(Pos.TOP_LEFT);
        
        workbox.getChildren().add(sideRibbon);
        workbox.getChildren().add(workspace);
        
        setCenter(workbox);
        

    }
    
    
    
    
}

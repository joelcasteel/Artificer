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
import main.java.artificer.ui.menu.MenuWrapper;

/**
 * A main panel that holds the majority of application content.
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class MainPane extends BorderPane {
    
    HBox workbox;
   
    SideRibbon sideRibbon;
    
    MenuWrapper menu;
    
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
    
    
    /**
     * Construct the new MainPane with all the substructures
     */
    public MainPane() {
        
        workbox = new HBox();
        
        sideRibbon = new SideRibbon();
        
        menu = MenuWrapper.getInstance();
        
        
        workspace = new StackPane();
        HBox.setHgrow(workspace, Priority.ALWAYS);
        workspace.getChildren().add(new Label("CONTENT"));
        workspace.setAlignment(Pos.TOP_LEFT);
        
        workbox.getChildren().add(menu);
        workbox.getChildren().add(workspace);
        
        setCenter(workbox);
        
        setLeft(sideRibbon);
        

    }
    
    
    
    
}

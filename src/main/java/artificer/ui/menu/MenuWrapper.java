package main.java.artificer.ui.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import main.java.artificer.ui.SideRibbon;

public class MenuWrapper extends BorderPane {
    
    private StackPane holder = new StackPane();
    
    private SearchMenu searchMenu;
    private MonsterDetailMenu monsterMenu;
    
    private HBox header = new HBox();
    private Button backButton = new Button("<-");
    private Button exitButton = new Button("x");
    private Label titleLabel = new Label("Title");
    
    SideRibbon parent;
    
    private Stack<Collapsible> chain;
    
    private HashMap<String, Collapsible> menus = new HashMap<>();
    
    private Collapsible current = null;
    
    
    private static MenuWrapper instance = null;
    
    public static MenuWrapper getInstance() {
        if(instance == null) {
            instance = new MenuWrapper();
        }
        return instance;
    }

    
    private MenuWrapper() {
        setId("menu-wrapper");
        chain = new Stack<>();
        
        
        backButton.setOnAction(backHandler);
        header.setId("menu-header");
        header.getChildren().addAll(
                backButton, titleLabel, exitButton
                );
        
        this.setTop(header);
        
        
        searchMenu = new SearchMenu();
        searchMenu.collapse();
        menus.put(searchMenu.getTitle(), searchMenu);
        
        monsterMenu = new MonsterDetailMenu();
        monsterMenu.collapse();
        menus.put(monsterMenu.getTitle(), monsterMenu);
        
        
        
        holder.setId("menu-holder");
        holder.getChildren().add(searchMenu);
        this.setCenter(holder);
        
    }
    
    public void changeContext(String title) {
        if(current != null) {
            current.collapse();
        }
        
        current = menus.get(title);
        current.expand();
    }
    
    
    EventHandler<ActionEvent> backHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
        }
        
    };
    
    

}

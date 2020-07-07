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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import main.java.artificer.ui.SideRibbon;

public class MenuWrapper extends BorderPane implements Collapsible {
    
    private StackPane holder = new StackPane();
    
    private SearchMenu searchMenu;
    private MonsterDetailMenu monsterMenu;
    
    private HBox header = new HBox();
    private Button backButton = new Button("BACK");
    private Button exitButton = new Button("EXIT");
    private Label titleLabel = new Label("SIDE MENU");
    
    SideRibbon parent;
    
    private Stack<Collapsible> chain;
    
    private HashMap<String, Collapsible> menus = new HashMap<>();
    
    private Collapsible current = null;
    
    private static MenuWrapper instance = null;
    
    private static boolean visible = false;
    
    
    private static final int MENU_WIDTH = 420;
    private static final int HEADER_HEIGHT = 30;
    
    public static MenuWrapper getInstance() {
        if(instance == null) {
            instance = new MenuWrapper();
        }
        return instance;
    }

    
    private MenuWrapper() {
        setId("menu-wrapper");
        chain = new Stack<>();
        
        setPrefWidth(MENU_WIDTH);
        
        header.setPrefHeight(HEADER_HEIGHT);
        
        titleLabel.getStyleClass().add("brutal-label");
        titleLabel.setId("menu-header-label");
        titleLabel.setPrefWidth(MENU_WIDTH - 120);
        titleLabel.setPrefHeight(HEADER_HEIGHT);
        titleLabel.setMinHeight(HEADER_HEIGHT);
        titleLabel.setAlignment(Pos.CENTER);
        
        
        backButton.getStyleClass().add("brutal-button");
        backButton.setId("menu-header-back-button");
        backButton.setPrefWidth(60);
        backButton.setPrefHeight(HEADER_HEIGHT);
        
        
        exitButton.getStyleClass().add("brutal-button");
        exitButton.setId("menu-header-exit-button");
        exitButton.setPrefWidth(60);
        exitButton.setPrefHeight(HEADER_HEIGHT);
        
        
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
        
        setVisible(false);
        setManaged(false);
        
    }
    
    public void changeContext(String title) {
        if(current != null) {
            current.collapse();
        }
        
        if(menus.containsKey(title)) {
            current = menus.get(title);
            expand();
            titleLabel.setText(title.toUpperCase());
            current.expand();
        }
    }
    
    
    EventHandler<ActionEvent> backHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
        }
        
    };

    @Override
    public void collapse() {
        setVisible(false);
        setManaged(false);
        
    }


    @Override
    public void expand() {
        // TODO Auto-generated method stub
        setVisible(true);
        setManaged(true);
        
    }


    @Override
    public void toggle() {
        if (isManaged() && isVisible()) {
            collapse();
        } else {
            expand();
        }
        
    }


    @Override
    public String getTitle() {
        return "Wrapper";
    }
    
    

}

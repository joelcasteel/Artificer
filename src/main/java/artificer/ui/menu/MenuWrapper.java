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
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import main.java.artificer.stats.Monster;
import main.java.artificer.ui.SideRibbon;

public class MenuWrapper extends BorderPane implements Collapsible {
    
    private StackPane holder = new StackPane();
    private ScrollPane scroller = new ScrollPane(holder);
    
    
    private SearchMenu searchMenu;
    private MonsterDetailMenu monsterMenu;
    private TestMenu testMenu;
    
    private HBox header = new HBox();
    private GridPane headerGrid = new GridPane();
    private Button backButton = new Button("BACK");
    private Button exitButton = new Button("EXIT");
    private Label titleLabel = new Label("SIDE MENU");
    
    SideRibbon parent;
    
    private Stack<Collapsible> chain;
    
    private HashMap<MenuTitle, Collapsible> menus = new HashMap<>();
    
    private Collapsible current = null;
    
    private static MenuWrapper instance = null;
    
    private static boolean visible = false;
    
    
    public static final int MENU_WIDTH = 420;
    public static final int HEADER_HEIGHT = 30;
    
    public enum MenuTitle {
        Search,
        Details,
        Wrapper,
        Test
    }
    
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
        headerGrid.setPrefHeight(HEADER_HEIGHT);
        
        
        titleLabel.getStyleClass().add("brutal-label");
        titleLabel.setId("menu-header-label");
        
        
        
        
        backButton.getStyleClass().add("brutal-button");
        backButton.setId("menu-header-back-button");
        backButton.setPrefWidth(60);
        backButton.setPrefHeight(HEADER_HEIGHT);
        
        
        exitButton.getStyleClass().add("brutal-button");
        exitButton.setId("menu-header-exit-button");
        exitButton.setPrefHeight(HEADER_HEIGHT);
        exitButton.setPrefWidth(60);
        
        
        backButton.setOnAction(backHandler);
        
        headerGrid.setId("menu-header");
        headerGrid.setAlignment(Pos.CENTER);
        /*header.getChildren().addAll(
                backButton, titleLabel, exitButton
                );*/
        
        headerGrid.getColumnConstraints().addAll(
                new ColumnConstraints(60, 60, 60, Priority.ALWAYS, HPos.CENTER, true),
                new ColumnConstraints(MENU_WIDTH-120, MENU_WIDTH-120, MENU_WIDTH-120, Priority.ALWAYS, HPos.CENTER, true),
                new ColumnConstraints(60, 60, 60, Priority.ALWAYS, HPos.CENTER, true)
                );
        
        
        headerGrid.add(backButton, 0, 0);
        headerGrid.add(titleLabel, 1, 0);
        headerGrid.add(exitButton, 2, 0);
        
        
        HBox test = new HBox();
        test.getStyleClass().add("test");
        test.setPrefHeight(100);
        this.setTop(headerGrid);
        
        
        searchMenu = new SearchMenu();
        searchMenu.collapse();
        menus.put(searchMenu.getTitle(), searchMenu);
        
        monsterMenu = new MonsterDetailMenu();
        monsterMenu.collapse();
        menus.put(monsterMenu.getTitle(), monsterMenu);
        
        testMenu = new TestMenu();
        testMenu.collapse();
        menus.put(testMenu.getTitle(), testMenu);
        
        
        scroller.setId("menu-scroller");
        scroller.setFitToHeight(true);
        scroller.setFitToWidth(true);
        scroller.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroller.setVbarPolicy(ScrollBarPolicy.NEVER);
        
        
        
        
        holder.setId("menu-holder");
        holder.getChildren().add(searchMenu);
        holder.getChildren().add(monsterMenu);
        holder.getChildren().add(testMenu);
        this.setCenter(scroller);
        
        setVisible(false);
        setManaged(false);
        
    }
    
    public void changeContext(MenuTitle title) {
        if(current != null) {
            current.collapse();
        }
        
        if(menus.containsKey(title)) {
            current = menus.get(title);
            expand();
            setTitle(title);
            current.expand();
        }
    }
    
    public void setTitle(MenuTitle title) {
        if(title.equals(MenuTitle.Search)) {
            titleLabel.setText("SEARCH MONSTERS");
        } else if(title.equals(MenuTitle.Details)) {
            titleLabel.setText("MONSTER DETAILS");
        } else {
            titleLabel.setText("Where are we?");
        }
    }
    
    public MenuTitle getContext() {
        return current.getTitle();
    }
    
    public boolean isOpen() {
        return visible;
        
    }
    
    public void openContent(Monster content) {
        monsterMenu.setContent(content);
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
        visible = false;
        
    }


    @Override
    public void expand() {
        setVisible(true);
        setManaged(true);
        visible = true;
        
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
    public MenuTitle getTitle() {
        return MenuTitle.Wrapper;
    }
    
    

}

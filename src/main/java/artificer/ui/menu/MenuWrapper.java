package main.java.artificer.ui.menu;

import java.util.HashMap;

import javafx.beans.Observable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuWrapper extends StackPane {
    
    public static final String SEARCH_MENU = "Search";
    public static final String ENCOUNTER_MENU = "Encounter";
    public static final String MONSTER_MENU = "Monster";
    public static final String ITEM_MENU = "Item";

    HashMap<String, SideMenu> menus = new HashMap<>();
    
    String currentMenu = SEARCH_MENU;
    
    
    
    public MenuWrapper() {
        menus.put(SEARCH_MENU, new SearchMenu());
        menus.put(MONSTER_MENU, new MonsterMenu());
        
        getChildren().addAll(menus.values());
    }
    
    public void SwitchContext(String context) {
        if(context.equals(currentMenu)) {
            menus.get(context).toggle();
        } else {
            menus.get(currentMenu).close();
            menus.get(context).toggle();
            currentMenu = context;
        }
    }
    
    
    

    
    
}

package main.java.artificer.ui;

import java.util.HashMap;

import javafx.scene.layout.StackPane;
import main.java.artificer.ui.menu.MonsterMenu;
import main.java.artificer.ui.menu.SearchMenu;
import main.java.artificer.ui.menu.SideMenu;

/**
 * The menu wrapper for handling different menu contexts
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class MenuWrapper extends StackPane {
    
    public static final String SEARCH_MENU = "Search";
    public static final String ENCOUNTER_MENU = "Encounter";
    public static final String MONSTER_MENU = "Monster";
    public static final String ITEM_MENU = "Item";

    HashMap<String, SideMenu> menus = new HashMap<>();
    
    String currentMenu = SEARCH_MENU;
    
    
    /**
     * Construct a new Menu Wrapper
     * 
     * @param ribbon The ribbon that controls menus/details
     */
    public MenuWrapper(SideRibbon ribbon) {
        menus.put(SEARCH_MENU, new SearchMenu(ribbon));
        menus.put(MONSTER_MENU, new MonsterMenu());
        
        getChildren().addAll(menus.values());
    }
    
    /**
     * Change the current Menu Context
     * 
     * @param context
     */
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

package main.java.artificer.ui.menu;

import javafx.scene.control.Label;

/**
 * Custom Monster menu module
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class MonsterMenu extends SideMenu {
    public MonsterMenu() {
        super("Monster");
        
        Label label = new Label("MONSTER MENU");
        Label label2 = new Label("MONSTER MENU");
        getChildren().add(label);
        getChildren().add(label2);
    }
}

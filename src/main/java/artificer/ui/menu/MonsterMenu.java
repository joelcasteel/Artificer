package main.java.artificer.ui.menu;

import javafx.scene.control.Label;

public class MonsterMenu extends SideMenu {
    public MonsterMenu() {
        super("Monster");
        
        Label label = new Label("MONSTER MENU");
        Label label2 = new Label("MONSTER MENU");
        getChildren().add(label);
        getChildren().add(label2);
        //label.getStyleClass().add("label");
    }
}

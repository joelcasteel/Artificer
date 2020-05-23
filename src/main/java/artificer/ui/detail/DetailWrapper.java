package main.java.artificer.ui.detail;

import java.util.HashMap;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import main.java.artificer.ui.SideRibbon;

public class DetailWrapper extends StackPane {

    public static final String MONSTER_DETAIL = "Monster";
    public static final String ITEM_DETAIL = "Item";
    
    HashMap<String, Detail> details = new HashMap<>();
    
    String currentDetail = MONSTER_DETAIL;
    
    SideRibbon parentRibbon;
    
    public DetailWrapper(SideRibbon ribbon) {
        getStylesheets().add(getClass().getResource("/ui/stylesheets/monster-details.css").toString());
        parentRibbon = ribbon;
        details.put(MONSTER_DETAIL, new MonsterDetail());
        
        getChildren().addAll(details.values());
    }
    
    public MonsterDetail getMonsterDetail() {
        return (MonsterDetail) details.get(MONSTER_DETAIL);
    }
}

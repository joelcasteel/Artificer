package main.java.artificer.ui.detail;

import java.util.HashMap;

import javafx.scene.layout.StackPane;

public class DetailWrapper extends StackPane {

    public static final String MONSTER_DETAIL = "Monster";
    public static final String ITEM_DETAIL = "Item";
    
    HashMap<String, Detail> details = new HashMap<>();
    
    String currentDetail = MONSTER_DETAIL;
    
    public DetailWrapper() {
        details.put(MONSTER_DETAIL, new MonsterDetail());
        
        getChildren().addAll(details.values());
    }
}

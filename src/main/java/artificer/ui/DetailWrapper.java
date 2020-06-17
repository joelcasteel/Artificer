package main.java.artificer.ui;

import java.util.HashMap;

import javafx.scene.layout.StackPane;
import main.java.artificer.ui.detail.Detail;
import main.java.artificer.ui.detail.MonsterDetail;

/**
 * The wrapper for switching detail contexts.
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class DetailWrapper extends StackPane {

    public static final String MONSTER_DETAIL = "Monster";
    public static final String ITEM_DETAIL = "Item";
    
    HashMap<String, Detail> details = new HashMap<>();
    
    String currentDetail = MONSTER_DETAIL;
    
    SideRibbon parentRibbon;
    
    /**
     * Construct the detail view.
     * 
     * @param ribbon The side ribbon that will be used to control menus/details.
     */
    public DetailWrapper(SideRibbon ribbon) {
        getStylesheets().add(getClass().getResource(App.stylesheet).toString());
        parentRibbon = ribbon;
        details.put(MONSTER_DETAIL, new MonsterDetail());
        
        getChildren().addAll(details.values());
    }
    
    /**
     * 
     * @return The Monster Detail View used in this wrapper.
     */
    public MonsterDetail getMonsterDetail() {
        return (MonsterDetail) details.get(MONSTER_DETAIL);
    }
}

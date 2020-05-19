package main.java.artificer.ui.menu;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import main.java.artificer.ui.SearchBox;
import main.java.artificer.ui.SideRibbon;

public class SearchMenu extends SideMenu {
    SearchBox searcher;
    
    
    
    public SearchMenu(SideRibbon ribbon) {
        super("Search");
        
        searcher = new SearchBox(ribbon);
        getChildren().add(searcher);
        
        
        
    }
    
    
    
}

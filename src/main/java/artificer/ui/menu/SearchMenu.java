package main.java.artificer.ui.menu;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import main.java.artificer.ui.SearchBox;

public class SearchMenu extends SideMenu {
    SearchBox searcher;
    
    
    
    public SearchMenu() {
        super("Search");
        
        searcher = new SearchBox();
        getChildren().add(searcher);
        
        
        
    }
    
    
    
}

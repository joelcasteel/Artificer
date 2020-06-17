package main.java.artificer.ui.menu;


import main.java.artificer.ui.SideRibbon;
import main.java.artificer.ui.elements.SearchBox;

public class SearchMenu extends SideMenu {
    SearchBox searcher;
    
    
    
    public SearchMenu(SideRibbon ribbon) {
        super("Search");
        
        searcher = new SearchBox(ribbon);
        getChildren().add(searcher);
        
    }
    
    
    
    
}

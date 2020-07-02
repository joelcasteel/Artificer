package main.java.artificer.ui.menu;

import javafx.scene.layout.VBox;
import main.java.artificer.ui.SideRibbon;
import main.java.artificer.ui.elements.SearchBox;

public class SearchMenu extends VBox implements Collapsible {
    
    private SearchBox searcher;
        
    public SearchMenu() {
        searcher = new SearchBox();
        getChildren().add(searcher);
    }

    @Override
    public void collapse() {
        setManaged(false);
        setVisible(false);
        
    }

    @Override
    public void expand() {
        setManaged(true);
        setVisible(true);
        
    }

    @Override
    public void toggle() {
        if (isManaged() && isVisible()) {
            collapse();
        } else {
            expand();
        }
        
    }

    @Override
    public String getTitle() {
        return "Search Monsters";
    }

}

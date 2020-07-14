package main.java.artificer.ui.menu;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.java.artificer.ui.menu.MenuWrapper.MenuTitle;

public class TestMenu extends VBox implements Collapsible {
    
    public TestMenu() {
        this.setPadding(new Insets(10));
        BorderPane pane = new BorderPane();
        pane.setPrefSize(300,300);
        pane.getStyleClass().add("test-pane");
        
        VBox box = new VBox();
        box.getStyleClass().add("test");
        
        pane.setCenter(box);
        getChildren().add(pane);
        
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
    public MenuTitle getTitle() {
        return MenuTitle.Test;
    }

}

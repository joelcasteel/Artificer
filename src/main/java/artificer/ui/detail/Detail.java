package main.java.artificer.ui.detail;

import javafx.scene.layout.VBox;

public class Detail extends VBox {
    protected boolean open;
    
    public Detail() {
        getStylesheets().add("/main/resources/ui/styleSheets/side-menu.css");
        getStyleClass().add("root");
        
        open = false;
        setVisible(open);
        setManaged(open);
        
        setPrefWidth(240);
    }
    
    public boolean isOpen() {
        return open;
    }
    
    public void toggle() {
        open = !open;
        setVisible(open);
        setManaged(open);
    }
    
    public void close() {
        open = false;
        setVisible(open);
        setManaged(open);
    }
    
    public void open() {
        open = true;
        setVisible(open);
        setManaged(open);
    }
    
    
}

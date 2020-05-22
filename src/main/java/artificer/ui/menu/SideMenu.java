package main.java.artificer.ui.menu;
import javafx.scene.layout.VBox;


public abstract class SideMenu extends VBox {
    
    protected String name;
    protected boolean open;
    
    
    
    public SideMenu(String pName) {
        getStylesheets().add(getClass().getResource("/ui/styleSheets/side-menu.css").toString());
        getStyleClass().add("root");
        
        name = pName;
        open = false;
        setVisible(open);
        setManaged(open);
        
        setPrefWidth(240);
    }
    
    public boolean isOpen() {
        return open;
    }
    
    public String getName() {
        return name;
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

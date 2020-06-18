package main.java.artificer.ui.menu;
import javafx.scene.layout.VBox;
import main.java.artificer.ui.App;

/**
 * The abstract definition for a SideMenu
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public abstract class SideMenu extends VBox {
    
    protected String name;
    protected boolean open;
    
    
    /**
     * Construct a new SideMenu
     * 
     * @param pName The name of the menu
     */
    public SideMenu(String pName) {
        getStylesheets().add(getClass().getResource(App.stylesheet).toString());
        getStyleClass().add("side-menu");
        
        name = pName;
        open = false;
        setVisible(open);
        setManaged(open);
        
        setPrefWidth(240);
    }
    
    /**
     * 
     * @return Is this menu open?
     */
    public boolean isOpen() {
        return open;
    }
    
    /**
     * 
     * @return name of the menu
     */
    public String getName() {
        return name;
    }
    
    /**
     * Toggle the menu
     */
    public void toggle() {
        open = !open;
        setVisible(open);
        setManaged(open);
    }
    
    /**
     * Close the menu
     */
    public void close() {
        open = false;
        setVisible(open);
        setManaged(open);
    }
    
    /**
     * Open the menu
     */
    public void open() {
        open = true;
        setVisible(open);
        setManaged(open);
    }
    

}

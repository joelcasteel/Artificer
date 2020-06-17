package main.java.artificer.ui.detail;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The Detail control class. 
 * It is extended to create new Detail Menu Modules
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public abstract class Detail extends BorderPane {
    protected boolean open;
    protected ScrollPane layout;
    protected HBox header;
    protected Label headerLabel;
    protected Button backButton;
    protected VBox holder;
    
    /**
     * Construct a new Detail.
     */
    public Detail() {
        setId("detail-menu");
        
        layout = new ScrollPane();
        layout.setHbarPolicy(ScrollBarPolicy.NEVER);
        
        
        header = new HBox();
        header.getStyleClass().add("hbox");
        header.setSpacing(12);
        header.setAlignment(Pos.CENTER_LEFT);
        
        
        headerLabel = new Label();
        backButton = new Button();
        backButton.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/exit.png").toString())));
        backButton.setOnAction(buttonHandler);
        
        header.getChildren().addAll(backButton, headerLabel);
        setTop(header);
        
        
        
        holder = new VBox();
        layout.setContent(holder);
        
        open = false;
        setVisible(open);
        setManaged(open);
        
        setPrefWidth(360);
        
        setCenter(layout);
    }
    
    /**
     * 
     * @return Is the detail menu open currently
     */
    public boolean isOpen() {
        return open;
    }
    
    /**
     * Toggle whether the menu is open
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
    
    
    /**
     * Event Handler for the back button.
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {

        @Override
        public void handle(ActionEvent event) {
            if(event.getSource().equals(backButton)) {
                close();
            }
            
        }
        
    };
    
    
}

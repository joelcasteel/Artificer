package main.java.artificer.ui.elements;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * Custom Cell for a ListView of MonsterCache
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class MonsterCell extends ListCell<MonsterCache> {
    
    private Label label = new Label();
    private StackPane holder = new StackPane();
    /**
     * Construct a new MonsterCell
     */
    public MonsterCell() {
        getStyleClass().add("brutal-list-cell");
        holder.getStyleClass().add("brutal-cell-holder");
        holder.getChildren().add(label);
    }

    
    @Override
    public void updateItem(MonsterCache cache, boolean empty) {
        super.updateItem(cache, empty);
        
        if(empty) {
            setText(null);
            setGraphic(null);
            
        } else {
            label.setText(cache.getName());
            setGraphic(holder);
            setOnMouseClicked(mouseHandler);
            
        }
    }
    
    
    /**
     * Handler for the Monster Cell.
     * This calls the selected method which in turn makes a request to the API for more data.
     */
    EventHandler<MouseEvent> mouseHandler = new EventHandler<>() {

        @Override
        public void handle(MouseEvent event) {
            if(getItem() != null) {
            System.out.println("Clicked on: " + getItem().getName());
            getItem().selected();
            }
            
        }
        
    };
    
}

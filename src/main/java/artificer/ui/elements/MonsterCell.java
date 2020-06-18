package main.java.artificer.ui.elements;

import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;

/**
 * Custom Cell for a ListView of MonsterCache
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class MonsterCell extends ListCell<MonsterCache> {
    
    /**
     * Construct a new MonsterCell
     */
    public MonsterCell() {
        getStyleClass().add("cool-list-cell");
    }

    
    @Override
    public void updateItem(MonsterCache cache, boolean empty) {
        super.updateItem(cache, empty);
        
        if(empty) {
            setText(null);
            
        } else {
            setText(cache.getName());
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

package main.java.artificer.ui;


import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;

public class MonsterCard extends ListCell<MonsterCache> {
    
    public MonsterCard() {
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

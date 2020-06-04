package main.java.artificer.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.ListView;

public class OpenListView<T> extends ListView<T> {
    
    private int cellHeight;
    
    public OpenListView(int cellSize) {
        super();
        cellHeight = cellSize;
        
        
    }
    
    
    
}

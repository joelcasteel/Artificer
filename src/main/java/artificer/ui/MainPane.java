package main.java.artificer.ui;

import javafx.scene.control.TabPane;
import main.java.artificer.ui.tabs.*;

public class MainPane extends TabPane {
    
    
    public MainPane() {
        getTabs().add(new WorktableTab());
        getTabs().add(new MonsterTab());
        getTabs().add(new ItemTab());
    }
    
    
    
}

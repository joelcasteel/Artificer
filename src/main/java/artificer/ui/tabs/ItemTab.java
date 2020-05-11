package main.java.artificer.ui.tabs;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemTab extends Tab {
    public ItemTab() {
        setText("Items");
        setClosable(false);
        
        Image icon = new Image("main/resources/ui/icons/items.png");
        setGraphic(new ImageView(icon));
    }
}

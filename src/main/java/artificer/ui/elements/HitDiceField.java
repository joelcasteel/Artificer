package main.java.artificer.ui.elements;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class HitDiceField extends HBox {
    
    public TextField diceField = new TextField();
    public Label dLabel = new Label("D");
    public TextField numberField = new TextField();
    
    public HitDiceField() {
        diceField.getStyleClass().add("");
    }

}

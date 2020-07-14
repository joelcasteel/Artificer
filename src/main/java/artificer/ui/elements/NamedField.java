package main.java.artificer.ui.elements;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NamedField extends GridPane {
    private Label label;
    private TextField field;
    
    public NamedField(String name) {
        label = new Label(name);
        field = new TextField();
        field.setPromptText(name);
        
        add(label, 0, 0);
        add(field, 1, 0);
    }
    
    public String getText() {
        return field.getText();
    }
    
    public void setText(String text) {
        field.setText(text);
    }
    

}

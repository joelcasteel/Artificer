package main.java.artificer.ui.elements;
import javafx.scene.control.TextField;

public class NumberField extends TextField {
    public int getNumericValue() throws NumberFormatException {
        try { 
            int value = Integer.parseInt(getText());
            
            return value;
            
        } catch (NumberFormatException ex) {
            throw new NumberFormatException();
            
        }
    }
}

package main.java.artificer.ui.elements;

import javafx.scene.control.TextField;

/**
 * A custom textfield for handling numbers.
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class NumberField extends TextField {
    
    /**
     * Get the numeric value of the NumberField
     * 
     * @return Text as Numeric Value
     * @throws NumberFormatException When text cannot be converted to numeric form.
     */
    public int getNumericValue() throws NumberFormatException {
        try { 
            int value = Integer.parseInt(getText());
            
            return value;
            
        } catch (NumberFormatException ex) {
            throw new NumberFormatException();
            
        }
    }
}

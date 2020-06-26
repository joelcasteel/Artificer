package main.java.artificer.ui.elements;

import javafx.scene.control.Label;

/**
 * A special type of NumberField for displaying modifiers
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class ModNumberLabel extends Label {
    
    /**
     * Set the value of the field to a numeric mod value.
     * The +/- is applied based on the value
     * 
     * @param value The mod value.
     */
    public void setNumericValue(int value) {
        String newText = "";
        if(value > 0 ) {
            newText += "+";
        }
        
        newText += Integer.toString(value);
        setText(newText);
    }
}

package main.java.artificer.ui.detail;

public class ModNumberField extends NumberField {
    @Override
    public int getNumericValue() throws NumberFormatException {
        try { 
            int value = Integer.parseInt(getText());
            
            setNumericValue(value);
            
            return value;
            
        } catch (NumberFormatException ex) {
            throw new NumberFormatException();
            
        }
    }
    
    public void setNumericValue(int value) {
        String newText = "";
        if(value > 0 ) {
            newText += "+";
        }
        
        newText += Integer.toString(value);
        setText(newText);
    }
}

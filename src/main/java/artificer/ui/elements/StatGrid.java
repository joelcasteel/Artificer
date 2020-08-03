package main.java.artificer.ui.elements;

import java.util.Hashtable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import main.java.artificer.stats.Stat;
import main.java.artificer.stats.StatBlock;
import main.java.artificer.ui.menu.MenuWrapper;

public class StatGrid extends GridPane{
    private Label strLabel, dexLabel, conLabel, intLabel, wisLabel, chaLabel;
    private Label names[] = new Label[6];
    private Label strMod, dexMod, conMod, intMod, wisMod, chaMod;
    private Label modLabels[] = new Label[6];
    private TextField strField, dexField, conField, intField, wisField, chaField;
    private TextField statFields[] = new TextField[6];
    
    private Hashtable<String, Label> mods = new Hashtable<>();
    private Hashtable<String, TextField> fields = new Hashtable<>();
    
    private StatBlock stats;
    
    private static final int WIDTH = MenuWrapper.MENU_ITEM_WIDTH;
    private static final int NAME_WIDTH = 120;
    private static final int MOD_WIDTH = 120;
    private static final int FIELD_WIDTH = WIDTH - NAME_WIDTH - MOD_WIDTH;
    
    
    public StatGrid() {
        setId("stat-grid");
        setMaxWidth(WIDTH);
        
        
        
        getColumnConstraints().addAll(
                new ColumnConstraints(NAME_WIDTH),
                new ColumnConstraints(MOD_WIDTH),
                new ColumnConstraints(FIELD_WIDTH)
                );
        
        StackPane holder[] = new StackPane[6];
        
        for(int i = 0; i < names.length; i++) {
            names[i] = new Label();
            names[i].getStyleClass().add("brutal-label");
            names[i].setPrefWidth(NAME_WIDTH);
            holder[i] = new StackPane(names[i]);
            holder[i].getStyleClass().add("holder-pane");
            add(holder[i], 0, i);
        }
        
        names[0].setText("STR");
        names[1].setText("DEX"); 
        names[2].setText("CON");
        names[3].setText("INT");
        names[4].setText("WIS");
        names[5].setText("CHA");
        
        
        StackPane modHolder[] = new StackPane[6];
        
        for(int i = 0; i < modLabels.length; i++) {
            modLabels[i] = new Label();
            modLabels[i].getStyleClass().add("brutal-label");
            modLabels[i].setPrefWidth(MOD_WIDTH);
            modHolder[i] = new StackPane(modLabels[i]);
            modHolder[i].getStyleClass().add("holder-pane");
            add(modHolder[i], 1, i);
        }
        
        mods.put(Stat.STR, modLabels[0]); 
        mods.put(Stat.DEX, modLabels[1]);
        mods.put(Stat.CON, modLabels[2]);
        mods.put(Stat.INT, modLabels[3]);
        mods.put(Stat.WIS, modLabels[4]);
        mods.put(Stat.CHA, modLabels[5]);
        
        HBox boxes[] = new HBox[6];
        
        for(int i = 0; i < statFields.length; i++) {
            statFields[i] = new TextField();
            statFields[i].setOnAction(fieldHandler);
            statFields[i].setAlignment(Pos.CENTER);
            statFields[i].getStyleClass().add("brutal-blank-text-field");
            boxes[i] = new HBox(statFields[i]);
            boxes[i].setPrefWidth(FIELD_WIDTH);
            HBox.setHgrow(statFields[i], Priority.ALWAYS);
            add(boxes[i], 2, i);
            
        }
        

        fields.put(Stat.STR, statFields[0]);
       
        fields.put(Stat.DEX, statFields[1]);
        
        fields.put(Stat.CON, statFields[2]);

        fields.put(Stat.INT, statFields[3]);

        fields.put(Stat.WIS, statFields[4]);

        fields.put(Stat.CHA, statFields[5]);
        
        
        
    }
    
    
    private void setModValue(int value, String mod) {
        String text = "";
        if(value > 0) {
            text += "+";
        } else if(value < 0) {
            text += "-";
        }
        text += Integer.toString((value-10)/2);
        mods.get(mod).setText(text);
    }
    
    private int getInput(String stat) {
        TextField field = fields.get(stat);
        try {
            return Integer.parseInt(field.getText());
            
        } catch (Exception ex) {
            field.setText("0");
            return 0;
        }
    }
    
    public void setValues(StatBlock block) {
        stats = block;
        for(String stat: fields.keySet()) {
            int score = block.getStat(stat).getScore();
            fields.get(stat).setText(Integer.toString(score));
            setModValue(score, stat);
        }
        
    }
    
    /**
     * Update the values inside of the table
     */
    public void updateValues() {
        for(String stat: fields.keySet()) {
            int score = stats.getStat(stat).getScore();
            fields.get(stat).setText(Integer.toString(score));
            setModValue(score, stat);
        }
    }
    
    public void saveEnteredValues() {
        for(String stat: fields.keySet()) {
            stats.changeScore(stat, getInput(stat));
        }
    }
    
    public StatBlock getStatBlock() {
        saveEnteredValues();
        return stats;
    }

    /**
     * Event handler for the value field
     */
    EventHandler<ActionEvent> fieldHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent event) {
            for(String stat : fields.keySet()) {
                
                TextField field = fields.get(stat);
                
                if(event.getSource().equals(field)) {
                    setModValue(getInput(stat), stat);
                }
            }
        }
        
    };
}

package main.java.artificer.ui.elements;

import java.util.Hashtable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import main.java.artificer.stats.Stat;
import main.java.artificer.stats.StatBlock;

public class StatGrid extends GridPane{
    private Label strLabel, dexLabel, conLabel, intLabel, wisLabel, chaLabel;
    private Label strMod, dexMod, conMod, intMod, wisMod, chaMod;
    private TextField strField, dexField, conField, intField, wisField, chaField;
    
    private Hashtable<String, Label> mods = new Hashtable<>();
    private Hashtable<String, TextField> fields = new Hashtable<>();
    
    private StatBlock stats;
    
    private static final int WIDTH = 396;
    
    
    public StatGrid() {
        setId("stat-grid");
        
        getColumnConstraints().addAll(
                new ColumnConstraints(72),
                new ColumnConstraints(108),
                new ColumnConstraints(WIDTH-180)
                );
        
        StackPane holder[] = new StackPane[6];
        
        strLabel = new Label("STR");
        strLabel.getStyleClass().add("brutal-label");
        holder[0] = new StackPane(strLabel);
        
        dexLabel = new Label("DEX");
        dexLabel.getStyleClass().add("brutal-label");
        holder[1] = new StackPane(dexLabel);
        
        conLabel = new Label("CON");
        conLabel.getStyleClass().add("brutal-label");
        holder[2] = new StackPane(conLabel);
        
        intLabel = new Label("INT");
        intLabel.getStyleClass().add("brutal-label");
        holder[3] = new StackPane(intLabel);
        
        wisLabel = new Label("WIS");
        wisLabel.getStyleClass().add("brutal-label");
        holder[4] = new StackPane(wisLabel);
        
        chaLabel = new Label("CHA");
        chaLabel.getStyleClass().add("brutal-label");
        holder[5] = new StackPane(chaLabel);
        
        for(int i = 0; i < 6; i++) {
            holder[i].getStyleClass().add("holder-pane");
            add(holder[i], 0, i);
        }
        
        StackPane modHolder[] = new StackPane[6];
        
        strMod = new Label();
        strMod.getStyleClass().add("brutal-label");
        modHolder[0] = new StackPane(strMod);
        mods.put(Stat.STR, strMod);
        
        dexMod = new Label();
        dexMod.getStyleClass().add("brutal-label");
        modHolder[1] = new StackPane(dexMod);
        mods.put(Stat.DEX, dexMod);
        
        conMod = new Label();
        conMod.getStyleClass().add("brutal-label");
        modHolder[2] = new StackPane(conMod);
        mods.put(Stat.CON, conMod);
        
        intMod = new Label();
        intMod.getStyleClass().add("brutal-label");
        modHolder[3] = new StackPane(intMod);
        mods.put(Stat.INT, intMod);
        
        wisMod = new Label();
        wisMod.getStyleClass().add("brutal-label");
        modHolder[4] = new StackPane(wisMod);
        mods.put(Stat.WIS, wisMod);
        
        chaMod = new Label();
        chaMod.getStyleClass().add("brutal-label");
        modHolder[5] = new StackPane(chaMod);
        mods.put(Stat.CHA, chaMod);
        
        for(int i = 0; i < 6; i++) {
            modHolder[i].getStyleClass().add("holder-pane");
            add(modHolder[i], 1, i);
        }
        
        
        strField = new TextField();
        strField.setOnAction(fieldHandler);
        strField.getStyleClass().add("brutal-text-field");
        add(strField, 2, 0);
        fields.put(Stat.STR, strField);
        
        dexField = new TextField();
        dexField.setOnAction(fieldHandler);
        dexField.getStyleClass().add("brutal-text-field");
        add(dexField, 2, 1);
        fields.put(Stat.DEX, dexField);
        
        conField = new TextField();
        conField.setOnAction(fieldHandler);
        conField.getStyleClass().add("brutal-text-field");
        add(conField, 2, 2);
        fields.put(Stat.CON, conField);
        
        intField = new TextField();
        intField.setOnAction(fieldHandler);
        intField.getStyleClass().add("brutal-text-field");
        add(intField, 2, 3);
        fields.put(Stat.INT, intField);
        
        wisField = new TextField();
        wisField.setOnAction(fieldHandler);
        wisField.getStyleClass().add("brutal-text-field");
        add(wisField, 2, 4);
        fields.put(Stat.WIS, wisField);
        
        chaField = new TextField();
        chaField.setOnAction(fieldHandler);
        chaField.getStyleClass().add("brutal-text-field");
        add(chaField, 2, 5);
        fields.put(Stat.CHA, chaField);
        
        for(String stat : fields.keySet()) {
            fields.get(stat).setOnAction(fieldHandler);
        }
        
        
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

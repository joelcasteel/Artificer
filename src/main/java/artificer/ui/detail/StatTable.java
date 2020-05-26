package main.java.artificer.ui.detail;

import java.util.HashMap;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.java.artificer.stats.Stat;
import main.java.artificer.stats.StatBlock;

public class StatTable extends GridPane {
    StatBlock stats;
    
    Label mods[] = new Label[6];
    TextField fields[] = new TextField[6];
    HashMap<String,StatRow> rows;
    
    public StatTable() {
        setId("stat-table");
        rows = new HashMap<String, StatRow>();
        
        stats = null;
        setVgap(6);
        for(int i = 0; i < Stat.STATNAME.length; i++) {
            rows.put(Stat.STATNAME[i], new StatRow(Stat.STATNAME[i]));
            add(rows.get(Stat.STATNAME[i]), 0, i);
        }
    }
    
    public void setValues(StatBlock block) {
        stats = block;
        for(int i = 0; i < Stat.STATNAME.length; i++) {
            rows.get(Stat.STATNAME[i]).setContent(block.getStat(Stat.STATNAME[i]));
        }
        
    }
    
    public void updateValues() {
        for(int i = 0; i < Stat.STATNAME.length; i++) {
            rows.get(Stat.STATNAME[i]).setContent(stats.getStat(Stat.STATNAME[i]));
        }
    }
    
    class StatRow extends GridPane {
        private Label name;
        private Label mod;
        private TextField score;
        
        public StatRow(String pName) {
            getStyleClass().add("stat-row");
            setAlignment(Pos.CENTER_LEFT);
            getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100)
                );
            
            name = new Label(pName);
            mod = new Label();
            
            
            score = new TextField();
            score.setMaxWidth(60);
            score.setOnAction(fieldHandler);
            
            add(name, 0,0);
            add(mod, 1, 0);
            add(score, 2, 0);
            
            
        }
        
        public void setContent(Stat stat) {
            mod.setText(stat.getModString());
            score.setText(Integer.toString(stat.getScore()));
        }
        
        public int getValue() {
            return Integer.parseInt(score.getText());
        }
        
        public TextField getField() {
            return score;
        }
        
        
        
    }
    
    EventHandler<ActionEvent> fieldHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent event) {
            for(int i = 0; i < Stat.STATNAME.length; i++) {
                
                if((event.getSource()).equals(
                        rows.get(Stat.STATNAME[i]).getField())) {
                    
                    stats.changeScore(Stat.STATNAME[i], 
                            rows.get(Stat.STATNAME[i]).getValue());
                }
            }
            updateValues();
            
            
        }
        
    };

}

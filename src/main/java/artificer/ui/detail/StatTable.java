package main.java.artificer.ui.detail;

import java.util.Iterator;

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

    
    public StatTable() {
        
        
        stats = null;
        setHgap(12);
        setAlignment(Pos.CENTER_LEFT);
        getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100)
                );
        
        add(new Label(Stat.STR), 0, 0);
        add(new Label(Stat.DEX), 0, 1);
        add(new Label(Stat.CON), 0, 2);
        add(new Label(Stat.INT), 0, 3);
        add(new Label(Stat.WIS), 0, 4);
        add(new Label(Stat.CHA), 0, 5);
        for(int i = 0; i < 6; i++) {
            mods[i] = new Label();
            add(mods[i], 1, i);
            fields[i] = new TextField();
            fields[i].setMaxWidth(24);
            fields[i].getStyleClass().add("number-text-field");
            add(fields[i], 2, i);
        }
    }
    
    public void setValues(StatBlock block) {
        stats = block;
        Iterator<Stat> iter = stats.getAllStats().iterator();
        for(int i = 0; i < 6; i++) {
            Stat stat = iter.next();
            String modString = " (";
            if(stat.getMod() >= 0) {
                modString += "+ ";
                modString += Integer.toString(stat.getMod());
            } else {
                modString += "- ";
                modString += Integer.toString(stat.getMod()*-1);
            }
            modString += ")";
            mods[i].setText(modString);
            fields[i].setText(Integer.toString(stat.getStat()));
        }
        
    }
    

}

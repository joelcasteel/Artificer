package main.java.artificer.ui.detail;

import com.google.gson.JsonObject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.java.artificer.stats.Monster;

public class MonsterDetail extends Detail {
    
    private StatTable statsTable;
    private GridPane detailGrid;
    
    private TextField name, size, type, alignment, hitPoints, armorClass;
    
    
    
    
    
    public MonsterDetail() {
        
        
        
        headerLabel.setText("Monster Details");
        
        
        detailGrid = new GridPane();
        detailGrid.setHgap(12);
        detailGrid.getColumnConstraints().add(new ColumnConstraints(100));
        detailGrid.getColumnConstraints().add(new ColumnConstraints(200));
        
        detailGrid.add(new Label("Name:"), 0, 0);
        detailGrid.add(new Label("Size:"), 0, 1);
        detailGrid.add(new Label("Type:"), 0, 2);
        detailGrid.add(new Label("Alignment:"), 0, 3);
        detailGrid.add(new Label("Hit Points:"), 0, 4);
        detailGrid.add(new Label("Armor Class"), 0, 5);
        
        name = new TextField();
        detailGrid.add(name, 1, 0);
        
        size = new TextField();
        detailGrid.add(size, 1, 1);
        
        type = new TextField();
        detailGrid.add(type, 1, 2);
        
        alignment = new TextField();
        detailGrid.add(alignment, 1, 3);
        
        hitPoints = new TextField();
        detailGrid.add(hitPoints, 1, 4);
        
        armorClass = new TextField();
        detailGrid.add(armorClass, 1, 5);
        
        
        
        statsTable = new StatTable();
        
        Separator split = new Separator();
        split.setHalignment(HPos.CENTER);
        
        
        holder.getChildren().addAll(
               detailGrid, split, statsTable
               );
        
    }
    
    public void setContent(Monster source) {
        open();
        name.setText(source.getName());
        size.setText(source.getSize());
        type.setText(source.getType());
        alignment.setText(source.getAlignment());
        hitPoints.setText(Integer.toString(source.getHP()));
        armorClass.setText(Integer.toString(source.getAC()));
        statsTable.setValues(source.getStats());
        
        
        
    }
    
    EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {

        @Override
        public void handle(ActionEvent event) {
            if(event.getSource().equals(backButton)) {
                close();
            }
            
        }
        
    };
    
}

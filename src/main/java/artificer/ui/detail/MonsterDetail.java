package main.java.artificer.ui.detail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonObject;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.Proficiency;
import main.java.artificer.ui.MonsterCache;
import main.java.artificer.ui.MonsterCard;
import main.java.artificer.ui.ProfCell;

public class MonsterDetail extends Detail {
    
    private StatTable statsTable;
    private GridPane detailGrid;
    
    private TextField name, size, type, alignment, hitPoints, armorClass;
    
    private ListView<Proficiency> profList = new ListView<Proficiency>();
    private ObservableList<Proficiency> profs = FXCollections.observableArrayList();
    
    private GridPane grid = new GridPane();
    private TextField newProfName = new TextField();
    private ModNumberField stat = new ModNumberField();
    private Button addButton = new Button();
    
    
    
    public MonsterDetail() {
        
        holder.setId("monster-detail");
        
        
        headerLabel.setText("Monster Details");
        
        
        detailGrid = new GridPane();
        detailGrid.setId("detail-grid");
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
        
        Separator split[] = new Separator[2];
        split[0] = new Separator();
        split[0].setHalignment(HPos.CENTER);
        split[1] = new Separator();
        split[1].setHalignment(HPos.CENTER);
        
        profList.getStyleClass().add("prof-list");
        profList.setPrefSize(300, 180);
        profList.setCellFactory(new Callback<ListView<Proficiency>, ListCell<Proficiency>>(){
            @Override
            public ListCell<Proficiency> call(ListView<Proficiency> monsterListView) {
                return new ProfCell();
                
            }
        });
        
        grid.setMaxHeight(ProfCell.CELL_HEIGHT);
        grid.setPrefHeight(ProfCell.CELL_HEIGHT);
        
        grid.setPrefWidth(300);
        grid.getStyleClass().add("create-box");
        
        grid.setHgap(12);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(24),
                new ColumnConstraints(120),
                new ColumnConstraints(48)
                );
        
        
        stat.getStyleClass().add("number-field");
        
        addButton.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/add_circle.png").toString())));
        addButton.setPadding(new Insets(6));
        
        grid.add(addButton, 0, 0);
        grid.add(newProfName, 1, 0);
        grid.add(stat, 2, 0);
        
        
        holder.getChildren().addAll(
               detailGrid, split[0], statsTable, split[1],
               profList, grid
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
        Iterator<Proficiency> iter = source.getStats().getAllProfs().iterator();
        List<Proficiency> profs = new ArrayList<Proficiency>();
        
        while(iter.hasNext()) {
            profs.add(iter.next());
        }
        profList.setItems(FXCollections.observableArrayList(profs));
        
        
        
        
        
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

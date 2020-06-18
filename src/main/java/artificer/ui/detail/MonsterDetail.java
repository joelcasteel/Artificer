package main.java.artificer.ui.detail;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.Proficiency;
import main.java.artificer.ui.elements.ModNumberField;
import main.java.artificer.ui.elements.ProfCell;
import main.java.artificer.ui.elements.ProfList;
import main.java.artificer.ui.elements.SkillTypeSelector;
import main.java.artificer.ui.elements.StatTable;

/**
 * The Monster Detail module
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class MonsterDetail extends Detail {
    
    private StatTable statsTable;
    
    private Label statLabel = new Label("Stats");
    private GridPane detailGrid;
    
    private TextField name, size, type, alignment, hitPoints, armorClass;
    
    private Label profLabel = new Label("Proficiencies");
    private ProfList profList = new ProfList();
    
    private GridPane grid = new GridPane();
    private TextField newProfName = new TextField();
    private ModNumberField stat = new ModNumberField();
    private Button addButton = new Button();
    private SkillTypeSelector typeBox = new SkillTypeSelector();
    
   
    
    
    /**
     * Construct a new Monster Detail Menu
     */
    public MonsterDetail() {
        
        holder.setId("monster-detail");
        
        
        headerLabel.setText("Monster Details");
        
        statLabel.getStyleClass().add("cool-section-label");
        
        
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
        
        
        grid.setMaxHeight(ProfCell.CELL_HEIGHT);
        grid.setPrefHeight(ProfCell.CELL_HEIGHT);
        
        grid.setPrefWidth(300);
        grid.getStyleClass().add("create-box");
        
        grid.setHgap(12);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(48),
                new ColumnConstraints(120),
                new ColumnConstraints(48)
                );
        
        
        stat.getStyleClass().add("number-field");
        stat.setOnAction(profFieldHandler);
        
        profLabel.getStyleClass().add("cool-section-label");
        
        newProfName.getStyleClass().add("blank-text-field");
        newProfName.setPromptText("New...");
        newProfName.setOnAction(profFieldHandler);
        
        addButton.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/add_circle.png").toString())));
        addButton.setPadding(new Insets(6));
        
        
        grid.add(typeBox, 0, 0);
        grid.add(newProfName, 1, 0);
        grid.add(stat, 2, 0);
        
        
        holder.getChildren().addAll(
               detailGrid, statLabel, split[0], statsTable, split[1],
               profLabel, profList.getProfList(), grid
               );
        
    }
    
    /**
     * 
     * 
     * @param source
     */
    public void setContent(Monster source) {
        open();
        
        name.setText(source.getName());
        size.setText(source.getSize());
        type.setText(source.getType());
        
        alignment.setText(source.getAlignment());
        
        hitPoints.setText(Integer.toString(source.getHP()));
        armorClass.setText(Integer.toString(source.getAC()));
        
        statsTable.setValues(source.getStats());
        

        
        profList.setContent(source);
   
        
    }
    
    EventHandler<ActionEvent> profFieldHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            int mod;
            
            try {
                mod = Integer.parseInt(stat.getText());
            } catch (Exception ex) {
                mod = 0;
            }
            
            Proficiency prof = new Proficiency(newProfName.getText(), mod,
                    typeBox.getSelectionModel().getSelectedItem().contentEquals("Skill"));
            
            
            profList.addContent(prof);
            
            
            
        }
        
    };
    
    
}

package main.java.artificer.ui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.java.artificer.stats.Proficiency;

public class ProfEditor extends GridPane {
    private TextField newProfName = new TextField();
    private ModNumberField stat = new ModNumberField();
    private Button addButton = new Button();
    private SkillTypeSelector typeBox = new SkillTypeSelector();
    
    private Proficiency prof = null;
    private ProfList profList = null;
    
    public ProfEditor(ProfList parentList) {
        
        profList = parentList;
        
        setMaxHeight(ProfCell.CELL_HEIGHT);
        setPrefHeight(ProfCell.CELL_HEIGHT);
        
        setPrefWidth(300);
        getStyleClass().add("create-box");
        
        setHgap(12);
        getColumnConstraints().addAll(
                new ColumnConstraints(48),
                new ColumnConstraints(120),
                new ColumnConstraints(48)
                );
        
        
        stat.getStyleClass().add("number-field");
        stat.setOnAction(profFieldHandler);
        
        newProfName.getStyleClass().add("blank-text-field");
        newProfName.setPromptText("Add New...");
        newProfName.setOnAction(profFieldHandler);
        
        typeBox.setOnAction(profFieldHandler);
        
        
        addButton.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/add_circle.png").toString())));
        addButton.setPadding(new Insets(6));
        
        
        add(typeBox, 0, 0);
        add(newProfName, 1, 0);
        add(stat, 2, 0);
    }
    
    
    public void setEditContext(Proficiency p) {
        prof = p;
        newProfName.setText(p.getName());
        stat.setText(Integer.toString(p.getValue()));
        
        if(p.isSkill()) {
            typeBox.getSelectionModel().select(0);
        } else {
            typeBox.getSelectionModel().select(1);
        }
        
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
            
            if(prof != null) {
                prof.setName(newProfName.getText());
                prof.setSkill(typeBox.getSelectionModel().getSelectedItem().contentEquals("Skill"));
                prof.setValue(mod);
            }
            
            profList.getProfList().refresh();
            
        }
        
    };
    
    
}

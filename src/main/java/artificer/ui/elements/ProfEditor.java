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
import javafx.scene.layout.StackPane;
import main.java.artificer.stats.Proficiency;
import main.java.artificer.ui.menu.MenuWrapper;

public class ProfEditor extends GridPane {
    private TextField newProfName = new TextField();
    private ModNumberField stat = new ModNumberField();
    private Button addButton = new Button();
    private SkillTypeSelector typeBox = new SkillTypeSelector();
    
    private Button saveButton = new Button("SAVE");
    private Button clearButton = new Button("CLEAR");
    private Button deleteButton = new Button("DELETE");
    
    private Proficiency prof = null;
    private ProfList profList = null;
    
    private Proficiency currentProf = null;
    
    private static int height = 36;
    private static int BOX_WIDTH = 72;
    private static int MOD_WIDTH = 60;
    private static int BUTTON_WIDTH = 60;
    private static int NAME_WIDTH = MenuWrapper.MENU_ITEM_WIDTH
            - BOX_WIDTH - MOD_WIDTH - 3*BUTTON_WIDTH;
    
    public ProfEditor(ProfList parentList) {
        
        
        
        profList = parentList;
        
        setMinHeight(height);
        setPrefHeight(height);
        
        
        
        setPrefWidth(MenuWrapper.MENU_ITEM_WIDTH);
        getStyleClass().add("brutal-prof-editor-grid");
        
        
        getColumnConstraints().addAll(
                new ColumnConstraints(BOX_WIDTH),
                new ColumnConstraints(NAME_WIDTH),
                new ColumnConstraints(MOD_WIDTH),
                new ColumnConstraints(BUTTON_WIDTH),
                new ColumnConstraints(BUTTON_WIDTH),
                new ColumnConstraints(BUTTON_WIDTH)
                );
        
        typeBox.setOnAction(profFieldHandler);
        typeBox.setPrefHeight(height);
        typeBox.setPrefWidth(BOX_WIDTH);
        StackPane typeHolder = new StackPane(typeBox);
        typeHolder.getStyleClass().add("brutal-blank-holder");
        add(typeHolder, 0, 0);
        
        newProfName.getStyleClass().add("brutal-blank-text-field");
        newProfName.setPromptText("Add New...");
        newProfName.setPrefHeight(height);
        newProfName.setOnAction(profFieldHandler);
        StackPane profNameHolder = new StackPane(newProfName);
        profNameHolder.getStyleClass().add("brutal-blank-holder");
        add(profNameHolder, 1, 0);
        
        stat.getStyleClass().add("brutal-blank-text-field");
        stat.setPromptText("(+/-)##");
        stat.setOnAction(profFieldHandler);
        stat.setPrefHeight(height);
        StackPane statHolder = new StackPane(stat);
        statHolder.getStyleClass().add("brutal-blank-holder");
        add(statHolder, 2, 0);
        
        saveButton.setPrefSize(60,height);
        saveButton.getStyleClass().add("brutal-button");
        saveButton.setId("save-prof-button");
        saveButton.setOnAction(editHandler);
        add(saveButton, 3, 0);
        
        clearButton.setPrefSize(60, height);
        clearButton.setId("clear-prof-button");
        clearButton.getStyleClass().add("brutal-button");
        clearButton.setOnAction(editHandler);
        add(clearButton, 5, 0);
        
        deleteButton.setPrefSize(60, height);
        deleteButton.setId("clear-prof-button");
        deleteButton.getStyleClass().add("brutal-button");
        deleteButton.setOnAction(editHandler);
        add(deleteButton, 4, 0);
        
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
    
    private int getMod() {
        
        try {
            return Integer.parseInt(stat.getText());
        } catch (Exception ex) {
            return 0;
        }
    }
    
    private void reset() {
        newProfName.setText("");
        typeBox.getSelectionModel().selectFirst();
        stat.setText("");
        currentProf = null;
    }
    
    EventHandler<ActionEvent> profFieldHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

            
        }
        
    };
    
    
    EventHandler<ActionEvent> editHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource().equals(saveButton)) {
                if(currentProf == null) {
                    currentProf = new Proficiency();
                    currentProf.setName(newProfName.getText());
                    currentProf.setSkill(typeBox.getSelectionModel().getSelectedItem().contentEquals("Skill"));
                    currentProf.setValue(getMod());
                    profList.addContent(currentProf);
                    
                    profList.getProfList().refresh();
                    
                    reset();
                }
                
            } else if(event.getSource().equals(clearButton)) {
                reset();
            }
        }
    };
    
    
}

package main.java.artificer.ui.elements;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.java.artificer.stats.Proficiency;

/**
 * Custom ListCell for a ListView of Proficiencies.
 * Ended up being more complex than I thought.
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class ProfCell extends ListCell<Proficiency> {

    public static final int CELL_HEIGHT = 36;
    
    public static ProfCell currentCell = null;
    
    private GridPane grid = new GridPane();
    private TextField nameField = new TextField();
    private Button deleteButton = new Button();
    
    private ModNumberField stat = new ModNumberField();
    private ImageView saveIcon = new ImageView(new Image(getClass().getResource("/ui/icons/save.png").toString()));
    private ImageView skillIcon = new ImageView(new Image(getClass().getResource("/ui/icons/skill.png").toString()));
    private ImageView deleteIcon = new ImageView(new Image(getClass().getResource("/ui/icons/exit.png").toString()));
    private Button imgButton = new Button();
    
    private ProfList profList;
    
    /**
     * Construct a new ProfCell
     * 
     * @param pProfList The parent ListView of this Cell.
     */
    public ProfCell(ProfList pProfList) {
        profList = pProfList;
        
        getStyleClass().add("cool-list-cell");
        setMaxHeight(CELL_HEIGHT);
        setPrefHeight(CELL_HEIGHT);
        
        
        
        grid.setHgap(12);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(48),
                new ColumnConstraints(102),
                new ColumnConstraints(48),
                new ColumnConstraints(36)
                
                );
        
        nameField.getStyleClass().add("blank-text-field");
        stat.getStyleClass().add("number-field");
        deleteButton.getStyleClass().add("cool-button");
        deleteButton.setOnAction(deleteHandler);
        
        deleteButton.setGraphic(deleteIcon);
        
        nameField.setEditable(false);
        nameField.setMouseTransparent(true);
        stat.setEditable(false);
        stat.setMouseTransparent(true);
        deleteButton.setVisible(false);
        imgButton.setMouseTransparent(true);
        imgButton.setDisable(true);
        
        
        stat.setOnAction(fieldHandler);
        nameField.setOnAction(fieldHandler);
        
        grid.add(nameField, 1, 0);
        grid.add(stat, 2, 0);
        grid.add(deleteButton, 3, 0);

        imgButton.getStyleClass().add("cool-button");
        imgButton.setOnAction(fieldHandler);
        grid.add(imgButton, 0, 0);
        
        
        setOnMouseClicked(selectHandler);
    }
    
    @Override
    public void updateItem(Proficiency prof, boolean empty) {
        super.updateItem(prof, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            chooseIcon(prof.isSkill());
            nameField.setText(prof.getName());
            stat.setNumericValue(prof.getValue());
            setGraphic(grid);
            toggleActive(false);
            
        }
    }
    
    /**
     * Chooses the Icon based on whether the proficiency is a skill or save.
     * 
     * @param skill T if Skill, F if Save
     */
    public void chooseIcon(boolean skill) {
        if(skill) {
            imgButton.setGraphic(skillIcon);
        } else {
            imgButton.setGraphic(saveIcon);
        }
        
    }
    
    /**
     * Toggles whether this cell is active and editable
     * 
     * @param on T if Editable, F if not
     */
    public void toggleActive(boolean on) {
        nameField.setEditable(on);
        nameField.setMouseTransparent(!on);
        stat.setEditable(on);
        stat.setMouseTransparent(!on);
        deleteButton.setVisible(on);
        imgButton.setMouseTransparent(!on);
        imgButton.setDisable(!on);
    }
    
    /**
     * Field handler for updating values
     */
    EventHandler<ActionEvent> fieldHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            //Check whether this is a skill
            boolean isASkill = getItem().isSkill();
            
            //SKILL BUTTON
            //Toglle skill/save
            if(event.getSource().equals(imgButton)) {
                if(imgButton.getGraphic().equals(skillIcon)) {
                    isASkill = false;
                    
                } else {
                    isASkill = true;
                }
                
            }
            
            //Required as a workaround... I could be doing something wrong.
            final boolean passSkill = isASkill;
            
            //ALL FIELDS
            //Update the Proficiency in the list with all the values from the cell.
            Platform.runLater(new Runnable() {
                public void run() {
                    profList.switchContent(getItem(), new Proficiency(nameField.getText(), stat.getNumericValue(), passSkill));
                    toggleActive(true);
                    
                }
                
            });
            
            
        }
        
    };
    
    /**
     * Event handler for the delete button
     */
    EventHandler<ActionEvent> deleteHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            profList.removeContent(getItem());
            profList.getProfList().requestFocus();
            
        }
        
    };
    
    /**
     * Mouse handler for selecting the cell.
     */
    EventHandler<MouseEvent> selectHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            if(currentCell != null) {
                currentCell.toggleActive(false);
            }
            toggleActive(true);
            currentCell = (ProfCell)event.getSource();
            
            
        }
        
    };
           
    
    
}

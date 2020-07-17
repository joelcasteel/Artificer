package main.java.artificer.ui.elements;


import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import main.java.artificer.Debugger;
import main.java.artificer.stats.Proficiency;
import main.java.artificer.ui.menu.MenuWrapper;

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
    private Label nameField = new Label();
    
    private ModNumberLabel stat = new ModNumberLabel();
    private ImageView saveIcon = new ImageView(new Image(getClass().getResource("/ui/icons/save.png").toString()));
    private ImageView skillIcon = new ImageView(new Image(getClass().getResource("/ui/icons/skill.png").toString()));
    private ImageView deleteIcon = new ImageView(new Image(getClass().getResource("/ui/icons/exit.png").toString()));
    private Button imgButton = new Button();
    private Label imgLabel = new Label();
    StackPane holder;
    
    private ProfList profList;
    
    /**
     * Construct a new ProfCell
     * 
     * @param pProfList The parent ListView of this Cell.
     */
    public ProfCell(ProfList pProfList) {
        profList = pProfList;
        
        
        
        getStyleClass().add("brutal-list-cell");
        //setMaxHeight(CELL_HEIGHT);
        //setPrefHeight(CELL_HEIGHT);
        
        int fullwidth = MenuWrapper.MENU_WIDTH-36;
        grid.getStyleClass().add("brutal-prof-cell-grid");
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(72),
                new ColumnConstraints(fullwidth - 72*2 - 24),
                new ColumnConstraints(72)
                
                );
        
        
        
        
        nameField.getStyleClass().add("brutal-label");
        StackPane nameHolder = new StackPane(nameField);
        nameHolder.getStyleClass().add("brutal-holder");
        grid.add(nameHolder, 1, 0);
        
        
        stat.getStyleClass().add("brutal-label");  
        StackPane statHolder = new StackPane(stat);
        statHolder.getStyleClass().add("brutal-holder");
        grid.add(statHolder, 2, 0);

        imgLabel.getStyleClass().add("brutal-label");
        StackPane imgHolder = new StackPane(imgLabel);
        imgHolder.getStyleClass().add("brutal-holder");
        grid.add(imgHolder, 0, 0);
        
        holder = new StackPane();
        holder.getStyleClass().add("brutal-cell-holder");
        
 
        
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
            Debugger.debug("Update:" + prof.getName());
            
            
        }
    }
    
    /**
     * Chooses the Icon based on whether the proficiency is a skill or save.
     * 
     * @param skill T if Skill, F if Save
     */
    public void chooseIcon(boolean skill) {
        if(skill) {
            imgLabel.setGraphic(skillIcon);
        } else {
            imgLabel.setGraphic(saveIcon);
        }
        
    }
    
    
           
    
    
}

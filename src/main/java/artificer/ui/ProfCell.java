package main.java.artificer.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import main.java.artificer.stats.Proficiency;
import main.java.artificer.ui.detail.ModNumberField;

public class ProfCell extends ListCell<Proficiency> {

    public static final int CELL_HEIGHT = 36;
    
    private GridPane grid = new GridPane();
    private Label name = new Label();
    private ModNumberField stat = new ModNumberField();
    private ImageView saveIcon = new ImageView(new Image(getClass().getResource("/ui/icons/save.png").toString()));
    private ImageView skillIcon = new ImageView(new Image(getClass().getResource("/ui/icons/skill.png").toString()));
    private StackPane imgPane = new StackPane();
    
    public ProfCell() {
        
        setMaxHeight(CELL_HEIGHT);
        setPrefHeight(CELL_HEIGHT);
        
        grid.setHgap(12);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(24),
                new ColumnConstraints(120),
                new ColumnConstraints(48)
                );
        
        name.getStyleClass().add("proficiency-label");
        stat.getStyleClass().add("number-field");
        
        stat.setOnAction(fieldHandler);
        
        grid.add(name, 1, 0);
        grid.add(stat, 2, 0);
        
        imgPane.setPadding(new Insets(6));
        grid.add(imgPane, 0, 0);
        imgPane.getChildren().add(saveIcon);
        imgPane.getChildren().add(skillIcon);
    }
    
    @Override
    public void updateItem(Proficiency prof, boolean empty) {
        super.updateItem(prof, empty);
        if(empty) {
            setText(null);
        } else {
            chooseIcon(prof.getName());
            stat.setNumericValue(prof.getValue());
            setGraphic(grid);
            
        }
    }
    
    public void chooseIcon(String type) {
        String split[] = type.split(": ");
        if(split[0].equals("Saving Throw")) {
            saveIcon.setVisible(true);
            skillIcon.setVisible(false);
        } else {
            saveIcon.setVisible(false);
            skillIcon.setVisible(true);
        }
        name.setText(split[1]);
    }
    
    
    EventHandler<ActionEvent> fieldHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            stat.getNumericValue();
            
        }
        
    };
    
    
}

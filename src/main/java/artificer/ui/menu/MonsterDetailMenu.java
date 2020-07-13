package main.java.artificer.ui.menu;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.MonsterLibrary;
import main.java.artificer.ui.elements.ProfList;
import main.java.artificer.ui.elements.SearchBox;
import main.java.artificer.ui.elements.StatTable;
import main.java.artificer.ui.menu.MenuWrapper.MenuTitle;

public class MonsterDetailMenu extends VBox implements Collapsible {
    
    private Monster backupSource = null;
    
    private Monster currentSource = null;
    
    //private VBox holder = new VBox();
    
    
    private StatTable statsTable;
    
    private Label statLabel = new Label("Stats");
    private GridPane detailGrid;
    
    private TextField name, size, type, alignment, hitPoints, armorClass;
    
    private Label profLabel = new Label("Proficiencies");
    private ProfList profList = new ProfList();
    
    private HBox bottomButtonBox = new HBox();
    private Button saveButton = new Button("Save");
    private Button restoreButton = new Button("Restore");

    public MonsterDetailMenu() {
        
       
        
        
        setId("monster-detail-menu");
        setPrefWidth(MenuWrapper.MENU_WIDTH-20);
        
        

        statLabel.getStyleClass().add("cool-section-label");
        
        
        detailGrid = new GridPane();
        detailGrid.setId("detail-profEditor");
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
        
        
        
        
        profLabel.getStyleClass().add("cool-section-label");
        
        bottomButtonBox.setPadding(new Insets(12));
        bottomButtonBox.setSpacing(12);
        
        saveButton.getStyleClass().add("cool-text-button");
        saveButton.setOnAction(bottomButtonHandler);
        
        restoreButton.getStyleClass().add("cool-text-button");
        restoreButton.setOnAction(bottomButtonHandler);
        
        bottomButtonBox.getChildren().addAll(saveButton, restoreButton);
        
        
        
        getChildren().addAll(
               detailGrid, statLabel, split[0], statsTable, split[1],
               profLabel, profList, bottomButtonBox
               );
        
      
        
        
        
        
        
    }
    
    /**
     * 
     * 
     * @param source
     */
    public void setContent(Monster source) {
        expand();
        
        backupSource = source.deepCopy();
        
        currentSource = source;
        
        
        name.setText(source.getName());
        size.setText(source.getSize());
        type.setText(source.getType());
        
        alignment.setText(source.getAlignment());
        
        hitPoints.setText(Integer.toString(source.getHP()));
        armorClass.setText(Integer.toString(source.getAC()));
        
        statsTable.setValues(source.getStats());
        
        profList.setContent(source.getProfs());
   
        
    }
    
    
    EventHandler<ActionEvent> bottomButtonHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if(event.getSource().equals(saveButton)) {
                
                currentSource.setName(name.getText());
                currentSource.setType(type.getText());
                currentSource.setSize(name.getText());
                currentSource.setAlignment(alignment.getText());
                
                currentSource.setAC(Integer.parseInt(armorClass.getText()));
                currentSource.setHP(Integer.parseInt(hitPoints.getText()));
                
                
                
                
                MonsterLibrary mLib = MonsterLibrary.getInstance();
                mLib.add(currentSource);
                mLib.saveLibraryToFile();
            } else if (event.getSource().equals(restoreButton)) {
                setContent(backupSource);
            }
            
        }
        
    };

    @Override
    public void collapse() {
        setManaged(false);
        setVisible(false);
        
    }

    @Override
    public void expand() {
        setManaged(true);
        setVisible(true);
        
    }

    @Override
    public void toggle() {
        if (isManaged() && isVisible()) {
            collapse();
        } else {
            expand();
        }
        
    }

    @Override
    public MenuTitle getTitle() {
        return MenuTitle.Details;
    }

}

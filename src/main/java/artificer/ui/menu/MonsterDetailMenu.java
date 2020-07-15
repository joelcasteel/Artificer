package main.java.artificer.ui.menu;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import main.java.artificer.Debugger;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.MonsterLibrary;
import main.java.artificer.ui.elements.ProfList;
import main.java.artificer.ui.elements.StatGrid;
import main.java.artificer.ui.elements.StatTable;
import main.java.artificer.ui.menu.MenuWrapper.MenuTitle;

public class MonsterDetailMenu extends VBox implements Collapsible {
    
    private Monster backupSource = null;
    
    private Monster currentSource = null;
    
    //private VBox holder = new VBox();
    
    
    private StatTable statsTable;
    
    private Label statLabel = new Label("STATS");
    private Label detailLabel = new Label("DETAILS");
    private GridPane detailGrid;
    
    private StatGrid statGrid = new StatGrid();
    
    private Label nameLabel, sizeLabel, typeLabel, alignmentLabel,
        hitPointsLabel, armorClassLabel;
    
    private ComboBox<String> sizeSelect, typeSelect, alignmentSelect;
    
    private TextField nameField, sizeField, typeField, alignmentField,
        hitPointsField, armorClassField;
    
    private Label profLabel = new Label("Proficiencies");
    private ProfList profList = new ProfList();
    
    private HBox bottomButtonBox = new HBox();
    private Button saveButton = new Button("Save");
    private Button restoreButton = new Button("Restore");

    public MonsterDetailMenu() {
        
       
        
        
        setId("monster-detail-menu");
        setPrefWidth(MenuWrapper.MENU_WIDTH);
        
        

        StackPane statHolder = new StackPane(statLabel);
        statHolder.getStyleClass().add("section-label");
        //statLabel.setPrefWidth(MenuWrapper.MENU_WIDTH);
        
        StackPane detailHolder = new StackPane(detailLabel);
        detailHolder.getStyleClass().add("section-label");
        
        
        
        detailGrid = new GridPane();
        detailGrid.setId("detail-grid");
        //int labelWidth = 120;
        //detailGrid.getColumnConstraints().add(new ColumnConstraints(labelWidth));
        int fieldWidth = 396;
        detailGrid.getColumnConstraints().add(new ColumnConstraints(fieldWidth));
        
        /*nameLabel = new Label("NAME");
        nameLabel.getStyleClass().add("brutal-label");
        nameLabel.setAlignment(Pos.CENTER_RIGHT);
        nameLabel.setPrefWidth(labelWidth);
        detailGrid.add(nameLabel, 0, 0);
        
        sizeLabel = new Label("SIZE");
        sizeLabel.getStyleClass().add("brutal-label");
        sizeLabel.setAlignment(Pos.CENTER_RIGHT);
        sizeLabel.setPrefWidth(labelWidth);
        detailGrid.add(sizeLabel, 0, 1);
        
        typeLabel = new Label("TYPE");
        typeLabel.getStyleClass().add("brutal-label");
        typeLabel.setAlignment(Pos.CENTER_RIGHT);
        typeLabel.setPrefWidth(labelWidth);
        detailGrid.add(typeLabel, 0, 2);
        
        alignmentLabel = new Label("ALIGNMENT");
        alignmentLabel.getStyleClass().add("brutal-label");
        alignmentLabel.setAlignment(Pos.CENTER_RIGHT);
        alignmentLabel.setPrefWidth(labelWidth);
        detailGrid.add(alignmentLabel, 0, 3);
        
        hitPointsLabel = new Label("HIT POINTS");
        hitPointsLabel.getStyleClass().add("brutal-label");
        hitPointsLabel.setAlignment(Pos.CENTER_RIGHT);
        hitPointsLabel.setPrefWidth(labelWidth);
        detailGrid.add(hitPointsLabel, 0, 4);
        
        armorClassLabel = new Label("ARMOR CLASS");
        armorClassLabel.getStyleClass().add("brutal-label");
        armorClassLabel.setAlignment(Pos.CENTER_RIGHT);
        armorClassLabel.setPrefWidth(labelWidth);
        detailGrid.add(armorClassLabel, 0, 5);*/
        
        
        nameField = new TextField();
        nameField.setPromptText("NAME");
        nameField.getStyleClass().add("brutal-text-field");
        detailGrid.add(nameField, 0, 0);
        
        
        sizeSelect = new ComboBox<>();
        sizeSelect.getStyleClass().add("brutal-combo-box");
        sizeSelect.setItems(FXCollections.observableArrayList(
                "Tiny", "Small", "Medium", "Large", "Huge", "Gargantuan"
                ));
        sizeSelect.setCellFactory(cellFactory);   
        sizeSelect.setMaxWidth(Double.MAX_VALUE);
        sizeSelect.setPromptText("SIZE");
        
        sizeField = new TextField();
        sizeField.setPromptText("SIZE");
        sizeField.getStyleClass().add("brutal-text-field");
        detailGrid.add(sizeSelect, 0, 1);
        
        
        typeSelect = new ComboBox<>();
        typeSelect.getStyleClass().add("brutal-combo-box");
        typeSelect.setItems(FXCollections.observableArrayList(
                "Aberration", "Beast", "Celestial", "Constructs", "Dragon",
                "Elemental", "Fey", "Fiend", "Giant", "Humanoid", "Monstrosity",
                "Ooze", "Plant", "Undead"
                ));
        typeSelect.setCellFactory(cellFactory);   
        typeSelect.setMaxWidth(Double.MAX_VALUE);
        typeSelect.setPromptText("TYPE");
        
        typeField = new TextField();
        typeField.getStyleClass().add("brutal-text-field");
        detailGrid.add(typeSelect, 0, 2);
        
        alignmentSelect = new ComboBox<>();
        alignmentSelect.getStyleClass().add("brutal-combo-box");
        alignmentSelect.setItems(FXCollections.observableArrayList(
                "Chaotic Good", "Neutral Good", "Lawful Good",
                "Chaotic Neutral", "Neutral", "Lawful Neutral",
                "Chaotic Evil", "Neutral Evil", "Lawful Evil"
                ));
        alignmentSelect.setCellFactory(cellFactory);   
        alignmentSelect.setMaxWidth(Double.MAX_VALUE);
        alignmentSelect.setPromptText("ALIGNMENT");
        
        alignmentField = new TextField();
        alignmentField.getStyleClass().add("brutal-text-field");
        detailGrid.add(alignmentSelect, 0, 3);
        
        hitPointsField = new TextField();
        hitPointsField.getStyleClass().add("brutal-text-field");
        hitPointsField.setPromptText("HIT POINTS");
        detailGrid.add(hitPointsField, 0, 4);
        
        armorClassField = new TextField();
        armorClassField.getStyleClass().add("brutal-text-field");
        armorClassField.setPromptText("ARMOR CLASS");
        detailGrid.add(armorClassField, 0, 5);
        
        
        
        statsTable = new StatTable();
        StackPane statGridHolder = new StackPane(statGrid);
        statGridHolder.getStyleClass().add("section-label");
        
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
               detailHolder,
               detailGrid, statHolder, statGridHolder, split[1],
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
        
        
        nameField.setText(source.getName());
        try {
            sizeSelect.getSelectionModel().select(source.getSize());
            typeSelect.getSelectionModel().select(source.getType());
            alignmentSelect.getSelectionModel().select(source.getAlignment());
        } catch(Exception ex) {
            Debugger.debug("Incorrect Selection Box: " + ex.getMessage());
        }
        
        hitPointsField.setText(Integer.toString(source.getHP()));
        armorClassField.setText(Integer.toString(source.getAC()));
        
        statGrid.setValues(source.getStats());
        
        profList.setContent(source.getProfs());
   
        
    }
    
    Callback<ListView<String>, ListCell<String>> cellFactory = new Callback<ListView<String>, ListCell<String>>() {

        @Override public ListCell<String> call(ListView<String> p) {
            return new ListCell<String>() {
                
                Label label;
                {
                    getStyleClass().add("brutal-combo-cell");
                    label = new Label();
                    
                }

                @Override protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        label.setText(item);
                        setGraphic(label);
                        
                    }
               }
          };
      }
    };
    
    
    EventHandler<ActionEvent> bottomButtonHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if(event.getSource().equals(saveButton)) {
                
                currentSource.setName(nameField.getText());
                currentSource.setType(typeField.getText());
                currentSource.setSize(nameField.getText());
                currentSource.setAlignment(alignmentField.getText());
                
                currentSource.setAC(Integer.parseInt(armorClassField.getText()));
                currentSource.setHP(Integer.parseInt(hitPointsField.getText()));
                
                
                
                
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

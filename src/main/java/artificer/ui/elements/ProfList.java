package main.java.artificer.ui.elements;

import java.util.Iterator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.Proficiency;
import main.java.artificer.stats.ProficiencyList;

/**
 * Custom ListView for Holding Proficiencies via ProfCells
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class ProfList extends VBox {
    
    private GridPane profEditor = new GridPane();
    private TextField newProfName = new TextField();
    private ModNumberField stat = new ModNumberField();
    private Button addButton = new Button();
    private SkillTypeSelector typeBox = new SkillTypeSelector();
    
    private Proficiency currentProf = null;
    
    //We have the ListView
    private ListView<Proficiency> profList = new ListView<Proficiency>();
    
    //We also have an observable list to manage.
    private ObservableList<Proficiency> obvsList =  FXCollections.observableArrayList();
    
    /**
     * Construct a new ProfList.s
     */
    public ProfList() {
        
        //This is so we can pass this object to the listCellFactory
        ProfList holder = this;
        
        profList.getStyleClass().add("prof-list");
        profList.setPrefSize(300, 180);
        profList.setCellFactory(new Callback<ListView<Proficiency>, ListCell<Proficiency>>(){
            @Override
            public ListCell<Proficiency> call(ListView<Proficiency> monsterListView) {
                return new ProfCell(holder);
                
            }
        });
        
        profList.setItems(obvsList);
        
        profList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Proficiency>() {

            @Override
            public void changed(ObservableValue<? extends Proficiency> observable, Proficiency oldValue,
                    Proficiency newValue) {
                setEditContext(newValue);
                
            }
            
        });
        
        profEditor.setMaxHeight(ProfCell.CELL_HEIGHT);
        profEditor.setPrefHeight(ProfCell.CELL_HEIGHT);
        
        profEditor.setPrefWidth(300);
        profEditor.getStyleClass().add("create-box");
        
        profEditor.setHgap(12);
        profEditor.getColumnConstraints().addAll(
                new ColumnConstraints(48),
                new ColumnConstraints(120),
                new ColumnConstraints(48)
                );
        
        
        stat.getStyleClass().add("number-field");
        stat.setOnAction(profFieldHandler);
        
        newProfName.getStyleClass().add("blank-text-field");
        newProfName.setPromptText("Add New...");
        newProfName.setOnAction(profFieldHandler);
        
        addButton.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/add_circle.png").toString())));
        addButton.setPadding(new Insets(6));
        
        
        profEditor.add(typeBox, 0, 0);
        profEditor.add(newProfName, 1, 0);
        profEditor.add(stat, 2, 0);
        
        
        
        getChildren().addAll(profList, profEditor);
        
    }
    
    /**
     * (Probable could have done this better with inheritance)
     * @return The actual ListView Node. This is for displaying purposes
     */
    public ListView<Proficiency> getProfList() {
        return profList;
    }
    
    /**
     * Set the initial content of the ProfList
     * 
     * @param source The Monster to take profs from
     */
    public void setContent(ProficiencyList source) {
        
        Iterable<Proficiency> iters = source.getAll();
        //mainList = new ArrayList<>();
        obvsList = FXCollections.observableArrayList();
        for(Proficiency p: iters) {
            obvsList.add(p);
        }
        profList.setItems(obvsList);
        
    }
    
    /**
     * 
     * @param prof The prof to remove
     */
    public void removeContent(Proficiency prof) {
        obvsList.remove(prof);
        
    }
    
    public Iterator<Proficiency> getCurrentContent() {
        return obvsList.iterator();
    }
    
    /**
     * 
     * @param prof The prof to add
     */
    public void addContent(Proficiency prof) {
        obvsList.add(prof);
        
    }
    
    public ProficiencyList getContent() {
        return null;
    }
    
    
    private void setEditContext(Proficiency p) {
        currentProf = p;
        newProfName.setText(p.getName());
        stat.setText(Integer.toString(p.getValue()));
    }
    
    /**
     * Replace a prof with a new one.
     * Useful for editing profs while maintaining our list.
     * 
     * @param prof The old proficiency
     * @param newProf The proficiency we want to replace it with
     */
    public void switchContent(Proficiency prof, Proficiency newProf) {
        int idx = obvsList.indexOf(prof);
        obvsList.set(idx, newProf);
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
            
            currentProf.setName(newProfName.getText());
            currentProf.setSkill(typeBox.getSelectionModel().getSelectedItem().contentEquals("Skill"));
            currentProf.setValue(mod);
            
            profList.refresh();
            
        }
        
    };
    
    
}

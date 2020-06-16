package main.java.artificer.ui.elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.prism.paint.Color;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import main.java.artificer.stats.Monster;
import main.java.artificer.stats.Proficiency;
import main.java.artificer.ui.detail.ModNumberField;

public class ProfList {
    private ListView<Proficiency> profList = new ListView<Proficiency>();
    private ObservableList<Proficiency> obvsList =  FXCollections.observableArrayList();
    
    private ArrayList<Proficiency> mainList;
    
    
    public ProfList() {
        
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
        
        
    }
    
    public ListView<Proficiency> getProfList() {
        return profList;
    }
    
    
    public void setContent(Monster source) {
        Iterator<Proficiency> iter = source.getStats().getAllProfs().iterator();
        //mainList = new ArrayList<>();
        obvsList = FXCollections.observableArrayList();
        while(iter.hasNext()) {
            Proficiency aProf = iter.next();
            obvsList.add(aProf);
            
        }
        profList.setItems(obvsList);
        updateContent();   
        
    }
    
    public void removeContent(Proficiency prof) {
        obvsList.remove(prof);
    }
    
    public void addContent(Proficiency prof) {
        obvsList.add(prof);
    }
    
    public void updateContent() {
        //profList.setItems(FXCollections.observableList(mainList));
    }
    
    public void switchContent(Proficiency prof, Proficiency newProf) {
        int idx = obvsList.indexOf(prof);
        obvsList.set(idx, newProf);
    }
    
    
}

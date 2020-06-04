package main.java.artificer.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.prism.paint.Color;

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

public class ProfList extends BorderPane {
    private ListView<Proficiency> profList = new ListView<Proficiency>();
    
    private Label profLabel = new Label("Proficiencies");
    
    private GridPane grid = new GridPane();
    private TextField name = new TextField();
    private ModNumberField stat = new ModNumberField();
    private Button addButton = new Button();
    
    
    public ProfList() {
        getStyleClass().add("prof-list");
        setPrefHeight(480);
        profList.setPrefSize(300, 360);
        profList.setMaxSize(300, 360);
        profList.setCellFactory(new Callback<ListView<Proficiency>, ListCell<Proficiency>>(){
            @Override
            public ListCell<Proficiency> call(ListView<Proficiency> monsterListView) {
                return new ProfCell();
                
            }
        });
        
        
        setTop(profLabel);
        setCenter(profList);
        
        
        grid.setMaxHeight(ProfCell.CELL_HEIGHT);
        grid.setPrefHeight(ProfCell.CELL_HEIGHT);
        
        grid.setPrefWidth(300);
        grid.getStyleClass().add("create-box");
        
        grid.setHgap(12);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(24),
                new ColumnConstraints(120),
                new ColumnConstraints(48)
                );
        
        
        stat.getStyleClass().add("number-field");
        
        addButton.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/add_circle.png").toString())));
        addButton.setPadding(new Insets(6));
        
        grid.add(addButton, 0, 0);
        grid.add(name, 1, 0);
        grid.add(stat, 2, 0);
        
        setBottom(grid);
        
        
        
    }
    
    
    public void setContent(Monster source) {
       
        
        Iterator<Proficiency> iter = source.getStats().getAllProfs().iterator();
        List<Proficiency> list = new ArrayList<>();
        while(iter.hasNext()) {
            list.add(iter.next());
            
        }
        profList.setItems(FXCollections.observableList(list));
        
        
    }
    
}

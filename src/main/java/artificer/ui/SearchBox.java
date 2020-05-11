package main.java.artificer.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchBox extends VBox {
    
    private ScrollPane scroller = new ScrollPane();
    private TextField searchField = new TextField();
    private Button searchButton = new Button("Search");
    private TextField filterField = new TextField("Filter...");
    private Button addButton = new Button("Create");
    
    public SearchBox() {
        setPrefSize(240, 540);
        setSpacing(12);
        
        searchButton.setText("Search");
        searchButton.setGraphic(new ImageView(new Image("main/resources/ui/icons/search.png")));
        HBox searchBox = new HBox();
        searchBox.setSpacing(6);
        searchBox.getChildren().add(searchField);
        searchBox.getChildren().add(searchButton);
        getChildren().add(searchBox);
        
        filterField.setPrefHeight(12);
        getChildren().add(filterField);
        
        scroller.setPrefSize(240, 480);
        getChildren().add(scroller);
        
        addButton.setGraphic(new ImageView(new Image("main/resources/ui/icons/add_circle.png")));
        getChildren().add(addButton);
        
        
    }
}

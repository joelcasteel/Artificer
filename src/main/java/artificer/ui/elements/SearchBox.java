package main.java.artificer.ui.elements;

import java.util.ArrayList;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.input.ClipboardContent;
//import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
//import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import main.java.artificer.ui.App;
import main.java.artificer.ui.SideRibbon;
import main.java.artificer.ui.menu.SideMenu;
import main.java.artificier.request.APIClient;
import main.java.artificier.request.Request;

/**
 * SearchBox UI element for API search requests
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public class SearchBox extends VBox implements Request {
    
    
    private ListView<MonsterCache> scroller = new ListView<MonsterCache>();
    private TextField searchField = new TextField();
    private Button searchButton = new Button();
    private HBox searchBox = new HBox();
    private TextField filterField = new TextField();
    private ImageView filterIcon = new ImageView(new Image(getClass().getResource("/ui/icons/filter.png").toString()));
    private HBox filterBox = new HBox();
    
    private ArrayList<MonsterCache> resultList;
    private ArrayList<MonsterCache> filteredList;
    
    SideMenu sideMenuParent;
    
    /**
     * Construct a new SearchBox
     * 
     * @param parent The SideRibbon Parent this belongs to.
     */
    public SearchBox() {
        
        
        getStylesheets().add(getClass().getResource(App.stylesheet).toString());
        getStyleClass().add("root");
        setId("search-menu");
       
        
        setPrefSize(240, 540);
        setSpacing(12);
        
        searchButton.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/search.png").toString())));
        searchButton.setOnAction(searchHandler);
        searchField.setOnAction(searchHandler);
        searchField.setPromptText("Search...");
        searchField.setPrefWidth(200);
        
        searchBox.getStyleClass().add("search-box");
        searchBox.setPrefHeight(18);
        searchBox.setSpacing(6);
        searchBox.getChildren().add(searchField);
        searchBox.getChildren().add(searchButton);
        getChildren().add(searchBox);
        
        StackPane filterImg = new StackPane();
        filterImg.setPadding(new Insets(6));
        filterImg.getChildren().add(filterIcon);
        filterField.setPrefWidth(200);
        filterField.setPromptText("Filter...");
        filterField.textProperty().addListener(filterHandler);
        //filterField.setId("filter-field");
        
        filterField.setPrefHeight(18);
        
        filterBox.getStyleClass().add("search-box");
        
        filterBox.setSpacing(6);
        filterBox.getChildren().addAll(
                filterField, filterImg
                );
        
        
        getChildren().add(filterBox);
        
        scroller.setPrefSize(240, 600 );
        scroller.getStyleClass().add("cool-list-view");
        scroller.setCellFactory(new Callback<ListView<MonsterCache>, ListCell<MonsterCache>>(){
            @Override
            public ListCell<MonsterCache> call(ListView<MonsterCache> monsterListView) {
                return new MonsterCell();
                
            }
        });
        
        getChildren().add(scroller);
        
        
    }
    
    @Override
    public void handleResponse(String response) {
        System.out.println(response);
        
        JsonObject source = JsonParser.parseString(response).getAsJsonObject();
        JsonArray results = source.get("results").getAsJsonArray();
        
        resultList = new ArrayList<>();
        
        for(int i = 0; i < results.size(); i++) {
            resultList.add(new MonsterCache(results.get(i).getAsJsonObject()));
        }
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                scroller.setItems(FXCollections.observableArrayList(resultList));
                
            }
        });
        
    }
    
    /**
     * Filters the results in the ListView
     * 
     * @param filText The Text to filter for
     */
    private void filterResults(String filText) {
        filteredList = new ArrayList<>();
        if(resultList != null) {
        for(MonsterCache card : resultList) {
            if(card.getName().toLowerCase().contains(filText.toLowerCase())) {
                filteredList.add(card);
            }
        }
        scroller.setItems(FXCollections.observableArrayList(filteredList));
        }
    }
    
    
    /**
     * Send the search request to the APIClient
     */
    public void search() {
        APIClient.asynchURLRequest(
                APIClient.createQuery(APIClient.MONSTER_SEARCH, searchField.getText()),
                this, 30);
    }
    
    /**
     * Event Handler for Searching
     */
    EventHandler<ActionEvent> searchHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            search();
        }
        
    };
    
    /**
     * Change listener for the filter text
     */
    ChangeListener<String> filterHandler = new ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            filterResults(newValue);
            
        }
    };
    
    /**
     * Event Handler for Drag and Drop (Unused)
     */
    EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
           /* Dragboard dragBoard = scroller.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();*/
            
            
        }
        
    };
}

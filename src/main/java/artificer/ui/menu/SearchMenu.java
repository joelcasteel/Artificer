package main.java.artificer.ui.menu;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import main.java.artificer.ui.App;
import main.java.artificer.ui.SideRibbon;
import main.java.artificer.ui.elements.MonsterCache;
import main.java.artificer.ui.elements.MonsterCell;
import main.java.artificer.ui.elements.SearchBox;
import main.java.artificer.ui.menu.MenuWrapper.MenuTitle;
import main.java.artificier.request.APIClient;
import main.java.artificier.request.Request;

public class SearchMenu extends VBox implements Collapsible, Request {
    
    private ListView<MonsterCache> scroller = new ListView<MonsterCache>();
    private StackPane listHolder = new StackPane();
    
    
    private TextField searchField = new TextField();
    private Button searchButton = new Button();
    private GridPane searchGrid = new GridPane();
    private StackPane searchHolder = new StackPane();
    
    private HBox searchBox = new HBox();
    private StackPane filterHolder = new StackPane();
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
    public SearchMenu() {
        
        
        getStylesheets().add(getClass().getResource(App.stylesheet).toString());
        getStyleClass().add("root");
        setId("search-menu");
       
        
        searchButton.setText("SEARCH");
        searchButton.setOnAction(searchHandler);
        searchButton.setId("search-button");
        searchButton.getStyleClass().add("brutal-button");
        searchButton.setPrefWidth(60);
        
        searchField.setOnAction(searchHandler);
        searchField.setPromptText("Search...");
        searchField.getStyleClass().add("brutal-blank-text-field");
        searchField.setPrefWidth(396-60);
        
        int fieldWidth = 432-24;
        searchGrid.getColumnConstraints().addAll(
                new ColumnConstraints(fieldWidth - 60),
                new ColumnConstraints(60)
                );
        searchGrid.getStyleClass().add("brutal-grid");
        searchGrid.add(searchField, 0, 0);
        searchGrid.add(searchButton, 1, 0);
        searchHolder.getStyleClass().add("brutal-holder");
        searchHolder.getChildren().add(searchGrid);
        getChildren().add(searchHolder);
        
        /*StackPane filterImg = new StackPane();
        filterImg.setPadding(new Insets(6));
        filterImg.getChildren().add(filterIcon);*/
        //filterField.setPrefWidth(200);
        
        //filterField.setId("filter-field");
        
        //filterField.setPrefHeight(18);
        
        filterField.setPromptText("Filter Results");
        filterField.textProperty().addListener(filterHandler);
        filterField.getStyleClass().add("brutal-text-field");
        
        filterHolder.getStyleClass().add("brutal-holder");
        filterHolder.getChildren().add(filterField);
        
        
        
        getChildren().add(filterHolder);
        
        scroller.setPrefSize(240, 600 );
        scroller.getStyleClass().add("brutal-list-view");
        scroller.setCellFactory(new Callback<ListView<MonsterCache>, ListCell<MonsterCache>>(){
            @Override
            public ListCell<MonsterCache> call(ListView<MonsterCache> monsterListView) {
                return new MonsterCell();
                
            }
        });
        
        listHolder.getStyleClass().add("brutal-holder");
        listHolder.getChildren().add(scroller);
        
        getChildren().add(listHolder);
        
        
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
        return MenuTitle.Search;
    }

}

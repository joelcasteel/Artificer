package main.java.artificer.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.time.Duration;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import main.java.artificer.stats.Monster;
import main.java.artificer.ui.menu.SideMenu;

public class SearchBox extends VBox {
    
    private String pre = "https://www.dnd5eapi.co/api/monsters/?name=";
    
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
    SideRibbon parentRibbon;
    
    
    public SearchBox(SideRibbon parent) {
        parentRibbon = parent;
        
        
        getStylesheets().add(getClass().getResource("/ui/styleSheets/search-bar.css").toString());
        getStyleClass().add("root");
       
        
        setPrefSize(240, 540);
        setSpacing(12);
        
        searchButton.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/search.png").toString())));
        searchButton.setOnAction(searchHandler);
        searchField.setOnAction(searchHandler);
        searchField.setPromptText("Search...");
        searchField.setPrefWidth(200);
        
        searchBox.getStyleClass().add("hbox");
        searchBox.setPrefHeight(18);
        searchBox.setSpacing(6);
        searchBox.getChildren().add(searchField);
        searchBox.getChildren().add(searchButton);
        getChildren().add(searchBox);
        
        StackPane filterImg = new StackPane();
        filterImg.setPadding(new Insets(4));
        filterImg.getChildren().add(filterIcon);
        filterField.setPrefWidth(200);
        filterField.setPromptText("Filter...");
        filterField.textProperty().addListener(filterHandler);
        
        filterField.setPrefHeight(18);
        
        filterBox.getStyleClass().add("hbox");
        
        filterBox.setSpacing(6);
        filterBox.getChildren().addAll(
                filterField, filterImg
                );
        
        
        getChildren().add(filterBox);
        
        scroller.setPrefSize(240, 600 );
        scroller.setCellFactory(new Callback<ListView<MonsterCache>, ListCell<MonsterCache>>(){
            @Override
            public ListCell<MonsterCache> call(ListView<MonsterCache> monsterListView) {
                return new MonsterCard();
                
            }
        });
        
        getChildren().add(scroller);
        
        
    }
    
    private void asynchURLRequest(String url) {
        System.out.println("Making Request: " + url);
        try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(1))
                .build();
       client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::handleResponse)
                .join();
        } catch (Exception ex) {
            System.out.println("Error making request: " + ex.getMessage());
        }
        
    }
    
    private void handleResponse(String response) {
        System.out.println(response);
        JsonObject source = JsonParser.parseString(response).getAsJsonObject();
        JsonArray results = source.get("results").getAsJsonArray();
        resultList = new ArrayList<>();
        for(int i = 0; i < results.size(); i++) {
            resultList.add(new MonsterCache(results.get(i).getAsJsonObject(), this));
        }
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                scroller.setItems(FXCollections.observableArrayList(resultList));
                
            }
        });
        
    }
    
    /**
     * This method is unused, but I decided to keep it anyway.
     * Not sure where it will be useful, but okay.
     * @param aUrl
     * @return
     */
    private String fetchURL(String aUrl) {
        System.out.println("Making Request to: " + aUrl);
        
        StringBuilder stringBuilder = new StringBuilder();
        URLConnection conn = null;
        InputStreamReader inputStream = null;
        try {
            URL url = new URL(aUrl);
            conn = url.openConnection();
            if(conn != null) {
                conn.setReadTimeout(20000);
                if(conn != null && conn.getInputStream() != null){
                    inputStream = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());
                    BufferedReader bufferedReader = new BufferedReader(inputStream);
                    if(bufferedReader != null){
                        int nextChar;
                        while((nextChar = bufferedReader.read()) != -1){
                          stringBuilder.append((char)nextChar);
                        }
                        bufferedReader.close();
                      }
                    }
                    inputStream.close(); 
                
            }
            
        } catch (Exception ex) {
            System.out.println("Error Retrieving Search result: " + ex.getMessage());

        }
        
        System.out.println("Finished Request");
        
        return stringBuilder.toString();
    }
    
    private void filterResults(String filText) {
        filteredList = new ArrayList<>();
        if(resultList != null) {
        for(MonsterCache card : resultList) {
            if(card.getName().contains(filText)) {
                filteredList.add(card);
            }
        }
        scroller.setItems(FXCollections.observableArrayList(filteredList));
        }
    }
    
    public SideRibbon getParentRibbon() {
        return parentRibbon;
        
    }
    
    EventHandler<ActionEvent> searchHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            asynchURLRequest(pre + searchField.getText().replaceAll(" ", "%20"));
            
        }
        
    };
    
    ChangeListener<String> filterHandler = new ChangeListener<String>() {

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            filterResults(newValue);
            
        }
    };
    
    EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            Dragboard dragBoard = scroller.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            
            
        }
        
    };
}

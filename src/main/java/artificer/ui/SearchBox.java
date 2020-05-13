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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchBox extends VBox {
    
    private String pre = "https://www.dnd5eapi.co/api/monsters/?name=";
    
    private ListView<Node> scroller = new ListView<Node>();
    private TextField searchField = new TextField();
    private Button searchButton = new Button("Search");
    private TextField filterField = new TextField("Filter...");
    private Button addButton = new Button("Create");
    
    
    
    
    public SearchBox() {
        setPrefSize(240, 540);
        setSpacing(12);
        
        searchButton.setText("Search");
        searchButton.setGraphic(new ImageView(new Image("main/resources/ui/icons/search.png")));
        searchButton.setOnAction(searchHandler);
        searchField.setOnAction(searchHandler);
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
        ListView<MonsterCard> resultView = new ListView<>();
        ArrayList<MonsterCard> list = new ArrayList<>();
        for(int i = 0; i < results.size(); i++) {
            list.add(new MonsterCard(results.get(i).getAsJsonObject()));
        }
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                scroller.setItems(FXCollections.observableArrayList(list));
                
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
    
    EventHandler<ActionEvent> searchHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            asynchURLRequest(pre + searchField.getText().replaceAll(" ", "%20"));
            
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

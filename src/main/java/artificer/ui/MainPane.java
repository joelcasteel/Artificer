package main.java.artificer.ui;

import javax.swing.GroupLayout.Alignment;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.java.artificer.ui.tabs.*;

public class MainPane extends BorderPane {
    
    HBox workbox;
   
    VBox sideRibbon;
    
    ImageView icon;
    
    Button search;
    Button monsters;
    Button items;
    Button encounters;
    Button add;
    
    
    BorderPane content;
    
    StackPane sidePane;
    VBox searchMenu;
    VBox encounterMenu;
    VBox monsterMenu;
    VBox itemMenu;
    
    Group searchGroup;
    Group encounterGroup;
    Group monsterGroup;
    Group itemGroup;
    
    StackPane workspace;
    TranslateTransition sideTranslation;
    
    
    public MainPane() {
        
        workbox = new HBox();
        
        sideRibbon  = new VBox();
        sideRibbon.setPadding(new Insets(12,6, 120,6));
        sideRibbon.setSpacing(12);
        sideRibbon.setAlignment(Pos.TOP_CENTER);
        BackgroundFill ribbonFill = new BackgroundFill(Color.DARKSEAGREEN,CornerRadii.EMPTY, Insets.EMPTY);
        sideRibbon.setBackground(new Background(ribbonFill));
        
        
        icon = new ImageView(new Image("main/resources/ui/icons/artificer_hand.png"));
        sideRibbon.getChildren().add(icon);
        Separator ribbonSeparator = new Separator();
        ribbonSeparator.setOrientation(Orientation.HORIZONTAL);
        ribbonSeparator.setPrefHeight(24);
        sideRibbon.getChildren().add(ribbonSeparator);
        
        search = new Button();
        search.setGraphic(new ImageView(new Image("main/resources/ui/icons/search.png")));
        search.setOnAction(ribbonButtonListener);
        sideRibbon.getChildren().add(search);
        
        encounters = new Button();
        encounters.setGraphic(new ImageView(new Image("main/resources/ui/icons/create.png")));
        sideRibbon.getChildren().add(encounters);
        
        monsters = new Button();
        monsters.setGraphic(new ImageView(new Image("main/resources/ui/icons/monster.png")));
        sideRibbon.getChildren().add(monsters);
        
        items = new Button();
        items.setGraphic(new ImageView(new Image("main/resources/ui/icons/items.png")));
        sideRibbon.getChildren().add(items);
        
        
        add = new Button();
        add.setGraphic(new ImageView(new Image("main/resources/ui/icons/add_circle.png")));
        sideRibbon.getChildren().add(add);
       
        
        searchGroup = new Group(new Label("SEARCHGROUP"));
        searchGroup.setVisible(true);
        encounterGroup = new Group(new Label("ENCOUNTERGROUP"));
        encounterGroup.setVisible(true);
        
        
        workspace = new StackPane();
        HBox.setHgrow(workspace, Priority.ALWAYS);
        workspace.getChildren().add(new Label("CONTENT"));
        workspace.setAlignment(Pos.TOP_LEFT);
        content.setCenter(workspace);
        
        workbox.getChildren().add(sideRibbon);
        workbox.getChildren().add(searchGroup);
        workbox.getChildren().add(workspace);
        
        setCenter(workbox);
        

    }
    
    EventHandler<ActionEvent> ribbonButtonListener = new EventHandler<>() {

        @Override
        public void handle(ActionEvent event) {
            //sideTranslation.setRate(1);
            //sideTranslation.play();
            if(searchGroup.isVisible()) {
                searchGroup.setVisible(false);
                searchGroup.setManaged(false);
                //sidePane.getChildren().remove(searchGroup);
                //encounterGroup.setVisible(false);
                
            } else {
                searchGroup.setVisible(true);
                searchGroup.setManaged(true);
                //sidePane.setRight(searchGroup);
            }
            
        }
        
    };
    
    
    
}

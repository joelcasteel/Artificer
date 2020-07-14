package main.java.artificer.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.java.artificer.ui.menu.MenuWrapper;
import main.java.artificer.ui.menu.MenuWrapper.MenuTitle;

/**
 * The interactive Side-Ribbon that allows menu selection
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class SideRibbon extends HBox {
    
    VBox sideRibbon;
    
    ImageView icon;
    
    Button search;
    Button monsters;
    Button items;
    Button encounters;
    Button add;
    Button settings;
    
    StackPane menuPane;
    //OldSearchMenu oldSearchMenu;
    
    
    /**
     * Construct the SideRibbon
     */
    public SideRibbon() {
      //SIDE RIBBON
        sideRibbon  = new VBox();
        getStylesheets().add(getClass().getResource(App.stylesheet).toString());
        sideRibbon.setId("side-ribbon");
        //RIBBON SETUP
        try {
            
        icon = new ImageView(new Image(getClass().getResource("/ui/icons/artificer_hand.png").toString()));
        } catch (Exception ex) {
            System.out.println("OOPS: " + ex.getMessage());
        }
        sideRibbon.getChildren().add(icon);
        Separator ribbonSeparator = new Separator();
        ribbonSeparator.setOrientation(Orientation.HORIZONTAL);
        ribbonSeparator.setPrefHeight(36);
        ribbonSeparator.setVisible(false);
        sideRibbon.getChildren().add(ribbonSeparator);
        
        //RIBBON BUTTONS
        search = new Button();
        search.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/search.png").toString())));
        search.setOnAction(ribbonButtonListener);
        sideRibbon.getChildren().add(search);
        
        encounters = new Button();
        encounters.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/create.png").toString())));
        encounters.setOnAction(ribbonButtonListener);
        sideRibbon.getChildren().add(encounters);
        
        monsters = new Button();
        monsters.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/monster.png").toString())));
        monsters.setOnAction(ribbonButtonListener);
        sideRibbon.getChildren().add(monsters);
        
        items = new Button();
        items.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/items.png").toString())));
        sideRibbon.getChildren().add(items);
        
        
        add = new Button();
        add.setGraphic(new ImageView(new Image(getClass().getResource("/ui/icons/add_circle.png").toString())));
        sideRibbon.getChildren().add(add);
        
        
        
        
        
        
        getChildren().addAll(sideRibbon);
    }
    

    
    /**
     * Listener for Ribbon Button interactions.
     */
    EventHandler<ActionEvent> ribbonButtonListener = new EventHandler<>() {

        @Override
        public void handle(ActionEvent event) {
            MenuWrapper wrapper = MenuWrapper.getInstance();
            MenuTitle selection = MenuTitle.Search;
            
            if(event.getSource().equals(search)) {
                selection = MenuTitle.Search;
                
            } else if (event.getSource().equals(monsters)) {
                selection = MenuTitle.Details;
            } else if(event.getSource().equals(encounters)) {
                selection = MenuTitle.Test;
            }
            
            if(wrapper.isOpen()) {
                if(wrapper.getContext().equals(selection)) {
                    wrapper.collapse();
                } else {
                    wrapper.changeContext(selection);
                }
            } else {
                wrapper.changeContext(selection);
            }
            
        }
        
    };
}

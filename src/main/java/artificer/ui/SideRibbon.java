package main.java.artificer.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.java.artificer.ui.detail.MonsterDetail;
import main.java.artificer.ui.menu.MenuWrapper;
import main.java.artificer.ui.menu.SearchMenu;

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
    MenuWrapper menuWrapper;
    SearchMenu searchMenu;
    
    StackPane detailPane;
    MonsterDetail monsterDetail;
    
    
    public SideRibbon() {
      //SIDE RIBBON
        sideRibbon  = new VBox();
        sideRibbon.setPadding(new Insets(12,6, 120,6));
        sideRibbon.setSpacing(12);
        sideRibbon.setAlignment(Pos.TOP_CENTER);
        BackgroundFill ribbonFill = new BackgroundFill(Color.DARKSEAGREEN,CornerRadii.EMPTY, Insets.EMPTY);
        sideRibbon.setBackground(new Background(ribbonFill));
        
        //RIBBON SETUP
        icon = new ImageView(new Image("main/resources/ui/icons/artificer_hand.png"));
        sideRibbon.getChildren().add(icon);
        Separator ribbonSeparator = new Separator();
        ribbonSeparator.setOrientation(Orientation.HORIZONTAL);
        ribbonSeparator.setPrefHeight(36);
        ribbonSeparator.setVisible(false);
        sideRibbon.getChildren().add(ribbonSeparator);
        
        //RIBBON BUTTONS
        search = new Button();
        search.setGraphic(new ImageView(new Image("main/resources/ui/icons/search.png")));
        search.setOnAction(ribbonButtonListener);
        sideRibbon.getChildren().add(search);
        
        encounters = new Button();
        encounters.setGraphic(new ImageView(new Image("main/resources/ui/icons/create.png")));
        sideRibbon.getChildren().add(encounters);
        
        monsters = new Button();
        monsters.setGraphic(new ImageView(new Image("main/resources/ui/icons/monster.png")));
        monsters.setOnAction(ribbonButtonListener);
        sideRibbon.getChildren().add(monsters);
        
        items = new Button();
        items.setGraphic(new ImageView(new Image("main/resources/ui/icons/items.png")));
        sideRibbon.getChildren().add(items);
        
        
        add = new Button();
        add.setGraphic(new ImageView(new Image("main/resources/ui/icons/add_circle.png")));
        sideRibbon.getChildren().add(add);
        
        
        menuWrapper = new MenuWrapper();
        
        
        detailPane = new StackPane();
        monsterDetail = new MonsterDetail();
        detailPane.getChildren().addAll(monsterDetail);
        
        
        
        getChildren().addAll(sideRibbon, menuWrapper, detailPane);
    }
    
    EventHandler<ActionEvent> ribbonButtonListener = new EventHandler<>() {

        @Override
        public void handle(ActionEvent event) {
            if(event.getSource().equals(search)) {
                menuWrapper.SwitchContext(MenuWrapper.SEARCH_MENU);
            } else if (event.getSource().equals(monsters)) {
                menuWrapper.SwitchContext(MenuWrapper.MONSTER_MENU);
            }
            
        }
        
    };
}

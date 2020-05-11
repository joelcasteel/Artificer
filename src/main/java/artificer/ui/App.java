package main.java.artificer.ui;

import javax.swing.UIManager;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    
    MainPane main = new MainPane();
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Scene scene = new Scene(main, 1200, 600);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        primaryStage.getIcons().add(new Image("main/resources/ui/icons/artificer_hand.png"));
        
        primaryStage.setTitle("Artificer Version: 0.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

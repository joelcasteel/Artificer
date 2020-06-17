package main.java.artificer.ui;

import javax.swing.UIManager;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Start an FX application with the proper stylesheet.
 * 
 * @author Joel Casteel
 * @version June 2020
 *
 */
public class App extends Application {
    
    //The actual main pane that holds application content
    MainPane main = new MainPane();
    
    public static String stylesheet = "/ui/stylesheets/artificer-light.css";
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Scene scene = new Scene(main, 1200, 600);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        scene.getStylesheets().add(getClass().getResource(stylesheet).toString());
        primaryStage.getIcons().add(new Image(getClass().getResource("/ui/icons/artificer_hand.png").toString()));
        
        primaryStage.setTitle("Artificer Version: 0.1");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}

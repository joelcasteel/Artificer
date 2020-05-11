package main.java.artificer.ui;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    
    MainScreen main = new MainScreen();
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Scene scene = new Scene(main, 800, 600);
        
        primaryStage.setTitle("Artificer Version: 0.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

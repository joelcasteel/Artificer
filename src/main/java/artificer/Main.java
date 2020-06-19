package main.java.artificer;

import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.application.*;
import main.java.artificer.filesystem.FileSystem;
import main.java.artificer.ui.App;


/**
 * Entry Point for the Artificer application.
 * 
 * Currently the class only initializes some basic json files and launches the UI applications.
 * 
 * @author Joel Casteel
 * @version May 2020
 *
 */
public class Main {
    
    //SAVE DIRECTORY CODE -- THIS WILL BE REPLACED BY A FILESYSTEM CLASS AND POTENTIALLY A PACKAGE
    //String for the saveDir.
    public static String saveDir;
    
    public static FileSystem fileSystem;
    
    
	public static void main(String[] args) {
	    
	    fileSystem = FileSystem.getInstance();
	    fileSystem.writeMonster("{HEEEEyyy}");
	    
	    
	    
	    //I'm using this as a workaround because of fucking eclipse.
	    //Launches the GUI Class.
		Application.launch(App.class, args);
	}
	


}
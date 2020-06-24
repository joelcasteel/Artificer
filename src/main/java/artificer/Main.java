package main.java.artificer;

import javafx.application.*;
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
    
    
	public static void main(String[] args) {
	    
	    //I'm using this as a workaround because of fucking eclipse.
	    //Launches the GUI Class.
		Application.launch(App.class, args);
	}
	


}
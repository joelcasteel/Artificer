package main.java.artificer;

import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.*;
import main.java.artificer.ui.App;
import main.java.artificier.request.APIClient;


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
    
    
	public static void main(String[] args) {
	    
	    saveDir = System.getProperty("user.home") +  "\\.artificer\\";
	    File file = new File(saveDir);
	    
	    if(!file.exists()) {
	        file.mkdir();
	    }
	    System.out.println(saveDir);
	    try {
	        JsonObject src = new JsonObject();
	        src.addProperty("Hello", "There");
	        Gson gson = new Gson();
	        String json = gson.toJson(src);
	        FileWriter writer = new FileWriter(saveDir + "monsters.json");
	        writer.write(json);
	        writer.close();
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    //END OF FILESYSTEM CODE
	    
	    
	    
	    //I'm using this as a workaround because of fucking eclipse.
	    //Launches the GUI Class.
		Application.launch(App.class, args);
	}
	


}
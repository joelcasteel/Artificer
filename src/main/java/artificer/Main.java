package main.java.artificer;

import java.io.File;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.*;
import main.java.artificer.ui.App;



public class Main {
    
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
	    
	    
	    
	    //I'm using this as a workaround because fucking eclipse.
		Application.launch(App.class, args);
	}
	


}
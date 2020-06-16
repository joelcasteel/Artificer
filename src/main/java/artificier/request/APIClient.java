package main.java.artificier.request;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

/**
 * Make Asynchronous HTTP Request via a static method.
 * 
 * @author Joel Casteel
 * @Version June 2020
 *
 */
public class APIClient {
    
    public static String PRE = "https://www.dnd5eapi.co";
    
    public static String MONSTER_SEARCH = "/api/monsters/?name=";
    
    
    
    public static HttpClient client = HttpClient.newHttpClient();
    
    /**
     * Make Asynch calls to the API site.
     * 
     * @param url
     * @param req
     * @param duration
     */
    public static void asynchURLRequest(String url, Request req, long duration) {
        try {
            //Create the request object with a duration of seconds
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(duration))
                    .build();
           
            //Send the asynchronous request.
            // I can't tell if this is actually asynchronous... Currently the UI Freezes.
            // I believe that calling a method from the UI thread kinda ruins it.
            client.sendAsync(request, BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(req::handleResponse)
                    .join();
           
        } catch (Exception ex) {
            System.out.println("Error making request for " + req.getClass() 
                +": " + ex.getMessage());
        }
    }

    public static String createURL(String url) {
        return PRE + url;
    }
    
    public static String createQuery(String typeUrl, String name) {
        return PRE + typeUrl + name.replaceAll(" ", "%20");
    }
   
}

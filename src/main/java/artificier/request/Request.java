package main.java.artificier.request;

/**
 * Request interface for the APIClient
 * 
 * @author Joel Castel
 * @version June 2020
 *
 */
public interface Request {
    /**
     * This method is used for handling the response from an API request via the HTTP client.
     * 
     * @param repsonse The string returned by the API
     */
    public void handleResponse(String repsonse);
}

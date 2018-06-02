package javaapplication2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author falcu
 */
public class RequestResponse {

    Weather we = new Weather();
    private String city;
    private final String appID = "63517466dcd2284276d4da5d4e897dd5";

    public RequestResponse(String city) throws Exception {
        this.city = city;
        establishConnection();
    }

    private void establishConnection() throws Exception {

        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + appID;
        URL requestURL = new URL(url);
        HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();

        switch (responseCode) {
            case 404:
                JOptionPane.showMessageDialog(null, "No such city in database");
                throw new Exception("No such city in database"); // Handling 404 error code.
            case 400:
                JOptionPane.showMessageDialog(null, "No City Entered in Search field");
                throw new Exception("No city entered in search field"); // Handling 400 error code.
            case 401:
                JOptionPane.showMessageDialog(null, "API Error, please try again later");
                throw new Exception("API Error, please try again later"); // Handling 401 error code.
        }

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        getResponse(con);
    }

    public void getResponse(HttpURLConnection huc) throws Exception {

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(huc.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        System.out.println(response.toString());

        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");

        JSONArray weatheArray = myResponse.getJSONArray("weather");

        we.setCityName(city);
        we.setWeatherDesc(weatheArray.getJSONObject(0).get("description").toString());
        we.setId(weatheArray.getJSONObject(0).getInt("id"));
        we.setTemperature(myResponse.getJSONObject("main").getFloat("temp"));
        we.setPressure(myResponse.getJSONObject("main").getLong("pressure"));

    }
}

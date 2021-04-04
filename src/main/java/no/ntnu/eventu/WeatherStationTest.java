package no.ntnu.eventu;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.*;

class WeatherStationTest {


    private String locationZipCode = "6429";
    private String countryCode = "no";
    private String API_KEY = "5543690c7b180d6a47132920e1c24c66";
    private String urlString = "http://api.openweathermap.org/data/2.5/weather?zip=" + locationZipCode + "," + countryCode + "&appid=" + API_KEY + "&units=metric" + "&lang=no";
    private OkHttpClient client;
    private Response response;


    WeatherStationTest() throws IOException {
    }

    public JSONObject getWeather(){
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONArray getWeatherArray(){
        JSONArray weatherArray = getWeather().getJSONArray("weather");
        return weatherArray;
    }

    @Test
    public JSONObject getMain(){
        JSONObject main = getWeather().getJSONObject("main");
        System.out.println(main.toString());
        return main;

    }


}
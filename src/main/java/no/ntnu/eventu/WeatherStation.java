package no.ntnu.eventu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.json.JSONException;

public class WeatherStation {

    private String locationZipCode = "6429";
    private String countryCode = "no";
    private String API_KEY = "5543690c7b180d6a47132920e1c24c66";
    private String urlString = "http://api.openweathermap.org/data/2.5/weather?zip=" + locationZipCode + "," + countryCode + "&appid=" + API_KEY + "&units=metric" + "&lang=no";
    private OkHttpClient client;
    private Response response;
    int temp;
    String description;
    String windSpeed;
    String clouds;
    int windDirection;
    int feltTemp;
    public String icon;
    private JSONObject jsonWeather;
    String city;


    /**
     * Returns a JasonObject
     * @param urlString
     * @return jsonObject
     * @throws IOException
     */
    public JSONObject getJson(String urlString) throws IOException {
        InputStream is = new URL(urlString).openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonString = read(reader);
            JSONObject json = new JSONObject(jsonString);
            return json;
        }   finally {
            is.close();
        }
    }

    private String read(Reader rd) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        while ((i = rd.read()) != -1){
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }

    public void getWeather(){

        JSONObject json = new JSONObject();
        JSONObject jsonObject;
        try {
            json = getJson("http://api.openweathermap.org/data/2.5/weather?zip=" + locationZipCode + "," + "no" + "&appid=" + API_KEY + "&units=metric" + "&lang=no");
        }catch (IOException ignored){
        }
        jsonObject = json.getJSONObject("main");
        this.temp = jsonObject.getInt("temp");
        this.feltTemp = jsonObject.getInt("feels_like");
        jsonObject = json.getJSONObject("wind");
        this.windSpeed = jsonObject.get("speed").toString();
        this.windDirection = jsonObject.getInt("deg");
        jsonObject = json.getJSONObject("clouds");
        this.clouds = jsonObject.get("all").toString();

        this.city = json.get("name").toString();


        jsonObject = json.getJSONArray("weather").getJSONObject(0);
        //jsonWeather = json.getJSONArray("weather").getJSONObject(0);
        this.description = jsonObject.get("description").toString();
        this.icon = jsonObject.get("icon").toString();
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon(){
        return this.icon;
    }

    public String getCity(){
        return this.city;
    }

    public int getFeltTemp(){
        return feltTemp;
    }


    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getTempDescription(){
        return getTemp()+"℃" + "\n Føles som:" ;
    }

    public String getTempString(){
    return getTemp() + "℃";
    }


    public String getWindDirection(){
        if((windDirection <= 23 && windDirection >= 0)|| (337 <= windDirection && windDirection<= 360)){
            return  "nord";
        } else if ( windDirection >= 24 && windDirection <= 68){
            return "Nordøst";
        }else if (69 <= windDirection && windDirection <= 113){
            return "Øst";
        } else if (windDirection >= 114 && windDirection <=158){
            return "Sørøst";
        } else if (windDirection >= 159 && windDirection <= 203){
            return "Sør";
        } else if ( windDirection >= 204 && windDirection <= 248 ){
            return "Sørvest";
        } else if(windDirection >= 249 && windDirection <= 293){
            return "Vest";
        } else if (windDirection >= 294 && windDirection <= 336){
            return "Norvest";
        }
        else return "";

    }

    public String getWindDescription(){
        return getWindSpeed()+ "m/s fra " +getWindDirection();
    }

    public String getDescription() {
        return description;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getClouds() {
        return clouds;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }


    public WeatherStation(String  locationZipcode){
        this.locationZipCode = locationZipcode;
    }
































    /**
     *
     * @return
     * @throws JSONException

    public JSONObject getWeather()  throws JSONException {
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

    public JSONArray getWeatherArray() throws JSONException{
        JSONArray weatherArray = getWeather().getJSONArray("weather");
        return weatherArray;
    }

    public JSONObject getMain()  throws JSONException {
        JSONObject main = getWeather().getJSONObject("main");
        return main;
    }
    



    public WeatherStation() {
    } */
}

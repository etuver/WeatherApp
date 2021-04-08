package no.ntnu.eventu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Class to get current weather
 * Uses different URL than forecast
 */
public class CurrentWeather {

    public String icon;
    public String dt_txt;
    public double temp;
    public double feltTemp;
    public double windSpeed;
    public int windDirection;
    public String description;
    public String time;
    String city;
    public long dt;
    private String locationZipCode = "6429";

    //JSONObject currentWeather = (JSONObject) jsonParser.parse(reader);

    //Empty
    public CurrentWeather(String text) throws IOException, ParseException {
    }


    public void getCurrentWeather(String locationZipCode) throws IOException, ParseException {


        JSONParser jsonParser = new JSONParser();
        LocalDateTime today = LocalDateTime.now();
        ZoneId z = ZoneId.of("Europe/Oslo");
        String API_KEY = "5543690c7b180d6a47132920e1c24c66";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?zip=" + locationZipCode + "," + "no" + "&appid=" + API_KEY + "&units=metric" + "&lang=no";
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JSONObject currentWeather = (JSONObject) jsonParser.parse(reader);
        JSONObject main = (JSONObject) currentWeather.get("main");
        JSONArray weather = (JSONArray) currentWeather.get("weather");
        JSONObject thisweather = (JSONObject) weather.get(0);
        this.dt = (long) currentWeather.get("dt");
        this.icon = ((String) thisweather.get("icon"));
        this.description = (String) thisweather.get("description");
        String mainTemp =  String.valueOf(main.get("temp")) ;
        this.temp = Double.parseDouble(mainTemp);
        this.feltTemp =(Double.parseDouble(String.valueOf(main.get("feels_like"))));
        this.city = (String) currentWeather.get("name");
        JSONObject windObject = (JSONObject) currentWeather.get("wind");
        this.windSpeed = Double.parseDouble(String.valueOf(windObject.get("speed")));
        this.windDirection = Integer.parseInt(String.valueOf(windObject.get("deg")));
        //JSONObject windObject = (JSONObject) currentWeather.get("wind");
        //this.windSpeed = Integer.parseInt(String.valueOf(windObject.get("speed")));
        //this.windDirection = (int) Long.parseLong(String.valueOf(windObject.get("deg")));
        Instant instant = Instant.ofEpochSecond(dt);
        ZonedDateTime zdt = instant.atZone(z);
        LocalDateTime localTime = zdt.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.time = localTime.format(formatter);
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
}

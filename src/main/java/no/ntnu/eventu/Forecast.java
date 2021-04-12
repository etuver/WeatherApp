package no.ntnu.eventu;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Forecast {



    // Weather variables for each days. should prob move these to a list in the future
    // For now focus is on getting api to work properly for both forecast and current

    /**
     * Day2
     */
    public String day2Icon;
    public String day2Dt_txt;
    public double day2Temp;
    public double day2FeltTemp;
    public double day2WindSpeed;
    public int day2WindDirection;
    public String day2Description;
    public String day2Time;
    public long day2Dt;



    /**
     * Day 3
     */
    public String day3Icon;
    public String day3Dt_txt;
    public double day3Temp;
    public double day3FeltTemp;
    public double day3WindSpeed;
    public int day3WindDirection;
    public String day3Description;
    public String day3Time;
    public long day3Dt;

    /**
     * Day 4
     */
    public String day4Icon;
    public String day4Dt_txt;
    public double day4Temp;
    public double day4FeltTemp;
    public double day4WindSpeed;
    public int day4WindDirection;
    public String day4Description;
    public String day4Time;
    public long day4Dt;

    /**
     * Day 5
     */
    public String day5Icon;
    public String day5Dt_txt;
    public double day5Temp;
    public double day5FeltTemp;
    public double day5WindSpeed;
    public int day5WindDirection;
    public String day5Description;
    public String day5Time;
    public long day5Dt;



    //List with all days
    JSONArray list;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    ZoneId z = ZoneId.of("Europe/Oslo");

    //Empty
    public Forecast(String zipCode){

    }

    /**
     * Connects to the irl with chosen zipcode
     * Fills list with list of dates from the api
     * @param locationZipCode
     * @throws IOException
     * @throws ParseException
     */
    public void getForeCast(String locationZipCode) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        String API_KEY = "5543690c7b180d6a47132920e1c24c66";
        String urlString = "http://api.openweathermap.org/data/2.5/forecast?zip=" + locationZipCode + "," + "no" + "&appid=" + API_KEY + "&units=metric" + "&lang=no";
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        list = (JSONArray) jsonObject.get("list");
        reader.close();
    }

    public void setday2(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1).withHour(14).withMinute(0).withSecond(0).withNano(0);
        long tomorrowAsEpoch = tomorrow.atZone(z).toEpochSecond();
        for (Object o : list){
            JSONObject forecast = (JSONObject) o;
            long dtt = (long) forecast.get("dt");
            if (dtt == tomorrowAsEpoch){
                JSONObject main = (JSONObject) forecast.get("main");
                day2Temp = (double) main.get("temp");
                day2FeltTemp = (double) main.get("feels_like");
                JSONArray weather = (JSONArray) forecast.get("weather");
                JSONObject weatherForecast = (JSONObject) weather.get(0);
                day2Icon = (String) weatherForecast.get("icon");
                day2Description = (String) weatherForecast.get("description");
                day2Dt_txt = (String) forecast.get("dt_txt");
                JSONObject windObject = (JSONObject) forecast.get("wind");
                day2WindSpeed = Double.parseDouble(String.valueOf(windObject.get("speed")));
                day2WindDirection = Integer.parseInt(String.valueOf(windObject.get("deg")));
                day2Time = String.format(day2Dt_txt, formatter);
            }


        }
    }

    public void setDay3(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime day3 = today.plusDays(2).withHour(14).withMinute(0).withSecond(0).withNano(0);
        long day3AsEpoch = day3.atZone(z).toEpochSecond();
        for (Object o: list){
            JSONObject forecast = (JSONObject) o;
            long dtt = (long) forecast.get("dt");
            if (dtt == day3AsEpoch){
                JSONObject main = (JSONObject) forecast.get("main");
                JSONArray weather = (JSONArray) forecast.get("weather");
                JSONObject weatherForecast = (JSONObject) weather.get(0);
                JSONObject windObject = (JSONObject) forecast.get("wind");
                day3Dt_txt = (String) forecast.get("dt_txt");
                day3Time = String.format(day3Dt_txt, formatter);
                day3Temp = (double) main.get("temp");
                day3FeltTemp = (double) main.get("feels_like");
                day3Description = (String) weatherForecast.get("description");
                day3Icon = (String) weatherForecast.get("icon");
                day3WindSpeed = Double.parseDouble(String.valueOf(windObject.get("speed")));
                day3WindDirection = Integer.parseInt(String.valueOf(windObject.get("deg")));
            }
        }
    }



    public void setDay4(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime day4 = today.plusDays(3).withHour(14).withMinute(0).withSecond(0).withNano(0);
        long day4AsEpoch = day4.atZone(z).toEpochSecond();
        for (Object o: list){
            JSONObject forecast = (JSONObject) o;
            long dtt = (long) forecast.get("dt");
            if (dtt == day4AsEpoch){
                JSONObject main = (JSONObject) forecast.get("main");
                JSONArray weather = (JSONArray) forecast.get("weather");
                JSONObject weatherForecast = (JSONObject) weather.get(0);
                JSONObject windObject = (JSONObject) forecast.get("wind");
                day4Dt_txt = (String) forecast.get("dt_txt");
                day4Time = String.format(day4Dt_txt, formatter);
                day4Temp = (double) main.get("temp");
                day4FeltTemp = (double) main.get("feels_like");
                day4Description = (String) weatherForecast.get("description");
                day4Icon = (String) weatherForecast.get("icon");
                day4WindSpeed = Double.parseDouble(String.valueOf(windObject.get("speed")));
                day4WindDirection = Integer.parseInt(String.valueOf(windObject.get("deg")));
            }
        }
    }

    public void setDay5(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime day5 = today.plusDays(4).withHour(14).withMinute(0).withSecond(0).withNano(0);
        long day5AsEpoch = day5.atZone(z).toEpochSecond();
        for (Object o: list){
            JSONObject forecast = (JSONObject) o;
            long dtt = (long) forecast.get("dt");
            if (dtt == day5AsEpoch){
                JSONObject main = (JSONObject) forecast.get("main");
                JSONArray weather = (JSONArray) forecast.get("weather");
                JSONObject weatherForecast = (JSONObject) weather.get(0);
                JSONObject windObject = (JSONObject) forecast.get("wind");
                day5Dt_txt = (String) forecast.get("dt_txt");
                day5Time = String.format(day4Dt_txt, formatter);
                day5Temp = (double) main.get("temp");
                day5FeltTemp = (double) main.get("feels_like");
                day5Description = (String) weatherForecast.get("description");
                day5Icon = (String) weatherForecast.get("icon");
                day5WindSpeed = Double.parseDouble(String.valueOf(windObject.get("speed")));
                day5WindDirection = Integer.parseInt(String.valueOf(windObject.get("deg")));
            }
        }

    }



    public String getWindDirection(int i){
        if((i <= 23 && i >= 0)|| (337 <= i && i<= 360)){
            return  "nord";
        } else if ( i >= 24 && i <= 68){
            return "Nordøst";
        }else if (69 <= i && i <= 113){
            return "Øst";
        } else if (i >= 114 && i <=158){
            return "Sørøst";
        } else if (i >= 159 && i <= 203){
            return "Sør";
        } else if ( i >= 204 && i <= 248 ){
            return "Sørvest";
        } else if(i >= 249 && i <= 293){
            return "Vest";
        } else if (i >= 294 && i <= 336){
            return "Norvest";
        }
        else return "";

    }























}

package no.ntnu.eventu;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class to get weather for upcoming days
 */
public class TomorrowForecast {
    private final String API_KEY = "5543690c7b180d6a47132920e1c24c66";
    private String locationZipCode = "6429";
    private String countryCode = "no";
    private String urlString = "http://api.openweathermap.org/data/2.5/forecast?zip=" + locationZipCode + "," + "no" + "&appid=" + API_KEY + "&units=metric" + "&lang=no&cnt=5";
    private int tomorrowTemp;
    private int tomorrowWindDirection;
    private int intTomorrowFeltTemp;
    private String tomorrowDescription;
    private String tomorrowWindspeed;
    private String tomorrowClouds;
    private String tomorrowIcon;


    public TomorrowForecast(String locationZipCode) throws IOException {
        this.locationZipCode = locationZipCode;
    }

    StringBuilder stringBuilder = new StringBuilder();
    public String read(Reader rd) throws IOException {
        int i;
        while ((i = rd.read()) != -1){
            stringBuilder.append((char) i);
        }
        return stringBuilder.toString();
    }


    public JSONObject getJson(String urlString) throws IOException {
        InputStream inputStream = new URL(urlString).openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonStringForeCast = read(reader);
            JSONObject forecastJson = new JSONObject(jsonStringForeCast);
            return forecastJson;
        } finally {
            inputStream.close();
        }
    }




    ArrayList<Map<String, Object>> forecastArray;
    Map<String, Object> forecast;
    Map<String,Object> fooList;
    List basj;
    public void getForecast(){
        JSONObject json =  new JSONObject();
        JSONObject jsonObject;
        JSONArray jsonArray;
        try {
            json = getJson("http://api.openweathermap.org/data/2.5/forecast?zip=" + locationZipCode + "," + "no" + "&appid=" + API_KEY + "&units=metric" + "&lang=no&cnt=5");
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }

        forecast = jsonToMap(stringBuilder.toString());
        System.out.println("forecast"+forecast.toString());
        //System.out.println("dsa"+forecast.get("list"));
        basj = (List) forecast.get("list");
        System.out.println("basj"+basj.toString());
        System.out.println("bash2"+basj.get(3));
        forecastArray = (ArrayList<Map<String, Object>>) forecast.get("list");
        Map<String, Object> firstDay = (Map<String, Object>) basj.get(2);
        System.out.println("firstday" + firstDay);
        System.out.println(firstDay.get("dt"));
        Map fishNchips = (Map) firstDay.get("main");
        System.out.println("fishnchips"+ fishNchips.toString());
        //System.out.println(firstDay.get("dt_txt"));
        Object temp = fishNchips.get("temp");
        System.out.println(temp);
        System.out.println(dateAsUnix());
        System.out.println(day2Noon());
        System.out.println(listAllDays());

        //System.out.println(getSpecificDay("2021-04-07 09:00:00"));

    }

    HashMap hash = new HashMap();
    public String listAllDays(){
        for (int i = 0; i < basj.size(); i++){
            hash.put(i, basj.get(i));
        }
        System.out.println(hash.size());
        return hash.toString();
    }

    public String getSpecificDay(String day){
        //day = "2021-04-07 00:00:00";
        Object obj = hash.get("dt_txt=").equals(day);
        if (obj != null){
            return obj.toString();
        }
        return "no result";
    }

    public String getSomeday(){
        return "";
    }




    /**
     *om klokka er < 0600 tomorrow = today
     * @return
     */




    public String dateAsUnix(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = formatter.format(date);
        return today;
    }

    public String day2Noon(){
        return dateAsUnix()+" 12:00:00";
    }





    Map<String, Object > liste;
    public void fillListe(){
        liste = (Map<String, Object>) forecast.get("weather");
        //System.out.println(liste.toString());
    }


    List anotherList;
    public void iterator(){
        Iterator iterator = basj.iterator();
        while (iterator.hasNext()){
            Object s = iterator.next();
            anotherList.add(s.toString());
        }
    }



    public Map<String,Object> getAll(){
        for (Map.Entry<String,Object> m : forecast.entrySet()){
            Object e = m.getValue();
            //System.out.println(e.toString());
        }
        return forecast;
    }

    public List sorted(){
        return forecastArray.stream()
                .collect(Collectors.toList());
    }






    public static Map<String, Object> jsonToMap(String str){
        Map<String,Object> map = new Gson().fromJson(
                str,new TypeToken<HashMap<String,Object>>(){}.getType()
        );
        return map;
    }





    public int getTomorrowTemp() {
        return tomorrowTemp;
    }


}

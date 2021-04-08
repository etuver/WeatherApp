package no.ntnu.eventu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.parser.ParseException;

public class PrimaryController {

    public Label todayLabel1;
    public Label tomorrowWindLabel;
    public Label todayLabel;
    public Label tomorrowFeltTempLabel;
    public Label tomorrowTempLabel;
    public Label tomorrowDescriptionLabel;
    public ImageView twoDaysIcon;
    public Label twodaysDescriptionLabel;
    public Label twoDaysTempLabel;
    public Label twoDaysFeltTempLabel;
    public Label twoDaysWindLabel;
    public Label threeDaysWindLabel;
    public Label threeDaysFeltTempLabel;
    public Label threeDaysTempLabel;
    public Label threeDaysDescriptionLabel;
    public ImageView threeDaysIcon;
    public Label fourDaysWindLabel;
    public Label fourDaysFeltTempLabel;
    public Label fourDaysTempLabel;
    public Label fourDescriptionLabel112;
    public ImageView fourDaysIcon;
    public Label currentTime;
    public Label day2TimeLabel;
    public Label day3TimeLabel;
    public Label day4TimeLabel;
    public Label day5TimeLabel;
    CurrentWeather currentWeather;
    Forecast forecast;


    public PrimaryController() throws IOException {
    }
    // general
    @FXML
    public Button getWeatherBtn;
    @FXML
    public TextField cityTextField;
    @FXML
    public Label todayDescriptionLabel;
    @FXML
    public Label cityLabel;


    //  Today
    @FXML
    public ImageView todayIcon;
    @FXML
    public Label todayTempLabel;
    @FXML
    public Label todayWindLabel;
    @FXML
    public Label todayFeltTempLabel;

    //Tomorrow
    @FXML
    public ImageView tomorrowIcon;


    // In two days


    // In three days


    // In four days








    @FXML
    private void initialize(){
        cityLabel.setAlignment(Pos.CENTER);
    cityTextField.setText("7020");
    getWeatherBtn.setOnAction(actionEvent ->{
        getWeatherClicked();
        });
    }



    private void getWeatherClicked(){
        if(cityTextField.getText().equals("")){
            return;
        }else try {
            currentWeather = new CurrentWeather(cityTextField.getText());
            currentWeather.getCurrentWeather(cityTextField.getText());
            forecast = new Forecast(cityTextField.getText());
            showWeather();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showWeather() throws IOException, ParseException {
        currentWeather.getCurrentWeather(cityTextField.getText());
        getTodayWeather();
        forecast.getForeCast(cityTextField.getText());
        getForecast();
        forecast.setday1();
        getDay2();
        //System.out.println(forecast.day2WindDirection);
    }

    public void getTodayWeather() throws IOException, ParseException {
        todayDescriptionLabel.setText(currentWeather.description.toLowerCase());
        todayFeltTempLabel.setText("Føles som "+currentWeather.feltTemp + "℃");
        todayTempLabel.setText(currentWeather.temp +"℃");
        todayWindLabel.setText(currentWeather.windSpeed + "m/s fra "+ currentWeather.getWindDirection());
        todayIcon.setImage(new Image(getClass().getResourceAsStream("/images/" + currentWeather.icon  +".png")));
        cityLabel.setText("Viser været for "+currentWeather.city);
        currentTime.setText(currentWeather.time);
    }

    public void getForecast() throws IOException {
    }

    public void getDay2(){
        tomorrowFeltTempLabel.setText("Føles som: "+forecast.day2FeltTemp);
        tomorrowTempLabel.setText(forecast.day2Temp+"℃");
        tomorrowDescriptionLabel.setText(forecast.day2Description);
        tomorrowIcon.setImage(new Image(getClass().getResourceAsStream("/images/"+forecast.day2Icon+".png")));
        tomorrowWindLabel.setText(forecast.day2WindSpeed+"m/s fra ");
        day2TimeLabel.setText(forecast.day2Dt_txt);


    }









    }






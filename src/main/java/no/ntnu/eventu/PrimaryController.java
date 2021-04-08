package no.ntnu.eventu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController {


    WeatherStation weatherStation;
    private String locationZipCode;
    TomorrowForecast tomorrowForecast;

    public PrimaryController() throws IOException {
        //WeatherStation weatherStation;
        //String locationZipCode;


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



    // In two days


    // In three days


    // In four days








    @FXML
    private void initialize(){


    //descriptionLabel.setText("No weather");
    cityTextField.setText("7020");
    getWeatherBtn.setOnAction(actionEvent ->{
        getWeatherClicked();
        });
    }



    private void getWeatherClicked(){
        if(cityTextField.getText().equals("")){
            return;
        }else try {
            this.locationZipCode = cityTextField.getText();
            weatherStation = new WeatherStation(locationZipCode);
            tomorrowForecast = new TomorrowForecast(locationZipCode);
            showWeather();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showWeather() throws IOException {
        getTodayWeather();
        //getForecast();
    }

    public void getTodayWeather() throws IOException {
        weatherStation.getWeather();
        todayDescriptionLabel.setText(weatherStation.getDescription().toLowerCase());
        todayFeltTempLabel.setText("Føles som "+weatherStation.feltTemp + "℃");
        todayTempLabel.setText(weatherStation.getTempString());
        todayWindLabel.setText(weatherStation.getWindDescription());
        todayIcon.setImage(new Image(getClass().getResourceAsStream("/images/" + weatherStation.getIcon()  +".png")));
        cityLabel.setText("Viser været for "+weatherStation.getCity());

        System.out.println("test"+tomorrowForecast.getTomorrowTemp());
    }

    public void getForecast() throws IOException {
        tomorrowForecast.getForecast();
    }









    }






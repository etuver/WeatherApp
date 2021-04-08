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

    CurrentWeather currentWeather;


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
            showWeather();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showWeather() throws IOException, ParseException {
        currentWeather.getCurrentWeather(cityTextField.getText());
        getTodayWeather();
    }

    public void getTodayWeather() throws IOException, ParseException {
        todayDescriptionLabel.setText(currentWeather.description.toLowerCase());
        todayFeltTempLabel.setText("Føles som "+currentWeather.feltTemp + "℃");
        todayTempLabel.setText(currentWeather.temp +"℃");
        todayWindLabel.setText(currentWeather.windSpeed + "m/s fra "+ currentWeather.getWindDirection());
        todayIcon.setImage(new Image(getClass().getResourceAsStream("/images/" + currentWeather.icon  +".png")));
        cityLabel.setText("Viser været for "+currentWeather.city);
    }

    public void getForecast() throws IOException {
        //tomorrowForecast.getForecast();
    }









    }






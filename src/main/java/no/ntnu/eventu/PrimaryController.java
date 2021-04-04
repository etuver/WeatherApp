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

    public PrimaryController() throws IOException {
        //WeatherStation weatherStation;
        //String locationZipCode;


    }

    @FXML
    public Button getWeatherBtn;

    @FXML
    public TextField cityTextField;

    @FXML
    public Label descriptionNowLbl, windNowLbl, tempNowLbl;

    //@FXML
    //public Label windNowLbl;
    //@FXML
    //public Label tempNowLbl;
    @FXML
    public ImageView todayIcon;





    @FXML
    private void initialize(){


    //descriptionLabel.setText("No weather");
    cityTextField.setText("7020");
    getWeatherBtn.setOnAction(actionEvent ->{
        getWeatherClicked();
        });
    }



    private void getWeatherClicked(){

        //if (cityTextField.getText().isBlank() || cityTextField.getText().isEmpty() || cityTextField.getText().equals("")){
        if(cityTextField.getText().equals("")){
            return;
        }else try {
            this.locationZipCode = cityTextField.getText();
            weatherStation = new WeatherStation(locationZipCode);
            showWeather();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showWeather() throws FileNotFoundException {

        //todayIcon.setImage(img);

        //InputStream in = new FileInputStream(IconHandler.getWeatherIcon(weatherStation.getIcon()));
        //InputStream in = getClass().getResourceAsStream("src/main/resources/no/ntnu/eventu/images/02d.png");
        //InputStream stream = new FileInputStream(iconHandler.getWeatherIcon(weatherStation.getIcon())) ;
        //Image image = new Image(in);


        weatherStation.getWeather();
        descriptionNowLbl.setText(weatherStation.getDescription().toLowerCase());
        tempNowLbl.setText(weatherStation.getTempString());
        windNowLbl.setText(weatherStation.getWindDescription());
        //todayIcon.setImage(image);
        System.out.println(weatherStation.getIcon());
        todayIcon.setImage(new Image(getClass().getResourceAsStream("/images/" + weatherStation.getIcon()  +".png")));
        //this.getClass().getResourceAsStream("src/main/resources/no/ntnu/eventu/images/04n.png");
    }










    }






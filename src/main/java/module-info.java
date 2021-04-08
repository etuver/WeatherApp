module WeatherApp {
    requires javafx.graphics;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.controls;

    opens no.ntnu.eventu to javafx.fxml;
    exports no.ntnu.eventu;
}
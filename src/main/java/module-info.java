module no.ntnu.eventu {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;
    requires okhttp3;
    requires org.json;
    requires org.junit.jupiter.api;

    opens no.ntnu.eventu to javafx.fxml;
    exports no.ntnu.eventu;
}
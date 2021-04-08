package no.ntnu.eventu;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

public class Weather {
    long dt;
    Instant instant;
    ZonedDateTime zdt;
    List main;
    List wind;
    double temp;
    double felt;
    String description;
    String icon;
    int windDirection;
    double windSpeed;


    // Empty constructor
    public Weather(){
    }


    public Weather(long dt, Instant instant, ZonedDateTime zdt, List main, List wind, double temp, double felt, String description, String icon, int windDirection, double windSpeed) {
        this.dt = dt;
        this.instant = instant;
        this.zdt = zdt;
        this.main = main;
        this.wind = wind;
        this.temp = temp;
        this.felt = felt;
        this.description = description;
        this.icon = icon;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }


    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public ZonedDateTime getZdt() {
        return zdt;
    }

    public void setZdt(ZonedDateTime zdt) {
        this.zdt = zdt;
    }

    public List getMain() {
        return main;
    }

    public void setMain(List main) {
        this.main = main;
    }

    public List getWind() {
        return wind;
    }

    public void setWind(List wind) {
        this.wind = wind;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFelt() {
        return felt;
    }

    public void setFelt(double felt) {
        this.felt = felt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
















}

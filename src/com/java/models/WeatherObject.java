package com.java.models;

public class WeatherObject {

    private long dateTime;  // unix milli
    private double temp;  // Celsius
    private double feelsLike;  // Celsius
    private double pressure;  // mmHg
    private int humidity;  // %
    private double windSpeed;  // metre/sec
    private String windDir;  // degrees
    private String description;

    private double dayTemp;  // Celsius
    private double nightTemp;  // Celsius
    private double eveTemp;  // Celsius
    private double mornTemp;  // Celsius
    private double dayFeelsLike;  // Celsius
    private double nightFeelsLike;  // Celsius
    private double eveFeelsLike;  // Celsius
    private double mornFeelsLike;  // Celsius

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir.toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public double getEveTemp() {
        return eveTemp;
    }

    public void setEveTemp(double eveTemp) {
        this.eveTemp = eveTemp;
    }

    public double getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(double mornTemp) {
        this.mornTemp = mornTemp;
    }

    public double getDayFeelsLike() {
        return dayFeelsLike;
    }

    public void setDayFeelsLike(double dayFeelsLike) {
        this.dayFeelsLike = dayFeelsLike;
    }

    public double getNightFeelsLike() {
        return nightFeelsLike;
    }

    public void setNightFeelsLike(double nightFeelsLike) {
        this.nightFeelsLike = nightFeelsLike;
    }

    public double getEveFeelsLike() {
        return eveFeelsLike;
    }

    public void setEveFeelsLike(double eveFeelsLike) {
        this.eveFeelsLike = eveFeelsLike;
    }

    public double getMornFeelsLike() {
        return mornFeelsLike;
    }

    public void setMornFeelsLike(double mornFeelsLike) {
        this.mornFeelsLike = mornFeelsLike;
    }

    public static long secToMilli(long sec) {
        return sec * 1000;
    }

    public static double pHaToMmHg(double pHa) {
        return pHa * 0.750064;
    }

    public static String windDegToDir(int deg) {
        double range = 45 / 2.0;
        int normalizedDeg = deg % 360;
        if (360 - range <= normalizedDeg || 0 <= normalizedDeg && normalizedDeg <= range) {
            return "N";
        } else if (45 - range <= normalizedDeg && normalizedDeg <= 45 + range) {
            return "NE";
        } else if (90 - range <= normalizedDeg && normalizedDeg <= 90 + range) {
            return  "E";
        } else if (135 - range <= normalizedDeg && normalizedDeg <= 135 + range) {
            return "SE";
        } else if (180 - range <= normalizedDeg && normalizedDeg <= 180 + range) {
            return "S";
        } else if (225 - range <= normalizedDeg && normalizedDeg <= 225 + range) {
            return "SW";
        } else if (270 - range <= normalizedDeg && normalizedDeg <= 270 + range) {
            return "W";
        } else {
            return "NW";
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "dateTime=" + dateTime +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDir +
                ", description='" + description + '\'' +
                '}';
    }
}

package com.java.models;

public class Weather {

    private long dateTime;  // unix sec
    private double temp;  // Celsius
    private double feelsLike;  // Celsius
    private double pressure;  // pHa
    private int humidity;  // %
    private double windSpeed;  // metre/sec
    private int windDeg;  // degrees
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

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
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

    @Override
    public String toString() {
        return "Weather{" +
                "dateTime=" + dateTime +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDeg +
                ", description='" + description + '\'' +
                '}';
    }
}

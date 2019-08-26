package model;

public class Weather {
    private Place place;
    private Condition condition;
    private Temperature temperature;
    private int pressuer;
    private int humidity;
    private String date;


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public int getPressuer() {
        return pressuer;
    }

    public void setPressuer(int pressuer) {
        this.pressuer = pressuer;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

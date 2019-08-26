package viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import model.Condition;
import model.Place;
import model.Temperature;
import model.Weather;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<List<Weather>> weatherMutableLiveData;

    public WeatherViewModel(){
        weatherMutableLiveData = new MutableLiveData<>();
        dummy();
    }
    private void dummy(){
        Weather weather = new Weather();
        Place p = new Place();
        p.setCity("Maroco");
        weather.setPlace(p);
        Temperature t = new Temperature();
        t.setTemp(24.0);
        weather.setTemperature(t);
        Condition c = new Condition();
        c.setCondition("Good");
        weather.setCondition(c);
        ArrayList<Weather>list = new ArrayList<>();
        list.add(weather);


        weatherMutableLiveData.setValue(list);



    }

    public LiveData<List<Weather>> getMutableWeatherList(){
        return getMutableWeatherList();
    }
}

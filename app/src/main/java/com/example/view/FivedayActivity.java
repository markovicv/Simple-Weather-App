package com.example.view;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

import adapter.WeatherAdapter;
import model.Weather;
import repository.JSONweather;
import repository.WeatherService;
import utility.Utils;

public class FivedayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    String currentCity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiveday);
        currentCity = getIntent().getStringExtra("cityI");
        init();
        getWeather(currentCity);
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerViewId);
        weatherAdapter = new WeatherAdapter();
        recyclerView.setAdapter(weatherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getWeather(String city){

        FiveDayWeatherTask weatherTask = new FiveDayWeatherTask();
        weatherTask.execute(new String[]{city+"&units=metric"+"&APPID=bc5758cce2fe63403f0354ae645bb6c6"});

    }

    public class FiveDayWeatherTask extends AsyncTask<String,Void, List<Weather>>{

        @Override
        protected List<Weather> doInBackground(String... strings) {
            WeatherService ws = new WeatherService();
            String data = ws.getWeatherData(strings[0], Utils.FIVE_DAY_URL);
            ArrayList<Weather> weathers = JSONweather.fiveDayWeather(data);

            return weathers;

        }

        @Override
        protected void onPostExecute(List<Weather> weathers) {

            weatherAdapter.setWeatherList(weathers);
        }
    }
}

package com.example.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import model.Weather;
import repository.JSONweather;
import repository.WeatherService;
import utility.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView currentTempretureField;
    private TextView cityField;
    private TextView dateField;
    private TextView maindescriptionField;
    private EditText searchCityField;
    private Toolbar toolbar;
    private ImageView iconView;
    private FloatingActionButton findCityBtn;

    private Weather weather = new Weather();
    private String currentCity = "Paris,France";



    private Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        weatherDisplay(currentCity);
        initListeners();
        myDialog = new Dialog(this);



    }
    private void initWidgets(){
        currentTempretureField = findViewById(R.id.currenttemperatureId);
        dateField = findViewById(R.id.dayId);
        cityField = findViewById(R.id.locationCityId);
        maindescriptionField = findViewById(R.id.weather_condition);
        toolbar = findViewById(R.id.toolBarId);
        searchCityField = findViewById(R.id.searchCityId);
        iconView = findViewById(R.id.iconId);
        findCityBtn = findViewById(R.id.floatingbtn);
        setSupportActionBar(toolbar);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId==R.id.fiveDayForecast) {
            Intent intent = new Intent(MainActivity.this, FivedayActivity.class);
            intent.putExtra("cityI",currentCity);
            startActivity(intent);
        }
        else if(itemId==R.id.detailsId){
            myDialog.setContentView(R.layout.custom_popup);
            TextView sunriseTxtField = myDialog.findViewById(R.id.sunriseId);
            TextView sunsetTxtField = myDialog.findViewById(R.id.sunsetId);
            TextView descTxtField = myDialog.findViewById(R.id.descriptionId);
            TextView humidityTxtField = myDialog.findViewById(R.id.humidityId);
            TextView pressureTxtField = myDialog.findViewById(R.id.pressureId);
            TextView exitView = myDialog.findViewById(R.id.xId);

            sunriseTxtField.setText("sunrise: "+Utils.unixTimeToString(weather.getPlace().getSunrise()).substring(10,19));
            sunsetTxtField.setText("sunset: "+Utils.unixTimeToString(weather.getPlace().getSunset()).substring(10,19));
            descTxtField.setText("description: "+weather.getCondition().getDescription());
            humidityTxtField.setText("humidity: "+weather.getHumidity()+"%");
            pressureTxtField.setText("pressure: "+weather.getPressuer());

            exitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myDialog.dismiss();
                }
            });



            myDialog.show();
        }

        return true;
    }

    private void weatherDisplay(String city){
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city+"&units=metric"+"&APPID=bc5758cce2fe63403f0354ae645bb6c6"});


    }

    private void initListeners(){
        findCityBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.floatingbtn){
            currentCity = searchCityField.getText().toString();
            weatherDisplay(currentCity);
        }
    }

    public  class WeatherTask extends AsyncTask<String,Void, Weather>{

        @Override
        protected Weather doInBackground(String... strings) {
            WeatherService ws = new WeatherService();
            String data = ws.getWeatherData(strings[0], Utils.B_URL);
            weather = JSONweather.getWeather(data);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            currentTempretureField.setText(String.valueOf(Math.round(weather.getTemperature().getTemp())));
            cityField.setText(weather.getPlace().getCity()+","+weather.getPlace().getCountry());
            dateField.setText(Utils.unixTimeToString(weather.getPlace().getLastUpdate()).substring(0,10));
            iconView.setImageResource(Utils.getWeatherIcon(weather.getCondition().getId()));
            maindescriptionField.setText(weather.getCondition().getCondition());

        }
    }



}

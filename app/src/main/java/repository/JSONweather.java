package repository;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Condition;
import model.Place;
import model.Temperature;
import model.Weather;

public class JSONweather {



    public static Weather getWeather(String data){
        Weather weather = new Weather();

        try{
            JSONObject jObj = new JSONObject(data);
            getPlace(weather,data,jObj);
            getWeatherCondition(weather,data,jObj);
            getTemperature(weather,data,jObj);


        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

    public static ArrayList<Weather>fiveDayWeather(String data){
        ArrayList<Weather> weathers = new ArrayList<>();
        float minTempG = 10000;
        float maxTempG = -10000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datee = sdf.format(new Date());


        try{
            JSONObject json = new JSONObject(data);
            JSONObject cityObj = json.getJSONObject("city");
            Date date = sdf.parse(datee);


            JSONArray listArray = json.getJSONArray("list");
            String country = cityObj.getString("country");
            String city = cityObj.getString("name");

            for(int i=0;i<listArray.length();i++){
                JSONObject listItem = listArray.getJSONObject(i);
                JSONObject mainOnj = listItem.getJSONObject("main");


                double pressure = mainOnj.getDouble("pressure");
                int humidity = mainOnj.getInt("humidity");
                float minTemp = (float)mainOnj.getDouble("temp_min");
                float maxTemp = (float)mainOnj.getDouble("temp_max");
                double temp = mainOnj.getDouble("temp");

                if(minTemp<minTempG)
                    minTempG = minTemp;
                if(maxTemp>maxTempG)
                    maxTempG = maxTemp;

                JSONArray weatherArray = listItem.getJSONArray("weather");
                JSONObject weatherObj = weatherArray.getJSONObject(0);

                String condition = weatherObj.getString("main");
                int weatherId = weatherObj.getInt("id");
                String description = weatherObj.getString("description");
                String datecurrent = listItem.getString("dt_txt");
                Date nDate = sdf.parse(datecurrent.substring(0,10));

                if(nDate.after(date)){
                    date = nDate;
                    minTemp=10000;
                    maxTemp=-10000;
                }
                else
                    continue;


                Weather weather = new Weather();
                weather.setHumidity(humidity);
                weather.setPressuer((int)pressure);
                weather.setDate(datecurrent.substring(0,10));

                Temperature temperature = new Temperature();
                temperature.setMinTemp(minTempG);
                temperature.setMaxTemp(maxTempG);
                temperature.setTemp(temp);
                weather.setTemperature(temperature);

                Condition conditions = new Condition();
                conditions.setCondition(condition);
                conditions.setDescription(description);
                conditions.setId(weatherId);
                weather.setCondition(conditions);

                Place place = new Place();
                place.setCountry(country);
                place.setCity(city);


                weathers.add(weather);

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }


        return weathers;

    }





    private static void getPlace(Weather weather,String data,JSONObject mainObj) throws Exception{
        Place place = new Place();
        JSONObject coordinatesJson = mainObj.getJSONObject("coord");
        float lontitude = (float)coordinatesJson.getDouble("lon");
        float altitude = (float)coordinatesJson.getDouble("lat");
        JSONObject sysObjJson = mainObj.getJSONObject("sys");
        long sunrise = sysObjJson.getLong("sunrise");
        long sunset = sysObjJson.getLong("sunset");
        long lastUpdate= mainObj.getInt("dt");
        String country = sysObjJson.getString("country");
        String city = mainObj.getString("name");

        place.setLontitude(lontitude);
        place.setAltitude(altitude);
        place.setSunrise(sunrise);
        place.setSunset(sunset);
        place.setLastUpdate(lastUpdate);
        place.setCity(city);
        place.setCountry(country);

        weather.setPlace(place);

    }
    private static void getWeatherCondition(Weather weather,String data,JSONObject mainObj) throws  Exception{
        Condition condition = new Condition();
        JSONArray jArray = mainObj.getJSONArray("weather");
        JSONObject jWeather = jArray.getJSONObject(0);
        condition.setId(jWeather.getInt("id"));
        condition.setDescription(jWeather.getString("description"));
        condition.setCondition(jWeather.getString("main"));
        weather.setCondition(condition);
    }
    private static void getTemperature(Weather weather,String data,JSONObject mainObj) throws  Exception{
        Temperature temperature = new Temperature();

        JSONObject tempObjectJson = mainObj.getJSONObject("main");
        weather.setHumidity(tempObjectJson.getInt("humidity"));
        weather.setPressuer(tempObjectJson.getInt("pressure"));
        temperature.setTemp(tempObjectJson.getDouble("temp"));
        temperature.setMaxTemp((float)tempObjectJson.getDouble("temp_max"));
        temperature.setMinTemp((float)tempObjectJson.getDouble("temp_min"));
        weather.setTemperature(temperature);
    }
}

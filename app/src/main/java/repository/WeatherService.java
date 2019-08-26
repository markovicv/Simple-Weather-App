package repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import utility.Utils;

public class WeatherService {

    HttpURLConnection connection;

    public String getWeatherData(String place,String url){
        InputStream inputStream;
        StringBuffer stringBuffer;

        try {
            connection = (HttpURLConnection) (new URL(url + place)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line+"\r\n");
            }
            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}

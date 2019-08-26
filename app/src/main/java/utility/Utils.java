package utility;

import com.example.weatherapp.R;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static final String  B_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String FIVE_DAY_URL = "https://api.openweathermap.org/data/2.5/forecast?q=";
    public static final String[] WEEK = {"Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday"};

    public static int getWeatherIcon(final int weatherIdIcon){
        switch (weatherIdIcon){
            case 800:
                return R.drawable.ic_sunny;
            case 200:
            case 201:
            case 202:
            case 210:
            case 211:
            case 212:
            case 221:
            case 230:
            case 231:
            case 232:
                return R.drawable.ic_thunderstorm;
            case 500:
            case 501:
            case 502:
            case 503:
            case 504:
            case 511:
            case 520:
            case 521:
            case 522:
            case 531:
                return R.drawable.ic_rain;
            case 600:
            case 601:
            case 602:
            case 611:
            case 612:
            case 613:
            case 615:
            case 616:
            case 620:
            case 621:
            case 622:
                return R.drawable.ic_snow;
            case 801:
            case 802:
            case 803:
            case 804:
                return R.drawable.ic_cloud;
            case 701:
            case 711:
            case 721:
            case 731:
            case 741:
            case 751:
            case 761:
                return R.drawable.ic_foggy;

            default:
                return R.drawable.ic_refresh;
        }

    }
    public static String unixTimeToString(long unixTime){
        Date date = new Date(unixTime*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formated = sdf.format(date);

        return formated;
    }

}

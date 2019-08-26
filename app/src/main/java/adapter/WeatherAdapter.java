package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.Weather;
import utility.Utils;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    List<Weather> weatherList = new ArrayList<>();

    public WeatherAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.mintemperatureField.setText("Min temp: "+String.valueOf(Math.round(weatherList.get(position).getTemperature().getMinTemp())));
       holder.maxtemperatureField.setText("Max temp: "+String.valueOf(Math.round(weatherList.get(position).getTemperature().getMaxTemp())));
       holder.icon.setImageResource(Utils.getWeatherIcon(weatherList.get(position).getCondition().getId()));
       holder.dateField.setText(weatherList.get(position).getDate());
       holder.weatherDescription.setText(weatherList.get(position).getCondition().getCondition());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView weatherDescription;
        private TextView dateField;
        private ImageView icon;
        private TextView mintemperatureField;
        private TextView maxtemperatureField;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherDescription =itemView.findViewById(R.id.descriptionRid);
            dateField = itemView.findViewById(R.id.dateRid);
            icon = itemView.findViewById(R.id.imgRid);
            mintemperatureField = itemView.findViewById(R.id.minTempRId);
            maxtemperatureField = itemView.findViewById(R.id.maxTempRId);
        }
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }
    public void addToList(Weather weather){
        this.weatherList.add(weather);
        notifyDataSetChanged();
    }

}

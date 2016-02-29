import java.util.*;
import java.text.*;

public class WeatherConverter
{
    public String date;
    public String time;
    public String temperature;
    public String humidity;
    public String barometer;
    public String windspeed;
    public String winddirection;
    public String windgust;
    public String windchill;
    public String heatindex;
    public String uvindex;
    public String rainfall;

    private Boolean _ready;

    public WeatherConverter()
    {
        _ready = true;
    }

    public WeatherDataContainer Convert()
    {
        WeatherDataContainer weatherPoint = new WeatherDataContainer();

        if(!_ready)
        {
            return null;
        }

        DateFormat Format = new SimpleDateFormat("mm/dd/yy hh:mmaa", Locale.ENGLISH);

        try
        {
            time = time.trim() + "M";
            System.out.println(date + time);
            weatherPoint.date = Format.parse(date + time);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        weatherPoint.temp = Double.parseDouble(temperature);
        weatherPoint.humidity = Double.parseDouble(humidity);
        weatherPoint.pressure = Double.parseDouble(barometer);
        weatherPoint.windSpeed = Double.parseDouble(windspeed);
        weatherPoint.windDirection = winddirection;
        weatherPoint.windGust = Double.parseDouble(windgust);
        weatherPoint.windChill = Double.parseDouble(windchill);
        weatherPoint.heatIndex = Double.parseDouble(heatindex);
        weatherPoint.uvIndex = Double.parseDouble(uvindex);
        weatherPoint.rainFall = Double.parseDouble(rainfall);

        return weatherPoint;
    }
}

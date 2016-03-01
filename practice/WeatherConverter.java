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
            weatherPoint.date = Format.parse(date + time);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        if(temperature != null)
        {
            weatherPoint.temp = Double.parseDouble(temperature);
        }

        if(humidity != null)
        {
            weatherPoint.humidity = Double.parseDouble(humidity);
        }

        if(barometer != null)
        {
            weatherPoint.pressure = Double.parseDouble(barometer);
        }

        if(windspeed != null)
        {
            weatherPoint.windSpeed = Double.parseDouble(windspeed);
        }

        if(winddirection != null)
        {
            weatherPoint.windDirection = winddirection;
        }

        if(windgust != null)
        {
            weatherPoint.windGust = Double.parseDouble(windgust);
        }

        if(windchill != null)
        {
            weatherPoint.windChill = Double.parseDouble(windchill);
        }

        if(heatindex != null)
        {
            weatherPoint.heatIndex = Double.parseDouble(heatindex);
        }

        if(uvindex != null)
        {
            weatherPoint.uvIndex = Double.parseDouble(uvindex);
        }

        if(rainfall != null)
        {
            weatherPoint.rainFall = Double.parseDouble(rainfall);
        }


        return weatherPoint;
    }
}

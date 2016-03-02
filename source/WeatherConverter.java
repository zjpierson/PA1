/*
 * WeatherConverter.java
 *
 * WeatherConverter is used because there are two seperate tags (date and time)
 * that are both used for createing a Date object.  the other fields will also 
 * be parse from strings into doubles.  The main Convert function does all of this
 * formating into the corrent WeatherDataContainer.
 *
 * @author Zachary Pierson
 * @version Spring 2016 - GUI
 *
 */

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

   /*
    * Convert: Converts this WeatherConverter object and returns it as a
    * WeatherDataContainer object.
    */
    public WeatherDataContainer Convert()
    {
        WeatherDataContainer weatherPoint = new WeatherDataContainer();

        if(!_ready)
        {
            return null;
        }

        //set the format for parsing the date
        DateFormat Format = new SimpleDateFormat("MM/dd/yy hh:mmaa", Locale.ENGLISH);

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
            weatherPoint.temperature = Double.parseDouble(temperature);
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

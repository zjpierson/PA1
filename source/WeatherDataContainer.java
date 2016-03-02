/*
 * WeatherDataContainer.java
 *
 * The weather container holds the values read in from the xml file. It also
 * has a toString method that makes the object printable for debugging purposes.
 *
 * @author Zachary Pierson
 * @version CSC468 GUI Programming, Spring 2016
 *
 * Modifications:
 */

import java.util.*;

/*
 * WeatherDataContainer class
 */
public class WeatherDataContainer 
{
    public Date date; // date object
    public double temperature; // temperature object
    public double humidity; // humidity object
    public double pressure; // pressure object
    public double windSpeed; // wind speed object
    public String windDirection; // wind direction object
    public double windGust; // wind gust object
    public double windChill; // wind chill object
    public double heatIndex; // heat index object
    public double uvIndex; // uv index object
    public double rainFall; // rainfall object

    /*
     * WeatherDataContainer Constructor: Does nothing
     */
    public WeatherDataContainer()
    {
    }

    /*
     * WeatherDataContainer Constructor: Does nothing
     */
    public String toString()
    {
        return "Date:            " + date + "\n" + 
                "Temperature:    " + temperature + "\n" + 
                "Humidity:       " + humidity + "\n" + 
                "Pressure:       " + pressure + "\n" + 
                "Wind Speed:     " + windSpeed + "\n" + 
                "Wind Direction: " + windDirection + "\n" + 
                "Wind Gust:      " + windGust + "\n" + 
                "Wind Chill:     " + windChill + "\n" + 
                "Heat Index:     " + heatIndex + "\n" + 
                "UV Index:       " + uvIndex + "\n" + 
                "Rain Fall:      " + rainFall + "\n";
    }
}

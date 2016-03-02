/*
 * WeatherToolTip.java
 *
 * The weather tool tip is what the charts use to give a tooltip at each data point.
 *
 * @author Zachary Pierson
 * @version CSC468 GUI Programming, Spring 2016
 */

import java.text.*;
import org.jfree.chart.labels.*;
import org.jfree.data.xy.*;

/*
 * WeatherToolTip class that implements XYToolTipGenerator
 */
public class WeatherToolTip implements XYToolTipGenerator
{
    WeatherList data; // WeatherList object that holds all the displayed data

    /*
     * WeatherToolTip Constructor: Takes in a WeatherList of data and sets it
     * to the local WeatherList.
     */
    public WeatherToolTip(WeatherList weatherData)
    {
        data = weatherData;
    }

    /*
     * generateToolTip: Takes in an XYDataset and two ints and returns a
     * formatted string with the data for that point.
     */
    public String generateToolTip(XYDataset dataset, int series, int item)
    {
        WeatherDataContainer dataPoint = data.get(item); // Get the data
        return stringFormatter(dataPoint);
    }

    /*
     * stringFormatter: Takes in a WeatherDataContainer and returns a string.
     */
    private String stringFormatter(WeatherDataContainer dataPoint)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        String toolTip = "<html>" +
                    "Date:           " + dateFormat.format(dataPoint.date) + "<br/>" +
                    "Time:           " + timeFormat.format(dataPoint.date) + "<br/>" +
                    "Temperature:    " + dataPoint.temperature + "<br/>" +
                    "Humidity:       " + dataPoint.humidity + "<br/>" +
                    "Pressure:       " + dataPoint.pressure + "<br/>" +
                    "Wind Speed:     " + dataPoint.windSpeed + "<br/>" +
                    "Wind Direction: " + dataPoint.windDirection + "<br/>" +
                    "Wind Gust:      " + dataPoint.windGust + "<br/>" +
                    "Wind Chill:     " + dataPoint.windChill + "<br/>" +
                    "Heat Index:     " + dataPoint.heatIndex + "<br/>" +
                    "UV Index:       " + dataPoint.uvIndex + "<br/>" +
                    "Rain Fall:      " + dataPoint.rainFall + "<br/>" +
                    "</html>";

        return toolTip;
    }
}

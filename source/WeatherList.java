/*
 * WeatherList.java
 *
 * The weather list ...
 *
 * @author Zachary Pierson
 * @version CSC468 GUI Programming, Spring 2016
 *
 * Modifications:
 */

import java.util.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;
import org.jfree.data.time.*;
import org.jfree.data.general.SeriesException; 

/*
 * WeatherList class that extends an ArrayList of WeatherDataContainer objects
 */
public class WeatherList extends ArrayList<WeatherDataContainer>
{
    /*
     * WeatherList Constructor: Calls super with no arguments
     */
    public WeatherList()
    {
        super();
    }

    /*
     * WeatherList Constructor: Calls super with an ArrayList of
     * WeatherDataContainer objects as an argument.
     */
    public WeatherList(ArrayList<WeatherDataContainer> dataList)
    {
        super(dataList);
    }

    /*
     * getTemperatureDataset: Returns a TimeSeriesCollection for temperatures.
     */
    public TimeSeriesCollection getTemperatureDataset()
    {
        TimeSeries temperature = new TimeSeries("Temperature");

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherTemp = super.get(i);
            try 
            {
                temperature.add(new Minute(weatherTemp.date), weatherTemp.temperature);
            }
            catch ( SeriesException e ) 
            {
               System.err.println("Error adding " + weatherTemp.date + " to series");
            }

        }

        return new TimeSeriesCollection(temperature);
    }

    /*
     * getPrecipitationDataset: Returns a TimeSeriesCollection for precipitation.
     */
    public TimeSeriesCollection getPrecipitationDataset()
    {
        TimeSeries precip = new TimeSeries("Precipitation");

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherPrecip = super.get(i);
            try 
            {
                precip.add(new Minute(weatherPrecip.date), weatherPrecip.rainFall);
            }
            catch ( SeriesException e ) 
            {
               System.err.println("Error adding " + weatherPrecip.date + " to series");
            }

        }

        return new TimeSeriesCollection(precip);
    }

    /*
     * getUVIndexDataset: Returns a TimeSeriesCollection for uv index.
     */
    public TimeSeriesCollection getUVIndexDataset()
    {
        TimeSeries uvIndex = new TimeSeries("UVIndex");

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherUV = super.get(i);
            try 
            {
                uvIndex.add(new Minute(weatherUV.date), weatherUV.uvIndex);
            }
            catch ( SeriesException e ) 
            {
               System.err.println("Error adding " + weatherUV.date + " to series");
            }

        }

        return new TimeSeriesCollection(uvIndex);
    }

    /*
     * getPressureDataset: Returns a TimeSeriesCollection for pressure.
     */
    public TimeSeriesCollection getPressureDataset()
    {
        TimeSeries pressure = new TimeSeries("Pressure");

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherPressure = super.get(i);
            try 
            {
                pressure.add(new Minute(weatherPressure.date), weatherPressure.pressure);
            }
            catch ( SeriesException e ) 
            {
               System.err.println("Error adding " + weatherPressure.date + " to series");
            }

        }

        return new TimeSeriesCollection(pressure);
    }

    /*
     * getHumidityDataset: Returns a TimeSeriesCollection for humidity.
     */
    public TimeSeriesCollection getHumidityDataset()
    {
        TimeSeries humidity = new TimeSeries("Humidity");

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherHumidity = super.get(i);
            try 
            {
                humidity.add(new Minute(weatherHumidity.date), weatherHumidity.humidity);
            }
            catch ( SeriesException e ) 
            {
               System.err.println("Error adding " + weatherHumidity.date + " to series");
            }

        }

        return new TimeSeriesCollection(humidity);
    }

    /*
     * getWindSpeedDataset: Returns a TimeSeriesCollection for wind speed.
     */
    public TimeSeriesCollection getWindSpeedDataset()
    {
        TimeSeries windSpeed = new TimeSeries("WindSpeed");

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherWindSpeed = super.get(i);
            try 
            {
                windSpeed.add(new Minute(weatherWindSpeed.date), weatherWindSpeed.windSpeed);
            }
            catch ( SeriesException e ) 
            {
               System.err.println("Error adding " + weatherWindSpeed.date + " to series");
            }

        }

        return new TimeSeriesCollection(windSpeed);
    }
}

import java.util.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;
import org.jfree.data.time.*;
import org.jfree.data.general.SeriesException; 

public class WeatherList extends ArrayList<WeatherDataContainer>
{
    public WeatherList()
    {
        super();
    }
    public WeatherList(ArrayList<WeatherDataContainer> dataList)
    {
        super(dataList);
    }


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

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


    public TimeSeriesCollection getTemperatureData()
    {
        TimeSeries temperature = new TimeSeries("Temperature");

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherTemp = super.get(i);
            try 
            {
                temperature.add(new Minute(weatherTemp.date), weatherTemp.temp);
            }
            catch ( SeriesException e ) 
            {
               System.err.println("Error adding " + weatherTemp.date + " to series");
            }

        }

        return new TimeSeriesCollection(temperature);

    }
}

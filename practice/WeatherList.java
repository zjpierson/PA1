import java.util.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;
import org.jfree.data.time.*;

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
        TimeSeries temperature = new TimeSeries("temperature");

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherTemp = super.get(i);
            temperature.add(new Day(weatherTemp.date), weatherTemp.temp);
        }

        return new TimeSeriesCollection(temperature);

    }
}

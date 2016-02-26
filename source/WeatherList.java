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
        TimeSeries Temperature = new TimeSeries("Temperature", Date.class);
//        XYSeries Temperature = new XYSeries("Temperature");
        TimeSeriesCollection dataset = new TimeSeriesCollection(Temperature);
//        XYSeriesCollection dataset = new XYSeriesCollection(Temperature);

        for(int i = 0; i < super.size(); i++)
        {
            WeatherDataContainer weatherTemp = super.get(i);
            Temperature.add(weatherTemp.date, weatherTemp.temp);
        }

        return dataset;

    }
}

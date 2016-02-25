import java.util.*;
import org.jfree.data.category.DefaultCategoryDataset;

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


    public DefaultCategoryDataset getTemperatureData()
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        WeatherDataContainer weatherTemp;

        for(int i = 0; i < super.size(); i++)
        {
            weatherTemp = super.get(i);
            dataset.addValue(weatherTemp.temp, "Temperature", Integer.toString(i));
        }

        return dataset;
    }
}

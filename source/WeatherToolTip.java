import java.text.*;

import org.jfree.chart.labels.*;
import org.jfree.data.xy.*;

public class WeatherToolTip implements XYToolTipGenerator
{
    WeatherList data;

    public WeatherToolTip(WeatherList weatherData)
    {
        data = weatherData;
    }

    public String generateToolTip(XYDataset dataset, int series, int item)
    {
        WeatherDataContainer dataPoint = data.get(item);
        return stringFormatter(dataPoint);
    }

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

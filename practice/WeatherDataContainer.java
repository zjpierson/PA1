import java.util.Date;

public class WeatherDataContainer 
{
    public Date date;
    public double temp;
    public double humidity;
    public double pressure;
    public double windSpeed;
    public String windDirection;
    public double windGust;
    public double windChill;
    public double heatIndex;
    public double uvIndex;
    public double rainFall;

    public WeatherDataContainer()
    {
        System.out.println("Called WeatherDataContainer Constructor!");
    }
}

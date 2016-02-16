/*
                    ***** WeatherDataContainer.java *****

Author: Zachary Pierson
Class: CSC468 GUI Programming, Spring 2016

Modifications:
*/

import java.util.*;

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

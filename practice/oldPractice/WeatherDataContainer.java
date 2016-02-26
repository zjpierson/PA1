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
    }

    public String toString()
    {
        return "Date:            " + date + "\n" + 
                "Temperature:    " + temp + "\n" + 
                "Humidity:       " + humidity + "\n" + 
                "Pressure:       " + pressure + "\n" + 
                "Wind Speed:     " + windSpeed + "\n" + 
                "Wind Direction: " + windDirection + "\n" + 
                "Wind Gust:      " + windGust + "\n" + 
                "Wind Chill:     " + windChill + "\n" + 
                "Heat Index:     " + heatIndex + "\n" + 
                "UV Index:       " + uvIndex + "\n" + 
                "Rain Fall:      " + rainFall + "\n";
    }
}














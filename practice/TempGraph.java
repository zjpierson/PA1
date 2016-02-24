/*
                    ***** TempGraph.java *****

Author: Zachary Pierson
Class: CSC468 GUI Programming, Spring 2016

Modifications:
*/

import java.util.*;

public class TempGraph
{
    public TempGraph()
    {
        Run();
    }

    public void Run()
    {
        ArrayList<WeatherDataContainer> weatherData = getWeatherData();

        for(int i = 0; i < weatherData.size(); i++)
        {
            System.out.println(weatherData.get(i));
        }
    }

    public static void main( String [] args )
    {
        new TempGraph();
    }

    private ArrayList<WeatherDataContainer> getWeatherData()
    {
        ArrayList<WeatherDataContainer> data = new ArrayList<WeatherDataContainer>();

        WeatherDataContainer data1 = new WeatherDataContainer();
        WeatherDataContainer data2 = new WeatherDataContainer();
        WeatherDataContainer data3 = new WeatherDataContainer();
        
        data1.date = new Date(99999999);
        data1.temp = 73.4;
        data1.humidity = 12.334; data1.pressure = 534.0;
        data1.windSpeed = 12.03;
        data1.windDirection = "SSE";
        data1.windGust = 21.23;
        data1.windChill = 3.16;
        data1.heatIndex = 56.13;
        data1.uvIndex = 79.654;
        data1.rainFall = 35.16;
        
        data2.date = new Date(125645);
        data2.temp = 73.4;
        data2.humidity = 12.334;
        data2.pressure = 534.0;
        data2.windSpeed = 12.03;
        data2.windDirection = "SSE";
        data2.windGust = 21.23;
        data2.windChill = 3.16;
        data2.heatIndex = 56.13;
        data2.uvIndex = 79.654;
        data2.rainFall = 35.16;

        data3.date = new Date(1238998985);
        data3.temp = 73.4;
        data3.humidity = 12.334;
        data3.pressure = 534.0;
        data3.windSpeed = 12.03;
        data3.windDirection = "SSE";
        data3.windGust = 21.23;
        data3.windChill = 3.16;
        data3.heatIndex = 56.13;
        data3.uvIndex = 79.654;
        data3.rainFall = 35.16;

        data.add(data1);
        data.add(data2);
        data.add(data3);

        return data;
    }
}

/*
                    ***** TempGraph.java *****

Author: Zachary Pierson
Class: CSC468 GUI Programming, Spring 2016

Modifications:
*/

import java.util.*;
import java.util.ArrayList;
import java.text.*;

public class TempGraph
{
    public TempGraph()
    {
        Run();
    }

    public void Run()
    {
        ParseWeatherData dataReader = new ParseWeatherData();
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 0, 23);

        if(!dataReader.GetDay(cal.getTime()))
        {
            System.out.println("GetDay failed");
            return;
        }

//        WeatherList data = new WeatherList(dataReader.WeatherData);
//
//        if( data == null)
//        {
//            System.out.println("data is null!");
//            return;
//        }


        for(int i = 0; i < dataReader.WeatherData.size(); i++)
        {
            System.out.println(dataReader.WeatherData.get(i));
        }
    }

    public static void main( String [] args )
    {
        new TempGraph();
    }

}

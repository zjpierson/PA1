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
        cal.set(2013, 1, 20);

        if(!dataReader.GetYear(cal.getTime()))
        {
            System.out.println("GetDay failed");
            return;
        }




//        for(int i = 0; i < dataReader.WeatherData.size(); i += 10)
//        {
//            System.out.println(dataReader.WeatherData.get(i).date);
//        }

        System.out.println("Size after call: " + dataReader.WeatherData.size());
    }

    public static void main( String [] args )
    {
        new TempGraph();
    }

}

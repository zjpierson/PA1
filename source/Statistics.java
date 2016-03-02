/******************************************************************************
*    @title       Statistics Class
*
*    @author      Samantha Kranstz
*   
*    @class       CSC 468 Graphical User Interface
*
*    @professor   Dr. J. Weiss
*
*    @description This is our Statistics Class for our first program. It is 
*                 designed to calculate various statistics for a list of 
*                 data points. The statistics included average temperature,
*                 the high temperature and the low temperature, prevailing
*                 wind direction, and several others.
*
******************************************************************************/

import java.util.*;

public class Statistics 
{
/******************************************************************************
*    @title       TempAvg
*
*    @author      Samantha Kranstz
*   
*    @description This is a method to find the average temperature of a list
*                 of weather data points.
*
******************************************************************************/
    public double TempAvg( WeatherList WeatherData ) 
    { 
      double count = 0;  // Count the number of data points read in
      double mean = 0;   // Average variable
      double sum = 0;    // Total of all the temperatures added together
      Double temp;   // temporary variable


      Iterator<WeatherDataContainer> iterator = WeatherData.iterator();
    
      while( iterator.hasNext() ) 
      {
        //
        temp = (Double) iterator.next().temperature;

        // 
        if( temp != null )
        {
            // add all the temperatures together
            sum += temp;
            // count the number of data points
            count++;
        }
      }
     
      // Find the average by dividing sum by number of datapoints
      mean = sum / count;
    
      return mean;
      
    }

/******************************************************************************
*    @title       HighLow
*
*    @author      Samantha Kranstz
*   
*    @description This method searches for the maximum and minimum temperature
*                 data points. When the high or low are located, it also stores
*                 the date and time that this max or min occured.
*
******************************************************************************/
    public ArrayList<OccurenceContainer> HighLow( WeatherList WeatherData  ) 
    {
      OccurenceContainer high = new OccurenceContainer(); // High variable
      OccurenceContainer low = new OccurenceContainer();  // Low variable

      // Create a list to store the high and low to be returned
      ArrayList <OccurenceContainer> high_low = new ArrayList<>();

      // Variable to iterate through the list passed through
      Iterator<WeatherDataContainer> iterator = WeatherData.iterator();

      WeatherDataContainer temp = new WeatherDataContainer();
      temp = iterator.next();

      // Initialize high and low to the first point in the set 
      if( ((Double)temp.temperature) !=  null)
      {
        high.dataPoint = temp.temperature;
        high.date = temp.date;
        low.dataPoint = temp.temperature;
        low.date = temp.date;
      }

      while( iterator.hasNext() ) 
      {
        temp = iterator.next();

        if( (Double) temp.temperature != null )
        {
            // Compare the next tempature data point to the current high temp
            if( temp.temperature >= high.dataPoint ) 
            {
              // The data point is higher, set it as the new high
              high.dataPoint = temp.temperature;
              high.date = temp.date;
            }

            // Compare the next temperature data point to the current low temp
            if( temp.temperature <= low.dataPoint ) 
            {
              // The data point is lower, set it as the new low
              low.dataPoint = temp.temperature;
              low.date = temp.date;
            }
         }
        
      }  

        // Add the high and low to the list to be returned
        high_low.add(high);
        high_low.add(low);
  
        return( high_low );
           
    }

/******************************************************************************
*    @title       WindSpeedMean
*
*    @author      Samantha Kranstz
*   
*    @description This method takes in a list of weather data points. It then
*                 calculates the average wind speed. 
*
******************************************************************************/
    public double WindSpeedMean( WeatherList WeatherData ) 
    {
      double count = 0;
      double mean = 0;
      double sum = 0;
      Double temp;

      Iterator<WeatherDataContainer> iterator = WeatherData.iterator();

      while( iterator.hasNext() ) 
      {
        temp = (Double) iterator.next().windSpeed;

        if( temp != null )
        {
          sum += temp;
          count++;
        }
      }

      mean = sum / count;
  
      return mean;

    }

/******************************************************************************
*    @title       MaxWindGust
*
*    @author      Samantha Kranstz
*   
*    @description This method finds the biggest wind gust in the data set.
*
******************************************************************************/
    public OccurenceContainer MaxWindGust( WeatherList WeatherData ) 
    {
      OccurenceContainer maxGust = new OccurenceContainer();

      Iterator<WeatherDataContainer> iterator = WeatherData.iterator();
 
      WeatherDataContainer temp = new WeatherDataContainer();

      maxGust.dataPoint = 0.0;

      while( iterator.hasNext() ) 
      {
        temp = iterator.next();

        if( (Double) temp.windGust != null )
        {
          if( temp.windGust > maxGust.dataPoint ) 
          {
            maxGust.dataPoint = temp.windGust;
            maxGust.date = temp.date;
          }
        }
      }

      return( maxGust );

     }

/******************************************************************************
*    @title       PrevailWindDirect
*
*    @author      Samantha Kranstz
*   
*    @description This method figures out which wind direction of 16 directions
*                 was the dominate wind direction for that weather data set.
*
******************************************************************************/
     public String PrevailWindDirect( WeatherList WeatherData )
     {
        String directions[] = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE",
                       "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW" };

        int counters[] = new int[16];
        for( int j = 0; j < counters.length; j++ )
          counters[j] = 0;

        int i = 0;
        int prevail = 0;
        String prevailWind = "";
        String temp = "";

        Iterator<WeatherDataContainer> iterator = WeatherData.iterator();

        while( iterator.hasNext() ) 
        {
          temp = iterator.next().windDirection;
          
          if( temp != null )
          {
              for( String d : directions )
              {
                if( d == iterator.next().windDirection )
                {
                  counters[i] += 1;
                }
                
                i++;
              }
          }

          i = 0;
        }

        for( int j = 0; j < counters.length; j++ )
        {
          if ( prevail < counters[j])
          {
            prevail = counters[j];
            prevailWind = directions[j];
          }
        }

        return (prevailWind);
     }

/******************************************************************************
*    @title       Rainfall
*
*    @author      Samantha Kranstz
*   
*    @description This method calculates the amount of rainfall of the list 
*                 given.
*
******************************************************************************/
     public double Rainfall( WeatherList WeatherData )
     {
       double total = 0;
       Double temp;

       Iterator<WeatherDataContainer> iterator = WeatherData.iterator();

       while( iterator.hasNext() ) 
       {
         temp = (Double) iterator.next().rainFall;

         if( temp != null )
           total += iterator.next().rainFall;
       }
  
       return total;
     }

}








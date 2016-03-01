/*

    **** ParseWeatherData.java - read and dump contents of an XML file ****

Illustrates use of JDOM to parse an XML file.
This version will dive into XML tree structure.

Usage:
javac -cp .:jdom.jar ElementLister.java         (use ; instead of : on Windoze)
java  -cp .:jdom.jar ElementLister file.xml     (use ; instead of : on Windoze)

Based on Java example in Processing XML with Java (Elliotte Harold).
For more info, see e.g. https://docs.oracle.com/javase/tutorial/jaxp/dom/readingXML.html

JMW 160205
*/

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.text.*;

public class ParseWeatherData
{
    public WeatherList WeatherData;
    private ArrayList<String> _DataFiles;
    private String _DataPath;

    //Constructor
    public ParseWeatherData(String dataDirectory)
    {
        WeatherData = new WeatherList();
        _DataPath = dataDirectory;
        _DataFiles = new ArrayList<String>();

        File[] files = new File(dataDirectory).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 

        for (File file : files)
        {
            if (file.isFile() && file.getName().contains(".xml")) 
            {
                _DataFiles.add(file.getName());
            }
        }
    }

    public ParseWeatherData()
    {
        this("./data/");
    }

    public boolean GetDay(Date day)
    {
        if(day == null)
        {
            return false;
        }

        Calendar startMonth = Calendar.getInstance();
        startMonth.setTime(day);
        startMonth.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endMonth = Calendar.getInstance();
        endMonth.setTime(day);
        endMonth.set(Calendar.DAY_OF_MONTH, 28);

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(day);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(day);
        endDate.set(Calendar.HOUR_OF_DAY, 23);
        endDate.set(Calendar.MINUTE, 59);
        endDate.set(Calendar.SECOND, 59);

        //figure out which files to use
        ArrayList<String> files = FindFiles(startMonth.getTime(), endMonth.getTime());

        //Parse files for the day and updates the WeatherData
        ParseFile(files, startDate.getTime(), endDate.getTime());

        return true;
    }

    public boolean GetWeek(Date week)
    {
        if(week == null)
        {
            return false;
        }

        Calendar startMonth = Calendar.getInstance();
        startMonth.setTime(week);
        startMonth.set(Calendar.DAY_OF_MONTH, 1);

        Calendar endMonth = Calendar.getInstance();
        endMonth.setTime(week);
        endMonth.set(Calendar.DAY_OF_MONTH, 28);

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(week);
        startDate.set(Calendar.DAY_OF_WEEK, 0);
        startDate.set(Calendar.HOUR_OF_DAY, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(week);
        endDate.set(Calendar.DAY_OF_WEEK, 6);
        endDate.set(Calendar.HOUR_OF_DAY, 23);

        //figure out which files to use
        ArrayList<String> files = FindFiles(startMonth.getTime(), endMonth.getTime());

        //Parse files for the day and updates the WeatherData
        ParseFile(files, startDate.getTime(), endDate.getTime());

        return true;
    }

    public boolean GetMonth(Date month)
    {
        if(month == null)
        {
            return false;
        }

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(month);
        startDate.set(Calendar.MONTH, 0);
        startDate.set(Calendar.DAY_OF_MONTH, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(month);
        endDate.set(Calendar.MONTH, 11);
        endDate.set(Calendar.DAY_OF_MONTH, 28);

        //figure out which files to use
        ArrayList<String> files = FindFiles(startDate.getTime(), endDate.getTime());

        //Parse files for the day and updates the WeatherData
        ParseFile(files, startDate.getTime(), endDate.getTime());

        return true;
    }

    public boolean GetYear(Date year)
    {
        if(year == null)
        {
            return false;
        }

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(year);
        startDate.set(Calendar.MONTH, 0);
        startDate.set(Calendar.DAY_OF_MONTH, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(year);
        endDate.set(Calendar.MONTH, 11);
        endDate.set(Calendar.DAY_OF_MONTH, 31);

        //figure out which files to use
        ArrayList<String> files = FindFiles(startDate.getTime(), endDate.getTime());

        System.out.println(startDate.getTime() + " -> " + endDate.getTime());

        //Parse files for the day and updates the WeatherData
        ParseFile(files, startDate.getTime(), endDate.getTime());

        return true;
    }

    private ArrayList<String> FindFiles(Date startDate, Date endDate)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> fileList = new ArrayList<String>();
        Date currentDate;

        for(int i = 0; i < _DataFiles.size(); i++)
        {
            try
            {
                currentDate = format.parse(_DataFiles.get(i).replace(".xml", "-12"));
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                return fileList;
            }

            if(currentDate.before(startDate) || currentDate.after(endDate))
            {
                continue;
            }
            else
            {
                System.out.println(_DataFiles.get(i));
                fileList.add(_DataFiles.get(i));
            }
        }

        return fileList;
    }


    public boolean ParseFile(ArrayList<String> files, Date startDate, Date endDate)
    {
        boolean success = false;

        for(int i = 0; i < files.size(); i++)
        {
            
            // read and parse XML document
            SAXBuilder builder = new SAXBuilder();
            try
            {
                Document doc = builder.build( _DataPath + files.get(i) );    // parse XML tags
                Element root = doc.getRootElement();        // get root of XML tree
                success = FillWeatherData( root, startDate, endDate );                    // fill WeatherData
            }
            // JDOMException indicates a well-formedness error
            catch ( JDOMException e )
            {
                System.out.println( _DataPath + files.get(i) + " is not well-formed." );
                System.out.println( e.getMessage() );
            }
            catch ( IOException e )
            {
                System.out.println( e );
            }
        }

        return success;
    }

    public boolean FillWeatherData( Element current, Date startDate, Date endDate )
    {
        // get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();
        boolean success = false;

        // recursively process each child node
        while ( iterator.hasNext() )
        {
            WeatherConverter dataPoint = new WeatherConverter();
            Element child = ( Element ) iterator.next();

            if(FillDataPoint( child, dataPoint, startDate, endDate))
            {
                WeatherData.add(dataPoint.Convert());
                success = true;
            }
        }

        return success;
    }

    // print XML tags and leaf node values
    public boolean FillDataPoint( Element current, WeatherConverter dataPoint, Date startDate, Date endDate )
    {
        // get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();
        String name = current.getName();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH");

        if (name == "date")
        {
            Date currentDate;
            try
            {
                currentDate = format.parse(current.getValue() + " 12");
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                return false;
            }


            if(currentDate.before(startDate) || currentDate.after(endDate))
            {
                return false;
            }

            dataPoint.date = current.getValue();
        }
        else if (name == "time")
        {
            dataPoint.time = current.getValue();
        }
        else if (name == "temperature")
        {
            dataPoint.temperature = current.getValue();
        }
        else if (name == "humidity")
        {
            dataPoint.humidity = current.getValue();
        }
        else if (name == "barometer")
        {
            dataPoint.barometer = current.getValue();
        }
        else if (name == "windspeed")
        {
            dataPoint.windspeed = current.getValue();
        }
        else if (name == "winddirection")
        {
            dataPoint.winddirection = current.getValue();
        }
        else if (name == "windgust")
        {
            dataPoint.windgust = current.getValue();
        }
        else if (name == "windchill")
        {
            dataPoint.windchill = current.getValue();
        }
        else if (name == "heatindex")
        {
            dataPoint.heatindex = current.getValue();
        }
        else if (name == "uvindex")
        {
            dataPoint.uvindex = current.getValue();
        }
        else if (name == "rainfall")
        {
            dataPoint.rainfall = current.getValue();
        }
        else if (!iterator.hasNext())
        {
            System.out.println(name + " Not Found");
        }

        // recursively process each child node
        while ( iterator.hasNext() )
        {
            Element child = ( Element ) iterator.next();
            if( !FillDataPoint( child, dataPoint, startDate, endDate ) )
            {
                return false;
            }
        }

        return true;
    }

}






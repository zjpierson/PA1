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
        //figure out which files to use
        ArrayList<String> files = FindFiles(day, day);

        //Parse files for the day and updates the WeatherData
        ParseFile(files, day, day);

        return true;
    }

    private ArrayList<String> FindFiles(Date startDate, Date endDate)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        ArrayList<String> fileList = new ArrayList<String>();

        for(int i = 0; i < _DataFiles.size(); i++)
        {
            if(_DataFiles.get(i).contains(format.format(startDate)))
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
                success = FillWeatherData( root, startDate );                    // fill WeatherData
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

    public boolean FillWeatherData( Element current, Date startDate )
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
            if(FillDataPoint( child, dataPoint, startDate))
            {
                WeatherData.add(dataPoint.Convert());
                success = true;
            }
        }

        return success;
    }

    // print XML tags and leaf node values
    public boolean FillDataPoint( Element current, WeatherConverter dataPoint, Date startDate )
    {
        // get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();
        String name = current.getName();

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yy");

        if (name == "date")
        {
            if(current.getValue() != format.format(startDate))
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
            if( !FillDataPoint( child, dataPoint, startDate ) )
            {
                return false;
            }
        }

        return true;
    }

}











//-----------------------------------------------------------------------//
//-----------------------------------------------------------------------//
//-----------------------------------------------------------------------//













//
//    public void ParseFile( String filename)
//    {
//        // read and parse XML document
//        SAXBuilder builder = new SAXBuilder();
//        try
//        {
//            Document doc = builder.build( filename );    // parse XML tags
//            Element root = doc.getRootElement();        // get root of XML tree
//            FillWeatherData( root );                    // fill WeatherData
//        }
//        // JDOMException indicates a well-formedness error
//        catch ( JDOMException e )
//        {
//            System.out.println( filename + " is not well-formed." );
//            System.out.println( e.getMessage() );
//        }
//        catch ( IOException e )
//        {
//            System.out.println( e );
//        }
//    }
//
//    public void FillWeatherData( Element current )
//    {
//        // get children of current node
//        List children = current.getChildren();
//        Iterator iterator = children.iterator();
//
//        // recursively process each child node
//        while ( iterator.hasNext() )
//        {
//            WeatherConverter dataPoint = new WeatherConverter();
//            Element child = ( Element ) iterator.next();
//            FillDataPoint( child, dataPoint);
//            WeatherData.add(dataPoint.Convert());
//        }
//    }
//
//    // print XML tags and leaf node values
//    public void FillDataPoint( Element current, WeatherConverter dataPoint )
//    {
//        // get children of current node
//        List children = current.getChildren();
//        Iterator iterator = children.iterator();
//        String name = current.getName();
//
//        if (name == "date")
//        {
//            dataPoint.date = current.getValue();
//        }
//        else if (name == "time")
//        {
//            dataPoint.time = current.getValue();
//        }
//        else if (name == "temperature")
//        {
//            dataPoint.temperature = current.getValue();
//        }
//        else if (name == "humidity")
//        {
//            dataPoint.humidity = current.getValue();
//        }
//        else if (name == "barometer")
//        {
//            dataPoint.barometer = current.getValue();
//        }
//        else if (name == "windspeed")
//        {
//            dataPoint.windspeed = current.getValue();
//        }
//        else if (name == "winddirection")
//        {
//            dataPoint.winddirection = current.getValue();
//        }
//        else if (name == "windgust")
//        {
//            dataPoint.windgust = current.getValue();
//        }
//        else if (name == "windchill")
//        {
//            dataPoint.windchill = current.getValue();
//        }
//        else if (name == "heatindex")
//        {
//            dataPoint.heatindex = current.getValue();
//        }
//        else if (name == "uvindex")
//        {
//            dataPoint.uvindex = current.getValue();
//        }
//        else if (name == "rainfall")
//        {
//            dataPoint.rainfall = current.getValue();
//        }
//        else if (!iterator.hasNext())
//        {
//            System.out.println(name + " Not Found");
//        }
//
//        // recursively process each child node
//        while ( iterator.hasNext() )
//        {
//            Element child = ( Element ) iterator.next();
//            FillDataPoint( child, depth + 1, dataPoint );
//        }
//    }
//}

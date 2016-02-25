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
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.text.*;

public class ParseWeatherData
{
    public WeatherList WeatherData;

    //Constructor
    public ParseWeatherData( )
    {
        this("2010-01.xml");
    }

    public ParseWeatherData( String filename )
    {
        WeatherData = new WeatherList();
        ParseFile(filename);
    }

    public void ParseFile( String filename)
    {
        // read and parse XML document
        SAXBuilder builder = new SAXBuilder();
        try
        {
            Document doc = builder.build( filename );    // parse XML tags
            Element root = doc.getRootElement();        // get root of XML tree
            FillWeatherData( root, 0 );                    // print info in XML tree
        }
        // JDOMException indicates a well-formedness error
        catch ( JDOMException e )
        {
            System.out.println( filename + " is not well-formed." );
            System.out.println( e.getMessage() );
        }
        catch ( IOException e )
        {
            System.out.println( e );
        }
    }

    public void FillWeatherData( Element current, int depth)
    {
        // get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();

        // recursively process each child node
        while ( iterator.hasNext() )
        {
            WeatherConverter dataPoint = new WeatherConverter();
            Element child = ( Element ) iterator.next();
            FillDataPoint( child, depth + 1, dataPoint);
            WeatherData.add(dataPoint.Convert());
        }
    }

    // print XML tags and leaf node values
    public void FillDataPoint( Element current, int depth, WeatherConverter dataPoint )
    {
        // get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();
        String name = current.getName();

        if (name == "date")
        {
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
            FillDataPoint( child, depth + 1, dataPoint );
        }
    }
}

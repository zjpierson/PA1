/*

    **** ReadXML2.java - read and dump contents of an XML file ****

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

public class ReadXML2
{
    public static void main( String[] args )
    {

        ArrayList<WeatherDataContainer> weatherData = new ArrayList<WeatherDataContainer>();

        // check usage
        if ( args.length == 0 )
        {
            System.out.println( "Usage: java -cp .:jdom.jar ReadXML2 file.xml" );
            return;
        }

        // read and parse XML document
        SAXBuilder builder = new SAXBuilder();
        try
        {
            Document doc = builder.build( args[0] );    // parse XML tags
            Element root = doc.getRootElement();        // get root of XML tree
            FillWeatherData( root, 0, weatherData );                    // print info in XML tree
        }
        // JDOMException indicates a well-formedness error
        catch ( JDOMException e )
        {
            System.out.println( args[0] + " is not well-formed." );
            System.out.println( e.getMessage() );
        }
        catch ( IOException e )
        {
            System.out.println( e );
        }
    }

    public static void FillWeatherData( Element current, int depth, ArrayList<WeatherDataContainer> weatherData )
    {
        // get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();

        if ( !iterator.hasNext() )
        {
            System.out.print( " = " + current.getValue() );
        }
        System.out.println();

        // recursively process each child node
        while ( iterator.hasNext() )
        {
            WeatherDataContainer dataPoint = new WeatherDataContainer();
//            System.out.println("New!!!!*************************");
            Element child = ( Element ) iterator.next();
//            listChildren(child, depth + 1);
            FillDataPoint( child, depth + 1, dataPoint);
        }
    }

    // print XML tags and leaf node values
    public static void FillDataPoint( Element current, int depth, WeatherDataContainer weatherDataPoint )
    {
        // get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();
        String name = current.getName();
        System.out.println(name);

        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yy", Locale.ENGLISH);
        DateFormat timeFormat = new SimpleDateFormat("hh:mma", Locale.ENGLISH);

        DateFormat Format = new SimpleDateFormat("mm/dd/yy hh:mma", Locale.ENGLISH);

        if (name == "date")
        {
            try
            {
                System.out.println("*************Parse Date***********************");
                weatherDataPoint.date = dateFormat.parse(current.getValue());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("*************Exception Caught***********************");
            }
        }
        else if (name == "time")
        {
            try
            {
                System.out.println("*************Parse Time***********************");
//                weatherDataPoint.date = Format.parse(date + current.getValue());
                weatherDataPoint.date = timeFormat.parse(current.getValue());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("*************Exception Caught***********************");
            }
        }
        else if (name == "temperature")
        {
        
        }
        else if (name == "humidity")
        {
        
        }
        else if (name == "barometer")
        {
        
        }
        else if (name == "windspeed")
        {
        
        }
        else if (name == "winddirection")
        {
        
        }
        else if (name == "windgust")
        {
        
        }
        else if (name == "windchill")
        {
        
        }
        else if (name == "heatindex")
        {
        
        }
        else if (name == "uvindex")
        {
        
        }
        else if (name == "rainfall")
        {
        
        }
        else 
        {
            System.out.println(name + "Not Found");
        }

        System.out.println(weatherDataPoint.date);

        // recursively process each child node
        while ( iterator.hasNext() )
        {
            Element child = ( Element ) iterator.next();
            FillDataPoint( child, depth + 1, weatherDataPoint );
        }
    }







    /*
     *---------------------------------------------------------
     */

    // print XML tags and leaf node values
    public static void listChildren( Element current, int depth)
    {
    // get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();

        // print node name and leaf node value, indented one space per level in XML tree
        printSpaces( depth );
        System.out.print( current.getName() );
        if ( !iterator.hasNext() )
            System.out.print( " = " + current.getValue() );
        System.out.println();

        // recursively process each child node
        while ( iterator.hasNext() )
        {
            Element child = ( Element ) iterator.next();
            listChildren( child, depth + 1 );
        }
    }

    // indent to show hierarchical structure of XML tree
    private static void printSpaces( int n )
    {
        for ( int i = 0; i < n; i++ )
        {
            System.out.print( " " );
        }
    }
}

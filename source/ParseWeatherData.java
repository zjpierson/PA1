/*
 * ParseWeatherData.java
 *
 * This class will be used to parse though xml files and fill a WeatherList
 * of WeatherDataContainer's. This class also provides the Datasets for each
 * graph when the main WeatherList object is updated.
 *
 * @author Zachary Pierson
 * @version Spring 2016 - GUI
 *
 */

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.text.*;

import org.jfree.data.time.*;

public class ParseWeatherData
{
    public WeatherList WeatherData; //Data that pertains to xml files 

    //Datasets that will update graphs in the main GUI:
    public TimeSeriesCollection TemperatureDataset;
    public TimeSeriesCollection PrecipDataset;
    public TimeSeriesCollection UVDataset;
    public TimeSeriesCollection PressureDataset;
    public TimeSeriesCollection HumidityDataset;
    public TimeSeriesCollection WindSpeedDataset;

    private ArrayList<String> _DataFiles;
    private String _DataPath;

   /*
   * ParseWeatherData Constructor: pass in the local path to the xmlfiles.
   * This constructor sets and inistantiates objects.
   */
    public ParseWeatherData(String dataDirectory)
    {
        WeatherData = new WeatherList();
        _DataPath = dataDirectory;
        _DataFiles = new ArrayList<String>();

        File[] files = new File(dataDirectory).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 

        //for each file, add it to the private _DataFiles
        for (File file : files)
        {
            if (file.isFile() && file.getName().contains(".xml")) 
            {
                _DataFiles.add(file.getName());
            }
        }
    }

   /*
   * ParseWeatherData Constructor: default data path is set to data
   */
    public ParseWeatherData()
    {
        this("./data/");
    }


   /*
   * GetDay: This function takes a Date object that the user selects and
   * finds a list of filenames that need to be parsed.  Then it parses though
   * those files and builds the WeatherData object.
   */
    public boolean GetDay(Date day)
    {
        if(day == null)
        {
            return false;
        }

        //clear the main WeatherList so a new elements can be added
        WeatherData.clear();

        //startMonth is used to get a list of files to parse
        Calendar startMonth = Calendar.getInstance();
        startMonth.setTime(day);
        startMonth.set(Calendar.DAY_OF_MONTH, 1);

        //endMonth is used to get a list of files to parse
        Calendar endMonth = Calendar.getInstance();
        endMonth.setTime(day);
        endMonth.set(Calendar.DAY_OF_MONTH, 28);

        //Get Start and end dates for file parsing purposes
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

        //set the Datasets for each graph
        UpdateDatasets();

        return true;
    }

   /*
   * GetWeek: This function takes a Date object that the user selects and
   * finds a list of filenames that need to be parsed.  Then it parses though
   * those files and builds the WeatherData object.  the Date passed in is
   * specific for the week. Currently does not work properly.
   */
    public boolean GetWeek(Date week)
    {
        if(week == null)
        {
            return false;
        }

        WeatherData.clear();

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

        //set the Datasets for each graph
        UpdateDatasets();

        return true;
    }

   /*
   * GetMonth: This function reads in a date object where it only looks to the
   * month property and parses the xml datafiles to update the WeatherList object.
   */
    public boolean GetMonth(Date month)
    {
        if(month == null)
        {
            return false;
        }

        WeatherData.clear();

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(month);
        startDate.set(Calendar.DAY_OF_MONTH, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(month);
        endDate.set(Calendar.DAY_OF_MONTH, 31);

        //figure out which files to use
        ArrayList<String> files = FindFiles(startDate.getTime(), endDate.getTime());

        //Parse files for the day and updates the WeatherData
        ParseFile(files, startDate.getTime(), endDate.getTime());

        //set the Datasets for each graph
        UpdateDatasets();

        return true;
    }

   /*
   * GetYear: This function reads in a date object where it only looks to the
   * year property and parses the xml datafiles to update the WeatherList object.
   */
    public boolean GetYear(Date year)
    {
        if(year == null)
        {
            return false;
        }

        WeatherData.clear();

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

        UpdateDatasets();

        //set the Datasets for each graph
        return true;
    }

   /*
   * FindFiles: Reads in a start and end date and finds all the xml files that
   * will contain this data. This will return an ArrayList of Strings
   * containing the file names to be parsed.
   */
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


   /*
   * ParseFile: This function will parse though the list of file names
   * and add WeatherDataContainer objects that are between the start 
   * and end date to the WeatherList.
   *
   * This function was taken from Dr. Weiss and Modifyed by Zachary Pierson
   */
    public boolean ParseFile(ArrayList<String> files, Date startDate, Date endDate)
    {
        boolean success = false;

        for(int i = 0; i < files.size(); i++)
        {
            
            // read and parse XML document
            SAXBuilder builder = new SAXBuilder();
            try
            {
                // parse XML tags
                Document doc = builder.build( _DataPath + files.get(i) );
                Element root = doc.getRootElement();        // get root of XML tree
                success = FillWeatherData( root, startDate, endDate );
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

   /*
   * FillWeatherData: This will process each child element of the XML tag.
   *
   * This function was taken from Dr. Weiss and Modifyed by Zachary Pierson
   */
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

    
   /*
   * FillDataPoint: recursively parses though one weather tag in the XML file
   * and set all the appropriate tags, as long as the date is between the start
   * and end dates.
   *
   * This function was taken from Dr. Weiss and Modifyed by Zachary Pierson
   */
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
            //get date of the current date tag value
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

   /*
   * UpdateDatasets: Updates all public member variables to the corrent dataset.
   */
    private void UpdateDatasets()
    {
        TemperatureDataset = WeatherData.getTemperatureDataset();
        PrecipDataset = WeatherData.getPrecipitationDataset();
        UVDataset = WeatherData.getUVIndexDataset();
        PressureDataset = WeatherData.getPressureDataset();
        HumidityDataset = WeatherData.getHumidityDataset();
        WindSpeedDataset = WeatherData.getWindSpeedDataset();
    }
}






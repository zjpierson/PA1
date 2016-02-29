import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.SeriesException; 
import org.jfree.data.time.*; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

public class MyTimeSeries extends ApplicationFrame 
{
   public MyTimeSeries( final String title )
   {
      super( title );         
      XYDataset dataset = createDataset( );         
      JFreeChart chart = createChart( dataset );         
      ChartPanel chartPanel = new ChartPanel( chart );         
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );         
      chartPanel.setMouseZoomable( true , false );         
      setContentPane( chartPanel );
   }

   private XYDataset createDataset( ) 
   {
      TimeSeries series = new TimeSeries( "Random Data" );         
      
      ParseWeatherData dataReader = new ParseWeatherData("2010-01.xml");
      WeatherList data = dataReader.WeatherData;

      for (int i = 0; i < data.size(); i++)    
      {
         try 
         {
            series.add(new Minute(data.get(i).date), data.get(i).temp );                 
         }
         catch ( SeriesException e ) 
         {
            System.err.println("Error adding " + data.get(i).date + " to series");
         }
      }

      return new TimeSeriesCollection(series);
   }     

   private JFreeChart createChart( XYDataset dataset ) 
   {
      return ChartFactory.createTimeSeriesChart(             
      "Computing Test", 
      "Seconds",              
      "Value",              
      dataset,             
      false,              
      true,              
      false);
   }

   public static void main( final String[ ] args )
   {
      String title = "Time Series Management";         
      MyTimeSeries demo = new MyTimeSeries( title );         
      demo.pack( );         
      RefineryUtilities.positionFrameRandomly( demo );         
      demo.setVisible( true );
   }
}   

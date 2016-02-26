import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;
import java.util.*;

public class MyLineChart extends ApplicationFrame
{
   public MyLineChart( String applicationTitle , String chartTitle )
   {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createXYLineChart(
         chartTitle,
         "Time","Temperature",
         createDataset1(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );

      pack();
      setVisible(true);


      boolean Dataset1 = true;
      while(true)
      {
          try
          {
              Thread.sleep(1000);
          }
          catch(InterruptedException ex)
          {
            Thread.currentThread().interrupt();
          }

          if(Dataset1)
          {
              lineChart.getXYPlot().setDataset(createDataset2());
              Dataset1 = false;
          }
          else
          {
              lineChart.getXYPlot().setDataset(createDataset1());
              Dataset1 = true;
          }

      }
   }

   private XYDataset createDataset1( )
   {
       XYSeriesCollection dataset = new XYSeriesCollection();
       XYSeries Temperature = new XYSeries("Temperature");

       Temperature.add( 15 , 1 );
       Temperature.add( 30 , 2 );
       Temperature.add( 60 , 3 );
       Temperature.add( 120 , 4 );
       Temperature.add( 200 , 5 );

       dataset.addSeries(Temperature);

      return dataset;
   }

   private XYDataset createDataset2( )
   {
       XYSeriesCollection dataset = new XYSeriesCollection();
       XYSeries Temperature = new XYSeries("Temperature");

       Temperature.add( 0 , 1 );
       Temperature.add( 30 , 2 );
       Temperature.add( 60 , 3 );
       Temperature.add( 90 , 4 );
       Temperature.add( 120 , 5 );

       dataset.addSeries(Temperature);

      return dataset;
   }

   public static void main( String[ ] args ) 
   {
      MyLineChart chart = new MyLineChart(
      "School Vs Years" ,
      "Numer of Schools vs years");

//      chart.pack( );
//      RefineryUtilities.centerFrameOnScreen( chart );
//      chart.setVisible( true );
   }
}

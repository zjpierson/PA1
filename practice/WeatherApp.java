import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

import org.jfree.chart.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.axis.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;
import org.jfree.data.time.*;

public class WeatherApp extends JFrame
{
    public WeatherApp()
    {
        super("Weather History"); // Call JFrame constructor with a title

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Exit on close

        InitComponents(); // Build GUI and set up events
    }

    private void InitComponents()
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar( menuBar ); //Set the frames' menu bar

        //Calculations Menu
        JMenu calcMenu = new JMenu("Calculate");

        JMenuItem avgTempItem = new JMenuItem("Average Temperature");
        avgTempItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //do stuff
            }
        } );
        calcMenu.add(avgTempItem); //Add avgTemp to calculation menu

        JMenuItem maxMinTempItem = new JMenuItem("Max & Min Temperatures");
        maxMinTempItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //do stuff
            }
        } );
        calcMenu.add(maxMinTempItem); //Add avgTemp to calculation menu

        JMenuItem avgWindSpdItem = new JMenuItem("Average Wind Speed");
        avgWindSpdItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //do stuff
            }
        } );
        calcMenu.add(avgWindSpdItem); //Add avgTemp to calculation menu

        JMenuItem maxGustItem = new JMenuItem("Max Wind Gust");
        maxGustItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //do stuff
            }
        } );
        calcMenu.add(maxGustItem); //Add avgTemp to calculation menu

        JMenuItem avgTemp = new JMenuItem("Average Temperature");
        avgTemp.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //do stuff
            }
        } );
        calcMenu.add(avgTemp); //Add avgTemp to calculation menu

        JMenuItem windDirItem = new JMenuItem("Prevailing Wind Direction");
        windDirItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //do stuff
            }
        } );
        calcMenu.add(windDirItem); //Add avgTemp to calculation menu

        JMenuItem rainfallItem = new JMenuItem("Total Rainfall");
        rainfallItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //do stuff
            }
        } );
        calcMenu.add(rainfallItem); //Add avgTemp to calculation menu
        menuBar.add(calcMenu); //Add calcMenu to menu bar

        //Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                // Display dialog with help message
                JOptionPane.showMessageDialog(null, "Here I will give help as to how"
                                              + " a person uses this program...",
                                              "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        helpMenu.add(helpItem); //Add help to help menu

        JMenuItem aboutItem = new JMenuItem("About Application");
        aboutItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                // Display dialog with about message
                JOptionPane.showMessageDialog(null, "About Weather History:"
                                             + "\nAuthors: Forrest Miller, Zachary"
                                             + " Pierson, and Samantha Kranstz\n"
                                             + "Version 1.0 Release 1\nThis program"
                                             + " was written in Java and is intended"
                                             + " to display weather history data.\n"
                                             + "Forrest blah. Zachary blah. "
                                             + "Samantha blah.", "About Weather History",
                                             JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        helpMenu.add(aboutItem); //Add about to help menu

        menuBar.add(helpMenu);

//------------------------ Temperature LineChart --------------------------//        

        ParseWeatherData dataReader = new ParseWeatherData();
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 1, 20);

        dataReader.GetYear(cal.getTime());

        JPanel temperaturePane = new JPanel();

        JFreeChart chartData = ChartFactory.createTimeSeriesChart( "Temperature", "CategoryAxisLable", "ValueAxisLable", dataReader.TemperatureDataset, true, true, false);
        WeatherToolTip xyToolTipGenerator = new WeatherToolTip(dataReader.WeatherData);
        XYPlot TemperaturePlot = chartData.getXYPlot();
        XYLineAndShapeRenderer render = (XYLineAndShapeRenderer) TemperaturePlot.getRenderer();
        render.setBaseToolTipGenerator(xyToolTipGenerator);

        ChartPanel chartPanel = new ChartPanel( chartData );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );

        temperaturePane.add(chartPanel);
        add(temperaturePane);

//--------------------------------- END ----------------------------------//        


        pack(); // Package the view

        setVisible(true);

          try
          {
              Thread.sleep(10000);
          }
          catch(InterruptedException ex)
          {
            Thread.currentThread().interrupt();
          }
              
            dataReader.GetDay(cal.getTime());
            TemperaturePlot.setDataset(dataReader.TemperatureDataset);

    }

    public static void main( String args[] )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                new WeatherApp();
            }
        } );
    }

}

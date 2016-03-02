/*
 * WeatherApp.java
 *
 * Weather history GUI with a custom inner panel. The main frame implements an
 * action listener, and creates a custom menu. The custom inner panel holds 5
 * more panels that display our data graphs.
 *
 * @author Forrest Miller
 * @version Spring 2016 - GUI
 *
 * Modifications:
 *  Feb. 13, 2016   Started on a GUI design
 *  Feb. 17, 2016   Got GUI design approved by group and started research
 *  Feb. 18, 2016   Meeting. Planned out main frame and panel setup
 *  Feb. 23, 2016   Created main frame and menus
 *  Feb. 24, 2016   Created custom panel and inner panels
 *  Feb. 25, 2016   Set up for action events and ready for graphs
 *                  Did Commenting
 *  Feb. 26, 2016   Commenting
 *  Feb. 28, 2016   More commenting. Started integrating Sam's code
 *  Feb. 29, 2016   More integration...waiting on Sam's code
 *  Mar.  1, 2016   Finished integration and submitted
 */

// Import java class libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.text.*;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;

/*
 * WeatherApp class that extends JFrame and implements the ActionListener
 */
public class WeatherApp extends JFrame implements ActionListener
{
    public JRadioButton dailyFilter; //Daily radio button
    public JRadioButton weeklyFilter; //Weekly radio button
    public JRadioButton monthlyFilter; //Monthly radio button
    public JRadioButton yearlyFilter; //Yearly radio button
    public JDatePickerImpl datePicker; //Date picker
    public MainPanel mainPanel; //Main panel for charts
    public Date currentDate; //Selected Date
    public Statistics stats = new Statistics();
    public ParseWeatherData parser = new ParseWeatherData();

    /*
     * WeatherApp constructor: Sets minimum size and calls InitComponents.
     */
    public WeatherApp()
    {
        super("Weather History"); // Call JFrame constructor with a title

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Exit on close
        setMinimumSize( new Dimension(600, 800) ); // Set minimum width and height

        initComponents(); // Build GUI and set up events
    }

    /*
     * initComponents: Creates GUI and packs it. Also sets up menu events.
     */
    private void initComponents()
    {
        JMenuBar menuBar = new JMenuBar(); // Main menu bar

        // Calculations Menu
        JMenu calcMenu = new JMenu("Calculate"); // Create calculations menu

    	// Create average temperature menu item and provide action listener
        JMenuItem avgTempItem = new JMenuItem("Average Temperature");
        avgTempItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                JOptionPane.showMessageDialog(null, stats.TempAvg(parser.WeatherData),
                                              "Average Temperature",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        calcMenu.add(avgTempItem); // Add avgTemp to calculation menu

    	// Create max/min temp menu item and provide action listener
        JMenuItem maxMinTempItem = new JMenuItem("Max & Min Temperatures");
        maxMinTempItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                ArrayList<OccurenceContainer> maxMinTemp = stats.HighLow(parser.WeatherData);
                OccurenceContainer max = maxMinTemp.get(0);
                OccurenceContainer min = maxMinTemp.get(1);

                JOptionPane.showMessageDialog(null, "Maximum: "+ max.dataPoint
                                              + " " + max.date + "\nMinimum: "
                                              + min.dataPoint + " " + min.date,
                                              "Min and Max Temperature",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        calcMenu.add(maxMinTempItem); // Add maxMinTemp to calculation menu

    	// Create average wind speed menu item and provide action listener
        JMenuItem avgWindSpdItem = new JMenuItem("Average Wind Speed");
        avgWindSpdItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                JOptionPane.showMessageDialog(null, stats.WindSpeedMean(parser.WeatherData),
                                              "Average Wind Speed",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        calcMenu.add(avgWindSpdItem); //Add avgWindSpd to calculation menu

        // Create max gust menu item and provide action listener
    	JMenuItem maxGustItem = new JMenuItem("Max Wind Gust");
        maxGustItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                OccurenceContainer gust = stats.MaxWindGust(parser.WeatherData);

                JOptionPane.showMessageDialog(null, "Max Gust: " + gust.dataPoint
                                              + " " + gust.date, "Max Gust",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        calcMenu.add(maxGustItem); //Add maxGust to calculation menu

    	// Create prevailing wind direction menu item and provide action listener
        JMenuItem windDirItem = new JMenuItem("Prevailing Wind Direction");
        windDirItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                JOptionPane.showMessageDialog(null, "Prevailing Wind Direction: "
                                              + stats.PrevailWindDirect(parser.WeatherData),
                                              "Prevailing Wind Direction",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        calcMenu.add(windDirItem); //Add windDir to calculation menu

    	// Create total rainfall menu item and provide action listener
        JMenuItem rainfallItem = new JMenuItem("Total Rainfall");
        rainfallItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                JOptionPane.showMessageDialog(null, "Rainfall: "
                                              + stats.Rainfall(parser.WeatherData),
                                              "Total Rainfall",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        calcMenu.add(rainfallItem); //Add rainfall to calculation menu
        menuBar.add(calcMenu); //Add calcMenu to menu bar

        //Help Menu
        JMenu helpMenu = new JMenu("Help"); // Create help menu
	
    	// Create help menu item and provide action listener
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                // Display dialog with help message
                JOptionPane.showMessageDialog(null, "To view calculations for the"
                                              + " current day, week, month, or"
					                          + " year:\n    Click on Calculate menu"
					                          + "\n    Choose the calculation you "
					                          + "wish to see\n\nTo view different "
					                          + "data sets:\n    Choose daily, "
					                          + "weekly, monthly, or yearly\n    "
                                              + "Select the date that you wish to "
					                          + "view, or is in the week/month/year"
					                          + " you wish to view\n\nThe graphs"
					                          + " available to view are(from top"
					                          + " to bottom):\n    Temperature\n"
					                          + "    Precipitation\n    UV Index, "
					                          + "Barometric Pressure, & Humidity"
					                          + "(in a tabbed panel)\n    Wind Direction",
                                              "Help", JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        helpMenu.add(helpItem); //Add help to help menu

    	// Create about menu item and provide action listener
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
                                             + "Forrest created the main GUI and did"
                                             + " some event handling. Zachary"
                                             + " did the\ngraphing and helped with the"
                                             + " file parsing. Samantha did the "
                                             + "calculations and\npreparing of the data"
                                             + " to send to the UI.",
                                             "About Weather History",
                                             JOptionPane.INFORMATION_MESSAGE);
            }
        } );
        helpMenu.add(aboutItem); //Add about to help menu
        menuBar.add(helpMenu); //Add help menu to menu bar
        setJMenuBar(menuBar); //Set the frame menu bar

        //Create panel with radio buttons and date picker
        JPanel topPanel = new JPanel(); // Create panel for buttons and picker
        ButtonGroup filterBtnGroup = new ButtonGroup();
        dailyFilter = new JRadioButton("Daily"); // Daily radio button
        weeklyFilter = new JRadioButton("Weekly"); // Weekly radio button
        monthlyFilter = new JRadioButton("Monthly"); // Monthly radio button
        yearlyFilter = new JRadioButton("Yearly"); // Yearly radio button

        dailyFilter.setActionCommand("Daily");
        dailyFilter.setSelected(true); // Make Daily automatically selected
        dailyFilter.addActionListener(this);
        filterBtnGroup.add(dailyFilter); // Add daily to button group
        
        weeklyFilter.setActionCommand("Weekly");
        weeklyFilter.addActionListener(this);
        filterBtnGroup.add(weeklyFilter); // Add weekly to button group

        monthlyFilter.setActionCommand("Monthly");
        monthlyFilter.addActionListener(this);
        filterBtnGroup.add(monthlyFilter); // Add monthly to button group

        yearlyFilter.setActionCommand("Yearly");
        yearlyFilter.addActionListener(this);
        filterBtnGroup.add(yearlyFilter); // Add monthly to button group

	    // Add radio buttons to panel
        topPanel.add(dailyFilter);
        topPanel.add(weeklyFilter);
        topPanel.add(monthlyFilter);
        topPanel.add(yearlyFilter);
        topPanel.setOpaque(true);

        // Set start day of picker to 7/11/2012
        UtilDateModel model = new UtilDateModel(new Date(112, 6, 11)); // Make model
        model.addChangeListener(new ChangeListener()
        {
            // Create a changeListener
            public void stateChanged( ChangeEvent e)
            {
                Date selectedDate = (Date) datePicker.getModel().getValue();

                if (selectedDate != null)
                {
                    currentDate = selectedDate; // Set selected date
                    String selectedBtn = filterBtnGroup.getSelection().getActionCommand();

                    // If button is Daily then call GetDay
                    if (selectedBtn == "Daily")
                    {
                        //parser.GetDay(currentDate);
                    }

                    // If button is Weekly then call GetWeekly
                    if (selectedBtn == "Weekly")
                    {
                        //parser.GetWeekly(currentDate);
                    }

                    // If button is Monthly then call GetMonthly
                    if (selectedBtn == "Monthly")
                    {
                        //parser.GetMonthly(currentDate);
                    }

                    // If button is Yearly then call GetYearly
                    if (selectedBtn == "Yearly")
                    {
                        //parser.GetYearly(currentDate);
                    }
                }
            }
        });

        Properties p = new Properties(); // Set up properties
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p); //Create panel

        // Create picker
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        topPanel.add(datePicker); // Add picker to topPanel

        add(topPanel, BorderLayout.NORTH); //Add top panel to frame

	    mainPanel = new MainPanel(); //Create main panel
        mainPanel.setOpaque(true);
        add(mainPanel, BorderLayout.CENTER); //Add main panel to frame

        pack(); // Package the view
    }

    /*
     * actionPerformed: Handles radio button events.
     */
    public void actionPerformed(ActionEvent ae)
    {
        Date selectedDate = (Date) datePicker.getModel().getValue();

        if ( ae.getSource() == dailyFilter )
        {
            //parser.GetDay(currentDate);
        }
        else if ( ae.getSource() == weeklyFilter )
        {
            //parser.GetWeekly(currentDate);
        }
        else if ( ae.getSource() == monthlyFilter )
        {
            //parser.GetMonthly(currentDate);
        }
        else if ( ae.getSource() == yearlyFilter )
        {
            //parser.GetYearly(currentDate);
        }
    }

    /*
     * main: Invokes a runnable and creates and shows WeatherApp.
     */
    public static void main( String args[] )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                new WeatherApp().setVisible(true);
            }
        } );
    }
}

/*
 * MainPanel class
 */
class MainPanel extends JPanel
{
    public JPanel tempPanel;
    public JPanel precipPanel;
    public JPanel uvPanel;
    public JPanel pressurePanel;
    public JPanel humidPanel;
    public JPanel windPanel;

    /*
     * MainPanel Constructor: Calls super constructor and calls initComponents.
     */
	public MainPanel()
	{
        super( new GridLayout(0,1) );
        initComponents();
	}

    /*
     * initComponents: Creates the panels that will display the graphs.
     */
    private void initComponents()
    {
        //temp panel
        tempPanel = new JPanel();
        tempPanel.setOpaque(true);
        add(tempPanel); // Add temperature panel to main panel

        //precipitation panel
        precipPanel = new JPanel();
        precipPanel.setOpaque(true);
        add(precipPanel); // Add precipitation panel to main panel

        //uv index/barometric pressure/humidity panels in a tabbed pane
        uvPanel = new JPanel();
        uvPanel.setOpaque(true);
        pressurePanel = new JPanel();
        pressurePanel.setOpaque(true);
	    humidPanel = new JPanel();
	    humidPanel.setOpaque(true);
        JTabbedPane tabbedPane = new JTabbedPane(); // Create a tabbed pane
        tabbedPane.addTab("UV Index", null, uvPanel); // Add UV panel to tabbed pane
        // Add barometric pressure panel to tabbed pane
        tabbedPane.addTab("Barometric Pressure", null, pressurePanel);
        // Add humidity panel to tabbed pane
	    tabbedPane.addTab("Humidity", null, humidPanel);
        add(tabbedPane); // Add tabbed pane to main panel

        //wind speed panel
        windPanel = new JPanel();
        windPanel.setOpaque(true);
        add(windPanel); // Add wind speed panel to main panel
    }
}

/*
 * DateLabelFormatter class
 */
class DateLabelFormatter extends AbstractFormatter
{
    private String datePattern = "MM/dd/yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    //Override
    public Object stringToValue(String text) throws ParseException
    {
        return dateFormatter.parseObject(text);
    }

    //Override
    public String valueToString(Object value) throws ParseException
    {
        if (value != null)
        {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }
}

/**
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
 *  Feb. 28, 2016   Finished putting everything together and submitted
 */

// Import java class libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * WeatherApp class that extends JFrame and implements the ActionListener
 */
public class WeatherApp extends JFrame implements ActionListener
{
    public JRadioButton dailyFilter; //Daily radio button
    public JRadioButton weeklyFilter; //Weekly radio button
    public JRadioButton monthlyFilter; //Monthly radio button
    public JRadioButton yearlyFilter; //Yearly radio button
    public Statistics stats = new Statistics();

    /**
     */
    public WeatherApp()
    {
        super("Weather History"); // Call JFrame constructor with a title

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Exit on close
        setMinimumSize( new Dimension(400, 600) );

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
                /*
                JOptionPane.showMessageDialog(null, stats.TempAvg(weatherContainerList),
                                              "Average Temperature For " + currentDate,
                                              JOptionPane.INFORMATION_MESSAGE);*/
            }
        } );
        calcMenu.add(avgTempItem); //Add avgTemp to calculation menu

        JMenuItem maxMinTempItem = new JMenuItem("Max & Min Temperatures");
        maxMinTempItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //call Samantha's stuff
            }
        } );
        calcMenu.add(maxMinTempItem); //Add avgTemp to calculation menu

        JMenuItem avgWindSpdItem = new JMenuItem("Average Wind Speed");
        avgWindSpdItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //call Samantha's stuff
            }
        } );
        calcMenu.add(avgWindSpdItem); //Add avgTemp to calculation menu

        JMenuItem maxGustItem = new JMenuItem("Max Wind Gust");
        maxGustItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //call Samantha's stuff
            }
        } );
        calcMenu.add(maxGustItem); //Add avgTemp to calculation menu

        JMenuItem avgTemp = new JMenuItem("Average Temperature");
        avgTemp.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //call Samantha's stuff
            }
        } );
        calcMenu.add(avgTemp); //Add avgTemp to calculation menu

        JMenuItem windDirItem = new JMenuItem("Prevailing Wind Direction");
        windDirItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //call Samantha's stuff
            }
        } );
        calcMenu.add(windDirItem); //Add avgTemp to calculation menu

        JMenuItem rainfallItem = new JMenuItem("Total Rainfall");
        rainfallItem.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent ae )
            {
                //call Samantha's stuff
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

        //Create panel with radio buttons
        JPanel radioBtnPanel = new JPanel();
        ButtonGroup filterBtnGroup = new ButtonGroup();
        dailyFilter = new JRadioButton("Daily");
        weeklyFilter = new JRadioButton("Weekly");
        monthlyFilter = new JRadioButton("Monthly");
        yearlyFilter = new JRadioButton("Yearly");

        dailyFilter.setActionCommand("Daily");
        dailyFilter.setSelected(true);
        dailyFilter.addActionListener(this);
        filterBtnGroup.add(dailyFilter);
        
        weeklyFilter.setActionCommand("Weekly");
        weeklyFilter.addActionListener(this);
        filterBtnGroup.add(weeklyFilter);

        monthlyFilter.setActionCommand("Monthly");
        monthlyFilter.addActionListener(this);
        filterBtnGroup.add(monthlyFilter);

        yearlyFilter.setActionCommand("Yearly");
        yearlyFilter.addActionListener(this);
        filterBtnGroup.add(yearlyFilter);

        radioBtnPanel.add(dailyFilter);
        radioBtnPanel.add(weeklyFilter);
        radioBtnPanel.add(monthlyFilter);
        radioBtnPanel.add(yearlyFilter);
        radioBtnPanel.setOpaque(true);

        add(radioBtnPanel, BorderLayout.NORTH); //Add radio button panel to frame

	    MainPanel mainPanel = new MainPanel();
        mainPanel.setOpaque(true);
        add(mainPanel, BorderLayout.CENTER); //Add main panel to frame

        pack(); // Package the view
    }

    public void actionPerformed(ActionEvent ae)
    {
        if ( ae.getSource() == dailyFilter )
        {
            //do daily things
        }
        else if ( ae.getSource() == weeklyFilter )
        {
            //do weekly things
        }
        else if ( ae.getSource() == monthlyFilter )
        {
            //do monthly things
        }
        else if ( ae.getSource() == yearlyFilter )
        {
            //do yearly things
        }
    }

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
    public JLabel dateLabel;
    public JPanel tempHumidPanel;
    public JPanel precipPanel;
    public JPanel uvPanel;
    public JPanel pressurePanel;
    public JPanel windPanel;

	public MainPanel()
	{
        super( new GridLayout(0,1) );
        initComponents();
	}

    private void initComponents()
    {
        //left arrow button + date label + right arrow button???? or combobox??

        //temp/humidity panel
        tempHumidPanel = new JPanel();
        tempHumidPanel.setOpaque(true);
        add(tempHumidPanel);

        //precipitation panel
        precipPanel = new JPanel();
        precipPanel.setOpaque(true);
        add(precipPanel);

        //uv index/barometric pressure panels in a tabbed pane
        uvPanel = new JPanel();
        uvPanel.setOpaque(true);
        pressurePanel = new JPanel();
        pressurePanel.setOpaque(true);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("UV Index", null, uvPanel);
        tabbedPane.addTab("Barometric Pressure", null, pressurePanel);
        add(tabbedPane);

        //wind speed panel
        windPanel = new JPanel();
        windPanel.setOpaque(true);
        add(windPanel);
    }
}

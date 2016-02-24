############################################
# THE JCOMMON CLASS LIBRARY: Version 0.9.2 #
############################################

26 March 2004.

(C)opyright, 2000-2004, by Object Refinery Limited and Contributors.

-----------------
1.  INTRODUCTION
-----------------
JCommon is a free general purpose Java class library that is used in
several projects at www.jfree.org, including JFreeChart and
JFreeReport.  

JCommon is licensed, free of charge, under the terms of the GNU Lesser
General Public Licence.  A copy of the licence is included in the download.

The latest version of this class library can be obtained from:

    http://www.jfree.org/jcommon/index.html

If you have any comments, suggestions or bugs to report, please post a
message in the JCommon forum.

-----------------
2.  CONTRIBUTORS
-----------------
Thanks to the following developers who have contributed code to this
class library:  Anthony Boulestreau, Jeremy Bowman, J. David
Eisenberg, Paul English, Hans-Jurgen Greiner, Bill Kelemen, Achilleus
Mantzios, Thomas Meier, Thomas Morgner, Krzysztof Paz, Andrzej Porebski, Nabuo
Tamemasa, Mark Watson and Hari.

---------------
3.  TEST CASES
---------------
Some test cases have been developed using the JUnit testing
framework.  These are included with the source code, in the following
packages:

org.jfree.junit
org.jfree.date.junit
org.jfree.ui.junit
org.jfree.util.junit

If you want to recompile these packages, you will need the junit.jar
file that is included in the lib subdirectory.

To find out more about JUnit, please visit:

http://www.junit.org/

The current JCommon tests have been written using JUnit 3.8.

---------------
4.  WHAT'S NEW
---------------
Changes in each version are listed here:

0.9.2 : (26-Mar-2004) Update to coincide with JFreeChart 0.9.17.

0.9.1 : (9-Jan-2004) Update to coincide with JFreeChart 0.9.16.

0.9.0 : (28-Nov-2003) Update to coincide with JFreeChart 0.9.15.

0.8.9 : (17-Nov-2003) Update to coincide with JFreeChart 0.9.14.

0.8.8 : (26-Sep-2003) Update to coincide with JFreeChart 0.9.13.

0.8.7 : (11-Sep-2003) Update to coincide with JFreeChart 0.9.12.

0.8.6 : (8-Aug-2003) Update to coincide with JFreeChart 0.9.11.

0.8.5 : (25-Jul-2003) Transferred some support classes from JFreeChart.

0.8.3 : (16-Jun-2003) XML Parser: error locations are printed in the
        parse exceptions to make debugging xml files easier.

0.8.2a: (04-Jun-2003) xml parser: configuration interface modified.
	+ Bugfixes ...
	
0.8.2 : (26-May-2003) xml parser class factory bug fix

0.8.1 : (09-May-2003) Added support for the xml parser and imported
        some base classes from the JFreeReport project.
        
0.8.0 : (24-Apr-2003) Renamed all packages from com.jrefinery.* 
        to org.jfree.*.

0.7.3 : (11-Apr-2003) Added serialization for SerialDate and
        SpreadsheetDate classes.  Added a SerialUtilities class.
        Removed palette classes (now in JFreeChart).  Added an
        attribute to control whether or not a workaround is used for
        drawing rotated text.

0.7.2 : (6-Feb-2003) Bug fixes and Javadoc updates, incorporated an
        Ant script to recompile the source files and generate the
        Javadocs.

0.7.1 : (18-Oct-2002) Bug fixes and Javadoc updates.

0.7.0 : (4-Sep-2002) Moved package (com.jrefinery.data) to JFreeChart
        project. Bug fixes and Javadoc updates.

0.6.4 : (27-Jun-2002) Added logo to about box. Minor bug fixes (plus
        JUnit tests) and code tidy up.

0.6.3 : (14-Jun-2002) Bug fixes and Javadoc updates.

0.6.2 : (7-Jun-2002) Added GanttSeriesCollection and supporting
        classes.  Added Polish resource bundle.  Minor bug fixes.

0.6.1 : (5-Apr-2002) Added MeterDataset interface and
        DefaultMeterDataset class. Resource bundles for French, German
        and Spanish languages. Reinstated the Week class.  Minor bugfixes.

0.6.0 : (22-Mar-2002) Changes to the API for the TimePeriod classes,
        to improve methods that convert to java.util.Date.  New
        DefaultHighLowDataset class.  New ResourceBundles for items
        that require localisation.
 
0.5.6 : (6-Mar-2002) Bug fix for combined datasets.  Additional
        methods in the TimePeriod class to handle different
        timezones. Updated About box classes moved to new package
        com.jrefinery.ui.about.  Renamed Files.java -->
        FileUtilities.java and SerialDates.java -->
        SerialDateUtilities.java.  Added new domain name
        (http://www.object-refinery.com) in the source headers.

0.5.5 : (15-Feb-2002) Fixed bugs in the constructors for the
        TimePeriod subclasses.  Reversed the order of the parameters
        in the Month(int, int) constructor.  Added methods to
        Datasets.java to handle stacked data ranges.  Fixed bug in
        CombinedDataset.
  
0.5.4 : (8-Feb-2002) New WindDataset interface and DefaultWindDataset
        class.  Bug fix for DefaultCategoryDataset. 

0.5.3 : (25-Jan-2002) Bug fixes, some minor API changes.

0.5.2 : (10-Dec-2001) Added new combination datasets by Bill Kelemen.
        Added contributors table to the AboutFrame class.

0.5.1 : (27-Nov-2001) AboutPanel and AboutFrame classes added.

0.5.0 : (21-Nov-2001) PieDataset and DefaultPieDataset classes added.

0.4.2 : (16-Nov-2001) New classes in the com.jrefinery.data.* package,
        plus some new JUnit test cases.

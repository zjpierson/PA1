// DateType.java

// Class to store mm/dd/yy dates.
// Includes constructors, equals method, and toString method.
// See also DateTest.java (tests functionality of DateType class).

// Author: John M. Weiss, Ph.D.
// Class: CSC468 GUI Programming, Spring 2016

package weiss.date;

import weiss.sort.*;

public class DateType implements Sortable
{
    // private data
    private int month, day, year;

    // default no-argument constructor function
    public DateType()
    {
        this( 1, 1, 1970 );
    }

    // 3-argument constructor function
    public DateType( int m, int d, int y )
    {
        month = m;
        day   = d;
        year  = y;
    }

    // test for object equality
    // note that all classes are derived from the Object class,
    // so an Object is the most general argument for this method
    public boolean equals( Object rhs )
    {
        // must have two DateType instances or cannot do comparison
        if ( !( rhs instanceof DateType ) ) return false;

        // see if month/day/year all match up
        // note that we must cast Object to DateType to access fields
        DateType rhsDate = ( DateType ) rhs;
        return month == rhsDate.month && day == rhsDate.day && year == rhsDate.year;
    }

    // relational test on two dates
    public boolean lessThan( Sortable rhs )
    {
        // must be two DateType instances or can't do comparison
        if ( !( rhs instanceof DateType ) ) return false;

        DateType rhsDate = ( DateType ) rhs;

        // test year
        if ( year < rhsDate.year ) return true;
        if ( year > rhsDate.year ) return false;

        // test month
        if ( month < rhsDate.month ) return true;
        if ( month > rhsDate.month ) return false;

        // test day
        if ( day < rhsDate.day ) return true;
        // if ( day >= rhsDate.day ) return false;
        return false;					// else Java complains
    }

    // convert a DateType object to a String (usually for output)
    public String toString( )
    {
        return String.format( "%02d/%02d/%02d", month, day, year );
    }
}


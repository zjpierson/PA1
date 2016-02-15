// DateSort.java

// Illustration of Java array usage.
// Generate an array of random DateType class instances (DateType.java)
// and sort them into ascending order.

// Author: John M. Weiss, Ph.D.
// Class: CSC468 GUI Programming, Spring 2016

import weiss.date.*;
import weiss.sort.*;

public class DateSort
{
    public static void main( String [] args )
    {
        // get number of dates to sort
        int n = 10;
        if ( args.length > 0 ) n = Integer.parseInt( args[0] );

        // generate an array of DateType instances
        DateType [] dates = makeDates( n );

        // print the unsorted date values
        System.out.println( "\nUnsorted Dates:" );
        for ( DateType date : dates )
        {
            System.out.println( date + " " );
        }

        // sort the array of dates
        Sort.selectionSort( dates );

        // print the sorted date values
        System.out.println( "\nSorted Dates:" );
        for ( DateType date : dates )
        {
            System.out.println( date + " " );
        }
    }

    // makeDates() - returns an array of DateType instances
    static DateType [] makeDates( int n )
    {
        // generate an array of DateType references
        DateType [] dates = new DateType [n];

        // generate n DateType instances and give them random date values
        for ( int i = 0; i < n; i++ )
        {
            int m = (int)Math.round( 11.0 * Math.random() ) + 1;

            int d = (int)Math.round( 29.0 * Math.random() ) + 1;
            if ( m == 2 && d > 28 ) d = 28;

            int y = (int)Math.round( 16.0 * Math.random() ) + 2000;

            dates[i] = new DateType( m, d, y );
        }

        // return the array of DateType instances
        return dates;
    }
}


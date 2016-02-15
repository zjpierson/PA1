// DateTest.java

// Test functionality of the DateType class (DateType.java).

// Author: John M. Weiss, Ph.D.
// Class: CSC468 GUI Programming, Spring 2016

import weiss.date.*;

public class DateTest
{
    public static void main( String [] args )
    {
        // test constructors
        DateType date1 = new DateType( );
        DateType date2 = new DateType( 7, 4, 1776 );

        // test output (toString method)
        System.out.println( "date1 = " + date1 );
        System.out.println( "date2 = " + date2 );

        // test equals method
        if ( date1.equals( date2 ) )
            System.out.println( date1 + " == " + date2 );
        else
            System.out.println( date1 + " != " + date2 );
    }
}


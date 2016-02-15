// Sort.java
//
// Generic sort class.
// Sorts an array of objects into ascending order using selection sort.
//
// Note that this would NOT work with an Object parameter,
// since the Object class does not have a lessThan() method.
// The Sortable interface ensures this functionality.
//
// Author: John M. Weiss, Ph.D.
// Class: CSC468 GUI Programming, Spring 2016

package weiss.sort;

public class Sort
{
    // selection sort - sorts an array of sortable objects into ascending order
    public static void selectionSort( Sortable [] array )
    {
        for ( int i = 0; i < array.length - 1; i++ )
        {
            int min = i;
            for ( int j = i + 1; j < array.length; j++ )
                if ( array[j].lessThan( array[min] ) )
                    min = j;
            Sortable temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
}


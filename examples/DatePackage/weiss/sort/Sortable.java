//===========================================================================
// Sortable.java
//===========================================================================
// Defines an interface for objects that may be sorted.
// Guarantees the presence of a lessThan() method for sorting.
//
// Author: John M. Weiss, Ph.D.
// Class: CSC468 GUI Programming, Spring 2016

package weiss.sort;

public interface Sortable
{
    public boolean lessThan( Sortable rhs );
}


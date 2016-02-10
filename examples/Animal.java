/*
                    ***** Animal.java *****

OOP example illustrating inheritance and late binding in Java.
Inheritance is used to derive various subclasses from an Animal base class.

This is the base Animal class. Derived classes include Bird, Fish, Mammal, Cat, and Dog.

Author: John M. Weiss, Ph.D.
Class: CSC468 GUI Programming, Spring 2016

Modifications:
*/

//-----------------------------------------------------------------------

// Define the base Animal class.
// Note that this class must be labelled abstract, since it contains abstract method(s).
public abstract class Animal
{
    // note use of protected instead of private
    protected String name;

    // constructor
    public Animal( String n )
    {
        System.out.println( "Animal constructor: " + n );
        name = n;
    }

    // get name of animal (accessor)
    public String getName( String n )
    {
        return name;
    }

    // set name of animal (mutator)
    public void setName( String n )
    {
        System.out.println( "Animal ChangeName: changing " + name + " to " + n );
        name = n;
    }

    // convert an Animal object to a String (usually for output)
    public String toString( )
    {
        return name;
    }

    // talk() is an abstract method that must be overridden in non-abstract derived classes
    // this helps define the Animal interface by guaranteeing the talk() method exists
    abstract public void talk();

    // say() is a method that may be overridden in derived classes (but need not be)
    public void say()
    {
        System.out.println( name + " sez: You must be talkin' to me! (Animal say)" );
    }

    // overloaded speak() method is final, and cannot be overridden in derived classes
    public final void speak()
    {
        System.out.println( name + " sez: You talkin' to me? (Animal speak)" );
    }

    public final void speak( String s )
    {
        System.out.println( name + " sez: " + s + " (overloaded Animal speak)" );
    }
}

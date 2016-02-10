/*
                    ***** AnimalTalk.java *****

OOP example illustrating inheritance and late binding in Java.
Inheritance is used to derive various subclasses from an Animal base class.

This is an application that exercises the class hierarchy functionality.

Author: John M. Weiss, Ph.D.
Class: CSC468 GUI Programming, Spring 2016

Modifications:
*/

//-----------------------------------------------------------------------
// main() function to test the class hierarchy
//-----------------------------------------------------------------------
public class AnimalTalk
{
    public static void main( String [] args )
    {
        Animal ani;     	        // abstract base class references are OK
        // ani = new Animal();          // but we cannot instantiate an abstract class

        // test Fish class with Fish reference
        System.out.println( "Create a Fish object, using a Fish reference" );
        System.out.println( "--------------------------------------------" );
        Fish fish = new Fish( "Nemo" );	// create Fish object
        fish.setName( "Sushi" );	// Animal setName() method
        fish.talk();			// Fish talk() method
        fish.speak();			// Animal speak() method
        fish.fishSpeak();		// Fish speak() method
        fish.say();			// Fish say() method
        System.out.println();

        // but here's the interesting part:
        // we can also use a Animal (base class) reference to manipulate a Fish object
        System.out.println( "Create a Fish object, using an Animal reference" );
        System.out.println( "-----------------------------------------------" );
        ani = new Fish( "Nemo" );	// create Fish object
        ani.setName( "Sushi" );	// Animal setName() method
        ani.talk();			// Fish talk() method
        ani.speak();			// Animal speak() method
        ani.say();			// Fish say() method
        System.out.println();

        // we can also use a Animal (base class) reference to manipulate a Bird object
        System.out.println( "Create a Bird object, using an Animal reference" );
        System.out.println( "-----------------------------------------------" );
        ani = new Bird( "Tweety" );	// create Bird object
        ani.setName( "Big Bird" );	// Animal setName() method
        ani.talk();			// Bird talk() method
        ani.speak();			// Animal speak() method
        ani.say();			// Animal say() method
        System.out.println();

        // we can also use a Animal (base class) reference to manipulate a Cat object
        System.out.println( "Create a Cat object, using an Animal reference" );
        System.out.println( "----------------------------------------------" );
        ani = new Cat( "Fluffy" );	// create Cat object
        ani.setName( "Sylvester" );	// Animal setName() method
        ani.talk();			// Cat talk() method
        ani.speak();			// Animal speak() method
        ani.say();			// Cat say() method
        System.out.println();

        // we can also use a Animal (base class) reference to manipulate a Dog object
        System.out.println( "Create a Dog object, using an Animal reference" );
        System.out.println( "----------------------------------------------" );
        ani = new Dog( "Ginger" );	// create Dog object
        ani.setName( "Snoopy" );	// Animal setName() method
        ani.talk();			// Dog talk() method
        ani.speak();			// Animal speak() method
        ani.speak( "um... woof?" );	// overloaded Animal speak() method
        ani.say();			// Dog say() method
        System.out.println();
    }
}

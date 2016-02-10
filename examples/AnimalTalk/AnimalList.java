/*
                    ***** AnimalList.java *****

OOP example illustrating inheritance and late binding in Java.
Inheritance is used to derive various subclasses from an Animal base class.

This is an application that exercises the class hierarchy functionality.

Author: John M. Weiss, Ph.D.
Class: CSC468 GUI Programming, Spring 2016

Modifications:
*/

import java.util.Random;

public class AnimalList
{
    public static void main( String[] args )
    {
        // get number of animals to generate
        int n = 10;
        if ( args.length > 0 ) n = Integer.parseInt( args[0] );

        // generate an array of random Animal objects
        System.out.println( "\nGenerating random list of " + n + " animals..." );
        Animal [] animals = makeAnimals( n );

        // print list of animals
        System.out.println( "\nAnimal list:" );
        for ( Animal animal : animals )
        {
            System.out.println( "animal type: " + animal );
            animal.talk();
        }
        System.out.println();
    }

    // makeShapes() - returns an array of Animal instances
    static Animal [] makeAnimals( int n )
    {
        // generate an array of Animal references
        Animal [] animals = new Animal [n];

        // generate random Animal objects
        Random rng = new Random();
        for ( int i = 0; i < n; i++ )
        {
            int m = rng.nextInt();
            if ( m < 0 ) m = -m;

            switch ( m % 4 )
            {
                case 0: 	// fish
                    animals[i] = new Fish( "fish" );
                    break;

                case 1: 	// bird
                    animals[i] = new Bird( "bird" );
                    break;

                case 2: 	// cat
                    animals[i] = new Cat( "cat" );
                    break;

                case 3: 	// dog
                    animals[i] = new Dog( "dog" );
                    break;
            }
        }

        // return the array of Animal instances
        return animals;
    }
}

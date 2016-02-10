// Derive a Mammal class from the base Animal class.
// This subclass is still abstract (doesn't implement the Animal talk() method).
public abstract class Mammal extends Animal
{
    public Mammal()
    {
        this( "Anonymous" );
    }
    
    public Mammal( String n )
    {
        super( n );
        System.out.println( "Mammal constructor: " + n );
    }
}

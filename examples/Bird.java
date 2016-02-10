// Derive a Bird class from the base Animal class.
// Note that we are not allowed to override the speak() methods.
public class Bird extends Animal
{
    public Bird()
    {
        this( "Anonymous" );
    }
    
    public Bird( String n )
    {
        super( n );
        System.out.println( "Bird constructor: " + n );
    }

    public void talk()
    {
        System.out.println( name + " sez: cheep cheep! (Bird talk)" );
    }
}

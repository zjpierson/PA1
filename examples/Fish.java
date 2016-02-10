// Derive a Fish class from the base Animal class.
// Note that we are not allowed to override the speak() methods.
public class Fish extends Animal
{
    public Fish()
    {
        this( "Anonymous" );
    }
    
    public Fish( String n )
    {
        super( n );
        System.out.println( "Fish constructor: " + n );
    }

    public void talk()
    {
        System.out.println( name + " sez: blub blub! (Fish talk)" );
    }

    public void say()
    {
        System.out.println( name + " sez: blub blub! (Fish say)" );
    }

    // speak() method is final in base class, and cannot be overridden
    // hence we must use a different method name
    public void fishSpeak()
    {
        System.out.println( name + " sez: blub blub! (Fish speak)" );
    }
}

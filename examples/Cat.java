// Derive a Cat class from the base Mammal class.
// Note that we are not allowed to override the speak() methods.
public class Cat extends Mammal
{
    public Cat()
    {
        this( "Anonymous" );
    }
    
    public Cat( String n )
    {
        super( n );
        System.out.println( "Cat constructor: " + n );
    }

    public void talk()
    {
        System.out.println( name + " sez: meow meow! (Cat talk)" );
    }
    
    public void say()
    {
        System.out.println( name + " sez: meow meow! (Cat say)" );
    }

    // speak() method is final in base class, and cannot be overridden
    // hence we must use a different method name
    public void catSpeak()
    {
        System.out.println( name + " sez: meow meow! (Cat speak)" );
    }
}

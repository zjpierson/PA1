// Derive a Dog class from the base Mammal class.
// Note that we are not allowed to override the speak() methods.
public class Dog extends Mammal
{
    public Dog()
    {
        this( "Anonymous" );
    }
    
    public Dog( String n )
    {
        super( n );
        System.out.println( "Dog constructor: " + n );
    }

    public void talk()
    {
        System.out.println( name + " sez: woof woof! (Dog talk)" );
    }

    public void say()
    {
        System.out.println( name + " sez: woof woof! (Dog say)" );
    }

    // speak() method is final in base class, and cannot be overridden
    // hence we must use a different method name
    public void dogSpeak()
    {
        System.out.println( name + " sez: woof woof! (Dog speak)" );
    }
}

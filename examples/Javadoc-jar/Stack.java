/**
 * File: Stack.java

 * Class to implement a generic pushdown stack.
 * This version uses an array to store the stack.

 * @author John M. Weiss, Ph.D.
 * @version CSC468 GUI Programming, Spring 2016
 */

public class Stack
{
    // private data members
    private int top;
    private Object [] stack;

    /**
     * default no-arg constructor (stack of up to 100 objects)
     */
    public Stack()
    {
        this( 100 );
    }

    /**
     * stack constructor of user-specified size
     * @param size allows user to specify max number of items on stack
     */
    public Stack( int size )
    {
        top = 0;
        stack = new Object [size];
    }

    /**
     * push an item on the stack
     * @param item specifies object to be pushed on the stack
     */
    public void Push( Object item )
    {
        stack[top++] = item;
    }

    /**
     * pop an item off the stack
     * @return object that was popped off the stack
     */
    public Object Pop()
    {
        return stack[--top];
    }

    /**
     * test for empty stack
     * @return true or false
     */
    public boolean Empty()
    {
        return top == 0;
    }
    
    /**
     * main function to test stack class
     */
    public static void main( String [] args )
    {
        // construct a Stack object
        Stack s = new Stack();

        // push some objects on the stack
        // note auto-boxing of primitives (ints and doubles)
        for ( int i = 1; i <= 5; i++ )
            s.Push( i * 10 );
        for ( int i = 1; i <= 5; i++ )
            s.Push( Math.sqrt( i * 10 ) );
        for ( int i = 0; i < 5; i++ )
            s.Push( (char)( 'A' + i ) );
        s.Push( "hello" );
        s.Push( "world" );

        // pop them off and print them
        while ( !s.Empty() )
            System.out.println( s.Pop() );
    }
}

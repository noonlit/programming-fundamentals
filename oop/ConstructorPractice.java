package oop;

public class ConstructorPractice
{
    private int x;
    private int y;
    
    /**
     * Calls full constructor with default value.
     * 
     * @param x
     */
    public ConstructorPractice(int x)
    {
        this(x, 0);
    }
    
    public ConstructorPractice(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

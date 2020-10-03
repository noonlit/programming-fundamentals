package oop;

public class NaturalNumber
{
    private long n;
    
    /*
     * Constructor with param.
     */
    public NaturalNumber(long n) 
    {
        if (n < 0) {
            this.n = 0;
        }
        
        this.n = n;
    }
    
    /*
     * Default/implicit constructor.
     */
    public NaturalNumber()
    {        
        this.n = 0;
    }
    
    /*
     * Getter.
     */
    public long getN()
    {
        return this.n;
    }
    
    /*
     * Setter.
     */
    public void setN(long n)
    {
        this.n = n;
    }
    
    /*
     * Decrement operation with 1 param.
     */
    public NaturalNumber decrement(NaturalNumber n1) 
    {
        NaturalNumber result = new NaturalNumber();
        
        if (this.n > n1.n) {
            result.n = this.n - n1.n;
        } else {
            result.n = 0;
        }
        
        return result;
    }
    
    /*
     * Decrement operation with 2 params.
     */
    public NaturalNumber decrement(NaturalNumber n, NaturalNumber n1)
    {
        NaturalNumber result = new NaturalNumber();
        
        if (n.n > n1.n) {
            result.n = this.n - n1.n;
        } else {
            result.n = 0;
        }
        
        return result;
    }
    
    /*
     * Square root operation with 1 param.
     */
    public static NaturalNumber sqrt(NaturalNumber n1)
    {
        NaturalNumber result = new NaturalNumber();
        
        result.n = (long) Math.sqrt(n1.n);
        
        return result;
    }
    
    /*
     * Highest common factor operation for n1 and n2.
     */
    public NaturalNumber cmmdc(NaturalNumber n1, NaturalNumber n2)
    {
        if (n1.n == 0) {
            return n2;
        }

        if (n2.n == 0) {
            return n1;
        }
        
        long a = n1.n;
        long b = n2.n;
        NaturalNumber result = new NaturalNumber();
        
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }

        result.n = a;

        return result;
    }
    
    /*
     * Does this even have a value?
     */
    public boolean equalsZero()
    {
        return n == 0;
    }
    
    public boolean isPrime()
    {
        /*
         * 0 and 1 are not prime.
         */
        if (n < 2) {
            return false;
        }
        
        /*
         * Even numbers > 2 are not prime.
         */
        if (n > 2 && n % 2 == 0) {
            return false;
        }
        
        /*
         * Check up until sqrt(n), increasing the step by 2 (we're only counting odd nrs)
         */
        for (long d = 3; d*d < n; d += 2) {
            if (n % d == 0) {
                return false;
            }
        }
        
        
        return true;
    }
}

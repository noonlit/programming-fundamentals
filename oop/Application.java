package oop;

public class Application
{
    public static void main(String[] args)
    {
        /*
         * Tests for NaturalNumber class.
         */
        
        /*
         * Diff
         */
        NaturalNumber first = new NaturalNumber(2);
        NaturalNumber second = new NaturalNumber(1);
        NaturalNumber diff = first.decrement(second);
        System.out.println("Difference: " + diff.getN());
        
        /*
         * CMMDC
         */
        NaturalNumber third = new NaturalNumber(36);
        NaturalNumber fourth = new NaturalNumber(48);
        NaturalNumber cmmdc = new NaturalNumber().cmmdc(third, fourth);
        System.out.println("CMMDC: " + cmmdc.getN());
        
        /*
         * Sqrt 
         */
        System.out.println("Sqrt of 4 is: " + NaturalNumber.sqrt(new NaturalNumber(4)).getN());
        
        /*
         * == 0
         */
        System.out.println("Is " + third.getN() + " equal to zero? " + third.equalsZero());
        
        /*
         * Prime
         */
        NaturalNumber fifth = new NaturalNumber(7);
        NaturalNumber sixth = new NaturalNumber(27);
        System.out.println("Is " + fifth.getN() + " prime? " + fifth.isPrime());
        System.out.println("Is " + sixth.getN() + " prime? " + sixth.isPrime());
    }
}

package practice;

import java.util.Random;

public class RandomNumbersGenerator
{
    private int length;
    
    public RandomNumbersGenerator(int length) 
    {
        this.length = length;
    }
    
    /**
     * Generates an array of pseudorandom ints that can take values between 0 and 1000.
     * 
     * Method created for test purposes.
     * 
     * @return
     */
    public int[] generate()
    {
        Random rd = new Random();
       
        int[] arr = new int[this.length];
        
        for (int i = 0; i < this.length; i++) {
           arr[i] = rd.nextInt(1000);
        }
        
        return arr;
    }
}

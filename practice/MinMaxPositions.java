package practice;

import java.util.ArrayList;

/**
 * Se dau n (n > 1) numere întregi: x 1 ,...,x n .
 * 
 * a) metodă care determină toate poziţiile M 1 ,...,M j ale valorii maxime.
 * 
 * b) metodă care determină toate poziţiile m 1 ,...,m k ale valorii minime.
 */
public class MinMaxPositions
{
    public static void main(String args[])
    {
        int[] numbers = { 1, 2, 6, 3, 4, 5, 6, 1, 2 };
        
        int max = getMax(numbers);
        int min = getMin(numbers);
        
        ArrayList<Integer> maxPositions = getPositionsForValue(numbers, max);
        ArrayList<Integer> minPositions = getPositionsForValue(numbers, min);
        
        System.out.println(maxPositions.toString());
        System.out.println(minPositions.toString());
    }
    
    private static int getMax(int[] numbers)
    {
        int max = Integer.MIN_VALUE;
        
        for (int i = 0, n = numbers.length; i < n; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        
        return max;
    }
    
    private static int getMin(int[] numbers)
    {
        int min = Integer.MAX_VALUE;
        
        for (int i = 0, n = numbers.length; i < n; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        
        return min;
    }
    
    private static ArrayList<Integer> getPositionsForValue(int[] numbers, int value)
    {
        ArrayList<Integer> result = new ArrayList<Integer>(numbers.length);
        
        for (int i = 0, n = numbers.length; i < n; i++) {
            if (numbers[i] == value) {
                result.add(i);
            }
        }
        
        return result;
    }
}

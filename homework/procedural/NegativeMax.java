package homework.procedural;

import java.util.Vector;

/**
 * 15. Se dă un vector de numere întregi de lungime n. 
 * 
 * a) metodă care determină cel mai mare număr negativ; 
 * b) metodă care determină poziţiile p1 , p2, ..., pk ale maximului negativ.
 */
public class NegativeMax
{
    public static void main(String[] args)
    {
        int[] numbers = { 1, -7, 3, -2, -2, 6 };

        int negativeMax = getNegativeMax(numbers);

        if (negativeMax < 0) {
            System.out.println("Negative maximum is " + negativeMax);
        } else {
            System.out.println("There is no negative maximum!");
            return;
        }

        Vector<Integer> negativeMaxPositions = getPositionsForItem(numbers, negativeMax);

        System.out.println("Positions for negative maximum are " + negativeMaxPositions.toString());
    }

    /**
     * Identifies the negative max number in the given array.
     * 
     * @param int[] numbers
     * @return int
     */
    private static int getNegativeMax(int[] numbers)
    {
        int negativeMax = Integer.MIN_VALUE;

        for (int i = 0, n = numbers.length; i < n; i++) {
            int current = numbers[i];

            if (current < 0 && current > negativeMax) {
                negativeMax = numbers[i];
            }

        }

        return negativeMax;
    }

    /**
     * Returns a Vector of integers representing the positions 
     * where the given value is found in the given vector.
     * 
     * @param int[] numbers
     * @param int item
     * @return Vector<Integer>
     */
    private static Vector<Integer> getPositionsForItem(int[] numbers, int item)
    {
        Vector<Integer> result = new Vector<Integer>();

        for (int i = 0, n = numbers.length; i < n; i++) {
            if (numbers[i] == item) {
                result.add(i);
            }
        }

        return result;
    }

}

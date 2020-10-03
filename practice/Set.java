package practice;

import java.util.ArrayList;
import java.util.List;

import homework.oop.Sorter;

/**
 * 11. Se dă un vector de numere întregi, A, de lungime n. 
 * a) metodă care transformă un vector A în într-o mulţime.
 */
public class Set
{
    public static void main(String[] args)
    {
        int[] numbers = { 1, 6, 6, 2, 2, 5, 5, 9, 5 };

        /*
         * Vector => Set.
         * 
         * We must ensure uniqueness for these numbers.
         */

        /*
         * Procedure: sort numbers, copy values to new container, skipping adjacent
         * duplicates.
         */
        Sorter sorter = new Sorter(numbers);
        sorter.sort();

        /*
         * Can't use an array for the result, since elements can't be added dynamically.
         * 
         * There is a set implementation in Java, but let's build our own using an
         * ArrayList.
         */
        List<Integer> set = new ArrayList<Integer>();

        /*
         * The first number definitely should be in the set
         */
        int index = 0;
        set.add(numbers[index]);

        int current = numbers[index];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == current) {
                continue;
            }

            index++;
            set.add(numbers[i]);
            current = numbers[i];
        }

        System.out.println("Numbers as set: ");
        System.out.println(set);
    }
}

package practice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Două sau mai multe numere naturale se numesc prietene dacă suma divizorilor
 * coincide (este aceeaşi pentru toate numerele).
 * 
 * a) metodă care determină suma divizorilor unui număr natural.
 * 
 * b) metodă care afişează toate mulţimile de 2 numere prietene din intervalul
 * a, a+1,...,b;
 */
public class FriendlyNumbers
{
    public static void main(String[] args)
    {
        int a = 6;
        int b = 12;
        
        /*
         * Keep numbers in a dictionary,
         * as [sum of divisors => list of numbers with that sum]
         */
        HashMap<Integer, ArrayList<Integer>> friends = new HashMap<Integer, ArrayList<Integer>>();
        
        for (int i = a; i <= b; i++) {
            /*
             * Retrieve element corresponding to the current sum.
             * If none exists, create one.
             */
            int sum = getSumOfDivisors(i);
            
            ArrayList<Integer> friendList = friends.get(sum);
            if (friendList == null) {
                friendList = new ArrayList<Integer>();
            }
            
            /*
             * Add current number to list and put it back in the dictionary.
             */
            friendList.add(i);
            friends.put(sum, friendList);
        }
        
        /*
         * Print friendly nrs.
         */
        for (HashMap.Entry<Integer, ArrayList<Integer>> mapElement : friends.entrySet()) { 
           ArrayList<Integer> friendlyNrs = mapElement.getValue();
           
           if (friendlyNrs.size() < 2) {
               continue;
           }
           
           System.out.println("Friendly numbers:");  
           for (Integer num : friendlyNrs) {              
               System.out.println(num);         
          }
        } 
        
    }

    private static int getSumOfDivisors(int number)
    {
        /*
         * The number itself and 1 are always divisors.
         */
        int result = 1 + number;
        int bound = (int) Math.sqrt(number);

        for (int i = 2; i <= bound; i++) {
            /*
             * Not a divisor.
             */
            if (number % i != 0) {
                continue;
            }
            
            /*
             * If divisor has no pair, add only itself
             */
            if (i == (number / i)) {
                result += i;
                continue;
            }

            /*
             * Add divisor and its pair (on the other side of the sqrt result)
             */
            result += (i + number / i);
        }

        return result;
    }
}

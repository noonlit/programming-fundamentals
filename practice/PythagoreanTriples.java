package practice;

/**
 * Fie n∈N*.
 * 
 * Se cere: metodă care afişează tripletele pitagorice (i,j,k) 
 * cu proprietatea: 3 <= i < j < k <= n.
 */
public class PythagoreanTriples
{
    public static void main(String[] args)
    {
        int n = 10;
        int numbers = n + 1;
        int[] squares = new int[numbers];

        /*
         * Store all squares so we only compute them once.
         */
        for (int i = 0; i < numbers; i++) {
            squares[i] = i * i;
        }

        for (int a = 3; a < numbers; a++) {
            for (int b = a; b < numbers; b++) {
                int cSquared = squares[a] + squares[b];
                /*
                 * If we extract the square root from the sum of squares, and the result is a
                 * whole number, we've found a Pythagorean triple.
                 */
                double c = Math.sqrt(cSquared);

                if (c % 1 == 0) {
                    System.out.println(String.format("Found the triple %d %d %d", a, b, (int) c));
                }
            }
        }
    }
}

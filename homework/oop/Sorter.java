package homework.oop;

/**
 * Se dau n (n>1) numere întregi: x1,...,xn.
 *  
 * a) metoda BubbleSort recursivă de sortare crescătoare;
 */
public class Sorter
{
    int[] numbers = {};
    
    /*
     * Constructor.
     */
    public Sorter(int[] numbers) {
        this.numbers = numbers;
    }
    
    /**
     * Sorts member array.
     * 
     * @param numbers
     * @param n
     */
    public void sort()
    {
        int n = numbers.length;
        
        recursiveBubbleSort(this.numbers, n);
    }
    
    /**
     * Recursively sorts given array using bubble sort.
     * 
     * @param numbers
     * @param n
     */
    private void recursiveBubbleSort(int[] numbers, int n)
    {
        if (n == 1) {
            return;
        }

        // Bubble max to end
        for (int i = 0; i < n - 1; i++)
            if (numbers[i] > numbers[i + 1]) {
                int temp = numbers[i];
                numbers[i] = numbers[i + 1];
                numbers[i + 1] = temp;
            }

        // Recurse for remaining numbers
        recursiveBubbleSort(numbers, n - 1);
    }
}

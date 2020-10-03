package homework.procedural;

import java.util.Arrays;
import java.util.Scanner;

import homework.oop.Sorter;
import practice.RandomNumbersGenerator;

/**
 * 7. Se dau n (n>1) numere întregi: x1,...,xn.
 *  
 * a) metoda BubbleSort recursivă de sortare crescătoare;
 */
public class RecursiveBubbleSort
{
    
    public static void main(String[] args)
    {
        /* Display menu and get user option */
        showMenu();

        Scanner scanner = new Scanner(System.in);
        long opt = readInt(scanner);

        while (opt != 0) {
            switch ((int) opt) {
                case 1:
                    System.out.println("How many numbers?");
                    int n = readInt(scanner);
                    int[] numbers = new int[n];
                    for (int i = 0; i < n; i++) {
                        System.out.println("Input number: ");
                        numbers[i] = readInt(scanner);
                    }
                    
                    System.out.println("Initial numbers: ");
                    System.out.println(Arrays.toString(numbers));
                    
                    //recursiveBubbleSort(numbers, numbers.length);
                    recursiveBubbleSort1(numbers.length - 1, numbers, 1, true);
                    

                    System.out.println("Sorted numbers: ");
                    System.out.println(Arrays.toString(numbers));
                    break;
                default:
                    System.out.println("No clue what you need. Retry.");
            }

            showMenu();
            opt = readInt(scanner);
        }

        System.out.println("Bye!");
        scanner.close();
    }

    /**
     * Reads an int from the keyboard.
     * 
     * @param Scanner scanner
     * @return
     */
    public static int readInt(Scanner scanner)
    {
        try {
            int result = scanner.nextInt();
            return result;
        } catch (Exception e) {
            System.out.println("Not a number. Try again.");
            return readInt(scanner);
        }
    }

    /**
     * Shows application menu.
     */
    public static void showMenu()
    {
        System.out.println("********************");
        System.out.println("1. Sort numbers:");
        System.out.println("0. Exit");
        System.out.println("********************");
    }
    
    /**
     * Recursively sorts given array using bubble sort.
     * 
     * @param int[] numbers
     * @param int n
     */
    public static void recursiveBubbleSort(int numbers[], int n)
    {
        if (n == 1) {
            return;
        }

        // Bubble max to end
        for (int i = 0; i < n - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                int temp = numbers[i];
                numbers[i] = numbers[i + 1];
                numbers[i + 1] = temp;
            }
        }

        // Recurse for remaining numbers
        recursiveBubbleSort(numbers, n - 1);
    }
    
    /**
     * Recursively sorts given array using bubble sort (professor version)
     * 
     * @param int[] numbers
     * @param int n
     */
    public static void recursiveBubbleSort1(int n, int[] numbers, int i, boolean isOK)
    {
        if (i < n) {
            if (numbers[i] > numbers[i + 1]) {
                // swap
                int temp = numbers[i];
                numbers[i] = numbers[i + 1];
                numbers[i + 1] = temp;
                
                isOK = false;
            }
            
            recursiveBubbleSort1(n, numbers, i+1, isOK);
        }
        
        if (!isOK) {
            recursiveBubbleSort1(n, numbers, 1, true);
        }
    }
}

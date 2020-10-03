package homework.oop;

import java.util.Arrays;
import java.util.Scanner;

public class Application
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
                    
                    Sorter sorter = new Sorter(numbers);
                    sorter.sort();

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
}

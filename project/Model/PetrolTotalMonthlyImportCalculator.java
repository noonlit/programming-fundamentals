package project.Model;

import java.util.ArrayList;

public class PetrolTotalMonthlyImportCalculator
{
    /*
     * Calculeaza totalul importului lunar de petrol.
     * 
     * @param ArrayList<Petrol>
     * @return int
     */
    public static long calculate(ArrayList<Petrol> elements)
    {
        long sum = 0;
        
        for (Petrol element : elements) {
            sum += element.getImportLunar();
        }
        
        return sum;
    }
}

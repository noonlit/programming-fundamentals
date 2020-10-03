package project.View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import project.Helper.PercentageCalculator;
import project.Model.Petrol;

/**
 * Afiseaza date despre importul de petrol.
 */
public class Report
{
    /**
     * Afiseaza datele sortate ascendent dupa numele tarii.
     * 
     * @param ArrayList<Petrol> data
     */
    public static void showData(ArrayList<Petrol> date)
    {
        String[] coloane = { "Nume tara", "Import lunar" };
        String formatCapTabel = "|%-28s|%-15s|";

        /*
         * Afisam capul de tabel.
         */
        int lineLength = 46;
        System.out.println("=".repeat(lineLength));
        System.out.println(String.format(formatCapTabel, coloane[0], coloane[1]));
        System.out.println("=".repeat(lineLength));
        
        /*
         * Afisam datele.
         */
        String formatDate = "|%-28s|%15s|";
        for (Petrol rand : date) {
            String numeTara = rand.getNumeTara();
            String importLunar = Long.toString(rand.getImportLunar());
            
            System.out.println(String.format(formatDate, numeTara, importLunar));
            System.out.println("-".repeat(lineLength));
        }
    }

    /**
     * Afiseaza datele sortate descendent dupa importul de petrol.
     * 
     * @param ArrayList<Petrol> date
     * @param long totalImport
     */
    public static void showData(ArrayList<Petrol> date, long totalImport)
    {
        String[] coloane = { "Nume tara", "Import lunar", "Procent total din petrol" };     
        String formatCapTabel = "|%-28s|%-15s|%-25s|";

        /*
         * Afisam capul de tabel.
         */
        int lineLength = 72;
        System.out.println("=".repeat(lineLength));
        System.out.println(String.format(formatCapTabel, coloane[0], coloane[1], coloane[2]));
        System.out.println("=".repeat(lineLength));
        
        /*
         * Afisam datele.
         */
        String formatDate = "|%-28s|%15s|%25s|";
        for (Petrol rand : date) {
            double procentTotal = PercentageCalculator.calculatePercentage((double) rand.getImportLunar(), (double) totalImport);
            
            String numeTara = rand.getNumeTara();
            String importLunar = Long.toString(rand.getImportLunar());
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(4);
            String procent = df.format(procentTotal) + "%";
            
            System.out.println(String.format(formatDate, numeTara, importLunar, procent));
            System.out.println("-".repeat(lineLength));
        }
        
        String totalImportFormatat = String.format("%,d", totalImport);
        System.out.println(String.format("%s: %s", "Total import petrol", totalImportFormatat));
        System.out.println("-".repeat(lineLength));
    }
}

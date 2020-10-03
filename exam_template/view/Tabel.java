package exam_template.view;

import java.text.DecimalFormat;
import java.util.ArrayList;

import exam_template.model.Calculator;
import exam_template.model.Entitate;
import exam_template.model.Haina;
import project.Helper.PercentageCalculator;
import project.Model.Petrol;

public class Tabel
{
    public static void afiseazaLista(ArrayList<Haina> haine)
    {
        if (haine == null) {
            System.out.println("Nu se poate afisa rezultatul!");
        }
  
        String[] coloane = { "Denumire", "Tip", "Disponibile", "PretUnit", "Valoare disponibila" };
        String formatCapTabel = "|%-20s|%-10s|%-15s|%-10s|%-20s|";

        /*
         * Afisam capul de tabel.
         */
        int lineLength = 81;
        System.out.println("=".repeat(lineLength));
        System.out.println(String.format(formatCapTabel, coloane[0], coloane[1], coloane[2], coloane[3], coloane[4]));
        System.out.println("=".repeat(lineLength));
        
        /*
         * Afisam datele.
         */
        String formatDate = "|%-20s|%10s|%15s|%10s|%20s|";
        int total = 0;
        for (Haina rand : haine) {
            int disponibile = rand.getStocInitial() - rand.getVandute();
            
            /*
             * Nu afisam produsele care nu mai sunt disponibile.
             */
            if (disponibile == 0) {
                continue;
            }
            
            String denumire = rand.getDenumire();
            String tip = rand.getTip();
            int pretUnit = rand.getPretUnitar();
            int valoareDisponibila = disponibile * rand.getPretUnitar();
            total += valoareDisponibila;
  
            System.out.println(String.format(formatDate, denumire, tip, disponibile, pretUnit, valoareDisponibila));
            System.out.println("-".repeat(lineLength));

        }
        
        System.out.println(String.format("%70s %d lei", "Total:", total));
    }
    
    public static void afiseazaListaTip(ArrayList<Haina> haine)
    {
        if (haine == null) {
            System.out.println("Nu se poate afisa rezultatul!");
        }
        
        
        String[] coloane = { "Denumire", "StocInit", "StocInitValoric", "Vandute", "VanduteValoric", "ProcVanzari" };
        String formatCapTabel = "|%-20s|%-10s|%-15s|%-10s|%-20s|%-18s|";

        /*
         * Afisam capul de tabel.
         */
        int lineLength = 100;
        System.out.println("=".repeat(lineLength));
        System.out.println(String.format(formatCapTabel, coloane[0], coloane[1], coloane[2], coloane[3], coloane[4], coloane[5]));
        System.out.println("=".repeat(lineLength));
        
        /*
         * Afisam datele.
         */
        String formatDate = "|%-20s|%10s|%15s|%10s|%20s|%18s";
        int totalStocInitialValoric = 0;
        int totalVanduteValoric = 0;
        for (Haina rand : haine) {            
            String denumire = rand.getDenumire();
            int stocInitial = rand.getStocInitial();
            int pretUnit = rand.getPretUnitar();
            int stocInitialValoric = stocInitial * pretUnit;
            totalStocInitialValoric += stocInitialValoric;
            int vandute = rand.getVandute();
            int vanduteValoric = vandute * pretUnit;
            totalVanduteValoric += vanduteValoric;
            double procentajVanzari = Calculator.calculeazaProcent(vanduteValoric, stocInitialValoric);
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            String procent = df.format(procentajVanzari);
            
            System.out.println(String.format(formatDate, denumire, stocInitial, stocInitialValoric, vandute, vanduteValoric, procent));
            System.out.println("-".repeat(lineLength));
        }
        
        
        System.out.print(String.format("%38s %d lei", "Total:", totalStocInitialValoric));
        System.out.print(String.format("%19s %d lei", "", totalVanduteValoric));
        
        double procentTotal = PercentageCalculator.calculatePercentage((double) totalVanduteValoric, (double) totalStocInitialValoric);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        String procent = df.format(procentTotal) + "%";
        
        System.out.println(String.format("%12s %s", "Proc:", procent));
    }
}

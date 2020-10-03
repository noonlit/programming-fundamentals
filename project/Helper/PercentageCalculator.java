package project.Helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PercentageCalculator
{
    /*
     * Calculeaza procentajul din total reprezentat de qt.
     * 
     * Returneaza un double cu 2 cifre dupa virgula.
     * 
     * @param double qt Cantitatea pentru care calculam procentajul
     * @param double total Cantitatea la care ne raportam pentru a calcula procentajul
     * 
     * @return double
     */
    public static double calculatePercentage(double qt, double total) {
        double result = qt * 100 / total;
        
        BigDecimal bd = new BigDecimal(result).setScale(4, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}

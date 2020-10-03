package exam_template.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator
{    
    public static double calculeazaProcent(double cantitate, double total) {
        double rezultat = cantitate * 100 / total;
        
        BigDecimal bd = new BigDecimal(rezultat).setScale(4, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}

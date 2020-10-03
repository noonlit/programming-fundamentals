package project.Model;

import java.util.Comparator;

public class PetrolImportComparator implements Comparator<Petrol>
{
    /*
     * Compara 2 instante de Petrol pe baza importului lunar de petrol.
     * 
     * Sortarea este descendenta.
     * 
     * @param Petrol o1
     * @param Petrol o2
     */
    @Override
    public int compare(Petrol o1, Petrol o2)
    {
        return (int) (o2.getImportLunar() - o1.getImportLunar());
    }
}

package project.Model;

import java.util.Comparator;

public class PetrolCountryComparator implements Comparator<Petrol>
{
    /*
     * Compara 2 instante de Petrol pe baza numelui tarii.
     * 
     * Sortarea este ascendenta.
     * 
     * @param Petrol o1
     * @param Petrol o2
     */
    @Override
    public int compare(Petrol o1, Petrol o2)
    {
        return o1.getNumeTara().compareTo(o2.getNumeTara());
    }
}

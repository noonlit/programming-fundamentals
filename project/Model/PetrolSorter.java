package project.Model;

import java.util.ArrayList;

public class PetrolSorter
{
    /*
     * Sorteaza obiecte de tip Petrol ascendent dupa numele tarii.
     * 
     * Foloseste selection sort.
     * 
     * @param ArrayList<Petrol> elemente
     * @return ArrayList<Petrol> Petrol
     */
    public ArrayList<Petrol> sortByCountryNameAscending(ArrayList<Petrol> elemente)
    {
        int n = elemente.size(); 
        
        for (int i = 0; i < n-1; i++) { 
            /*
             * Indicele la care se gaseste cel mai mic element din array.
             */
            int indiceMinim = i;
            
            for (int j = i+1; j < n; j++) {
                String valoareCurenta = elemente.get(j).getNumeTara();
                String valoareMinima = elemente.get(indiceMinim).getNumeTara();
                if (valoareCurenta.compareTo(valoareMinima) < 0) { 
                    indiceMinim = j; 
                }
            }
            
            /*
             * Punem elementul minim in locul elementului curent.            
             */
            Petrol temp = elemente.get(indiceMinim); 
            elemente.set(indiceMinim, elemente.get(i)); 
            elemente.set(i, temp); 
        } 
        
        return elemente;
    }
    
    /*
     * Sorteaza obiecte de tip Petrol descendent dupa importul lunar de petrol.
     * 
     * Foloseste selection sort.
     * 
     * @param ArrayList<Petrol> elemente
     * @return ArrayList<Petrol> Petrol
     */
    public ArrayList<Petrol> sortByOilImportDescending(ArrayList<Petrol> elemente)
    {
        int n = elemente.size(); 
        
        for (int i = 0; i < n-1; i++) { 
            /*
             * Indicele la care se gaseste cel mai mare element din array.
             */
            int indiceMaxim = i;
            
            for (int j = i+1; j < n; j++) {
                long valoareCurenta = elemente.get(j).getImportLunar();
                long valoareMaxima = elemente.get(indiceMaxim).getImportLunar();
                if (valoareMaxima < valoareCurenta) { 
                    indiceMaxim = j; 
                }
            }
            
            /*
             * Punem elementul maxim in locul elementului curent.          
             */
            Petrol temp = elemente.get(indiceMaxim); 
            elemente.set(indiceMaxim, elemente.get(i)); 
            elemente.set(i, temp); 
        } 
        
        return elemente;
    }  
}

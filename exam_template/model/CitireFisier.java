package exam_template.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CitireFisier
{
    /*
     * Numarul coloanei pe care se afla informatia specifica proprietatii.
     */
    private final static int INDEX_DENUMIRE = 0;
    private final static int INDEX_TIP = 1;
    private final static int INDEX_STOC_INITIAL = 2;
    private final static int INDEX_VANDUTE = 3;
    private final static int INDEX_PRET_UNIT = 4;
    /*
     * Path-ul catre fisierul cu date.
     */
    private final static String PATH = "C:\\labs\\programming_fundamentals\\src\\exam_template\\Haine.txt";
    
    public static ArrayList<Haina> citeste()
    {        
        /*
         * Initializam o lista care sa stocheze local datele citite din fisier.
         */
        ArrayList<Haina> elemente = new ArrayList<Haina>();

        try {
            /*
             * Instantiem un obiect cu ajutorul caruia vom citi date din fisier.
             */
            BufferedReader cititor = new BufferedReader(new FileReader(PATH));

            String linie = "";
            while ((linie = cititor.readLine()) != null) {
                String[] tokens = linie.split(",");
                String denumire = tokens[INDEX_DENUMIRE];
                String tip = tokens[INDEX_TIP];
                int stocInitial = Integer.valueOf(tokens[INDEX_STOC_INITIAL]);
                int vandute = Integer.valueOf(tokens[INDEX_VANDUTE]);
                int pretUnitar = Integer.valueOf(tokens[INDEX_PRET_UNIT]);
                
                Haina element = new Haina(denumire, tip, stocInitial, vandute, pretUnitar);
                
                elemente.add(element);
            }

            cititor.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return elemente;
    }
}

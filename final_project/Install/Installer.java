package final_project.Install;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import final_project.Model.Proprietate;
import final_project.Repository.*;
import final_project.Serviciu.ServiciuConvertireDate;

public class Installer
{
    /*
     * Path-ul catre fisierul cu date.
     */
    private final static String PATH = "C:\\labs\\programming_fundamentals\\src\\final_project\\Install\\proprietati.txt";

    /*
     * Numarul coloanei pe care se afla informatia specifica proprietatii.
     */
    private final static int INDEX_COD     = 0;
    private final static int INDEX_TIP     = 1;
    private final static int INDEX_CAMERE  = 2;
    private final static int INDEX_CARTIER = 3;
    private final static int INDEX_ADRESA  = 4;
    private final static int INDEX_PRET    = 5;

    /**
     * Insereaza datele initiale pentru aplicatie in baza de date.
     * 
     * @param String[] args
     */
    public static void main(String[] args)
    {
        /*
         * Citim datele din fisier.
         */
        ArrayList<Proprietate> proprietati = readFromFile();

        /*
         * Stergem datele din baza de date.
         */
        ProprietateRepository repo = new ProprietateRepository();
        ProprietateInchiriataRepository repoInchirieri = new ProprietateInchiriataRepository();
        try {
            repo.stergeToate();

            /*
             * Inseram datele din fisier in baza de date.
             */
            for (Proprietate proprietate : proprietati) {
                repo.insereaza(proprietate);
            }
            
            /*
             * Generam date de test pentru inchirieri. 
             */
            int i = 1;
            for (Proprietate proprietate : proprietati) {
                UUID random = UUID.randomUUID();
                int number = (int) random.getLeastSignificantBits();
                
                if (number % 2 == 0) {
                    continue;
                }
                
                String inceputString = String.format("2020-0%s-0%s", i, i);
                LocalDate inceput = ServiciuConvertireDate.dinStringInLocalDate(inceputString);      
                LocalDate sfarsit = proprietate.getTip().equals("apartament") ? inceput.plusMonths(i) : inceput.plusDays(i);
                             
                repoInchirieri.inchiriazaProprietate(
                        proprietate.getCod(), 
                        String.format("andra@%s.com", random.toString().substring(0, 6)), 
                        inceput,
                        sfarsit
                        );
                
                i++;
            }
            
            System.out.println("Am instalat datele originale.");
        } catch (Exception e) {
            System.out.println("Nu am putut instala datele originale.");
            System.out.println(e.getMessage());
        }    
    }

    /**
     * Creeaza o lista de proprietati pe baza unui fisier.
     * 
     * @return ArrayList<Proprietati>
     */
    private static ArrayList<Proprietate> readFromFile()
    {
        ArrayList<Proprietate> proprietati = new ArrayList<Proprietate>();
        String linie = "";

        try {
            BufferedReader cititor = new BufferedReader(new FileReader(PATH));

            while ((linie = cititor.readLine()) != null) {
                /*
                 * Construim o proprietate din linia curenta din fisier.
                 */
                String[] tokens = linie.split(",");
                Proprietate proprietate = new Proprietate(null, tokens[INDEX_COD], tokens[INDEX_TIP],
                        Integer.parseInt(tokens[INDEX_CAMERE]), tokens[INDEX_CARTIER], tokens[INDEX_ADRESA],
                        Integer.parseInt(tokens[INDEX_PRET]));

                proprietati.add(proprietate);
            }
            cititor.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return proprietati;
    }
}

package project.Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import project.Model.Petrol;
import project.Model.PetrolCountryComparator;
import project.Model.PetrolImportComparator;
import project.Model.PetrolSorter;
import project.Model.PetrolTotalMonthlyImportCalculator;
import project.View.Report;

public class Application
{
    /*
     * Path-ul catre fisierul cu date.
     */
    private final static String PATH = "C:\\labs\\programming_fundamentals\\src\\project\\petrol_scurt.txt";

    /*
     * Constante care reprezinta optiunile de citire a datelor.
     */
    private final static int CITIRE_FISIER    = 1;
    private final static int CITIRE_TASTATURA = 2;

    /*
     * Constante care reprezinta optiunile de sortare a datelor din fisiere.
     */
    private final static int SORTARE_ASCENDENTA_NUME_TARA        = 1;
    private final static int SORTARE_DESCENDENTA_IMPORT          = 2;
    private final static int SORTARE_ASCENDENTA_NUME_TARA_CUSTOM = 3;
    private final static int SORTARE_DESCENDENTA_IMPORT_CUSTOM   = 4;

    public static void main(String[] args)
    {
        /*
         * Preluam optiunea utilizatorului cu privire la sursa datelor (fisier vs. tastatura).
         */
        showMenu();
        
        Scanner scanner = new Scanner(System.in);
        long opt = readLong(scanner);
        System.out.println(String.format("Ai ales %d.", opt));

        /*
         * Citim datele in modul indicat de utilizator.
         */
        ArrayList<Petrol> date = new ArrayList<Petrol>();
        boolean optionIsValid = false;
        while (!optionIsValid) {
            switch ((int) opt) {
                case CITIRE_FISIER:
                    date = readFromFile(PATH);
                    optionIsValid = true;
                    break;
                case CITIRE_TASTATURA:
                    date = readFromKeyboard(scanner);
                    optionIsValid = true;
                    break;
                default:
                    System.out.println("Optiune invalida.");
            }
            
            if (!optionIsValid) {
                showMenu();
                opt = readLong(scanner);
            }
        }

        /*
         * Deoarece e posibil sa se ceara sa afisam ce procentaj din importul lunar
         * total reprezinta importul fiecarei tari, vom avea nevoie de suma totala a
         * importurilor.
         */
        long total = 0;

        /*
         * Instantiem un obiect care va efectua sortarea in 2 din 4 cazuri.
         */
        PetrolSorter sorter = new PetrolSorter();

        /*
         * Ii aratam utilizatorului meniul de optiuni.
         */
        showSortingMenu();
        opt = readLong(scanner);
        System.out.println(String.format("Ai ales %d.", opt));

        while (opt != 0) {
            switch ((int) opt) {
                case SORTARE_ASCENDENTA_NUME_TARA:
                    Collections.sort(date, new PetrolCountryComparator());
                    Report.showData(date);
                    break;
                case SORTARE_DESCENDENTA_IMPORT:
                    Collections.sort(date, new PetrolImportComparator());
                    total = PetrolTotalMonthlyImportCalculator.calculate(date);
                    Report.showData(date, total);
                    break;
                case SORTARE_ASCENDENTA_NUME_TARA_CUSTOM:
                    date = sorter.sortByCountryNameAscending(date);
                    Report.showData(date);
                    break;
                case SORTARE_DESCENDENTA_IMPORT_CUSTOM:
                    date = sorter.sortByOilImportDescending(date);
                    total = PetrolTotalMonthlyImportCalculator.calculate(date);
                    Report.showData(date, total);
                    break;
                default:
                    System.out.println("Optiune invalida.");
            }

            showSortingMenu();
            opt = readLong(scanner);
        }

        scanner.close();
        System.out.println("Executie incheiata.");
    }

    /**
     * Construieste o lista de tari + import lunar de petrol asociat.
     * 
     * @return ArrayList<Petrol>
     */
    private static ArrayList<Petrol> readFromFile(String path)
    {
        /*
         * Initializam o lista care sa stocheze local datele citite din fisier.
         */
        ArrayList<Petrol> elemente = new ArrayList<Petrol>();

        try {
            /*
             * Instantiem un obiect cu ajutorul caruia vom citi date din fisier.
             */
            BufferedReader cititor = new BufferedReader(new FileReader(path));

            String linie = "";
            while ((linie = cititor.readLine()) != null) {
                /*
                 * Instantiem un tokenizer ca sa extragem separat numele tarii si importul de
                 * petrol de pe linia citita.
                 */
                StringTokenizer tk = new StringTokenizer(linie, ",");

                String tara = tk.nextToken();
                long importLunar = Long.parseLong(tk.nextToken());

                /*
                 * Construim un obiect de tip Petrol din datele citite din fisier si il adaugam
                 * listei de elemente similare.
                 */
                Petrol element = new Petrol(tara, importLunar);
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
    
    /**
     * Construieste o lista de tari + import lunar de petrol asociat.
     * 
     * @return ArrayList<Petrol>
     */
    private static ArrayList<Petrol> readFromKeyboard(Scanner scanner)
    {
        System.out.println("Cate tari?");

        long n = readLong(scanner);
        ArrayList<Petrol> tari = new ArrayList<Petrol>((int) n);

        for (int i = 0; i < n; i++) {
            Petrol petrol = new Petrol();

            System.out.println("Numele tarii: ");
            String numeTara = readLine(scanner);
            petrol.setNumeTara(numeTara);

            System.out.println("Importul de petrol: ");
            long importLunar = readLong(scanner);
            petrol.setImportLunar(importLunar);

            tari.add(petrol);
        }

        return tari;
    }

    /**
     * Citeste un sir de la tastatura.
     * 
     * @param Scanner scanner
     * @return String
     */
    private static String readLine(Scanner scanner)
    {
        try {
            String rezultat = scanner.next();
            return rezultat;
        } catch (Exception e) {
            System.out.println("Introdu un sir (cuvant).");
            scanner.next();
            return readLine(scanner);
        }
    }

    /**
     * Citeste un numar de la tastatura.
     * 
     * @param Scanner scanner
     * @return long
     */
    private static long readLong(Scanner scanner)
    {
        try {
            long rezultat = scanner.nextInt();
            return rezultat;
        } catch (Exception e) {
            System.out.println("Introdu un numar.");
            scanner.next();
            return readLong(scanner);
        }
    }

    /*
     * Afiseaza meniul principal al aplicatiei.
     */
    private static void showMenu()
    {
        System.out.println("********************");
        System.out.println("Datele sa fie citite de la tastatura sau din fisier?");
        System.out.println(String.format("%s. Din fisier.", CITIRE_FISIER));
        System.out.println(String.format("%s. De la tastatura.", CITIRE_TASTATURA));
        System.out.println("********************");
    }

    /**
     * Afiseaza meniul de sortari al aplicatiei.
     */
    private static void showSortingMenu()
    {
        System.out.println("********************");
        System.out.println("Optiunile de sortare sunt urmatoarele:");
        System.out.println(String.format("%s. Afisare date sortate alfabetic dupa tara (cu Comparator).",
                SORTARE_ASCENDENTA_NUME_TARA));
        System.out.println(
                String.format("%s. Afisare date sortate descrescator dupa importul lunar de petrol (cu Comparator).",
                        SORTARE_DESCENDENTA_IMPORT));
        System.out.println(String.format("%s. Afisare date sortate alfabetic dupa tara (fara Comparator).",
                SORTARE_ASCENDENTA_NUME_TARA_CUSTOM));
        System.out.println(
                String.format("%s. Afisare date sortate descrescator dupa importul lunar de petrol (fara Comparator).",
                        SORTARE_DESCENDENTA_IMPORT_CUSTOM));
        System.out.println("0. Terminare program.");
        System.out.println("********************");
    }
}

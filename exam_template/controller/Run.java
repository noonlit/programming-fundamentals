package exam_template.controller;

import java.util.ArrayList;
import java.util.Scanner;
import exam_template.model.CitireFisier;
import exam_template.model.Haina;
import exam_template.serviciu.ServiciuFiltrare;
import exam_template.view.Tabel;

public class Run
{
    public static final int AFISARE_PRODUSE = 1;
    public static final int AFISARE_TIP = 2;
    
    /*
     * Firul principal de executie.
     */
    public static void main(String[] args)
    {
        afiseazaMeniuPrincipal();

        /*
         * Preluam optiunea utilizatorului si pregatim executia.
         */
        Scanner scanner = new Scanner(System.in);
        long opt = citesteLong(scanner);
        System.out.println(String.format("Ai ales %d.", opt));

        ArrayList<Haina> haine = CitireFisier.citeste();
        ServiciuFiltrare serviciuFiltrare = new ServiciuFiltrare();
        
        /*
         * Executam optiunea utilizatorului.
         */
        while (opt != 0) {
            try {
                executaOptiune((int) opt, scanner, haine, serviciuFiltrare);
                
                System.out.println("Doriti sa mai efectuati si alte operatii? (D/N)");
                String continuare = citesteLinie(scanner);
                
                while (!continuare.equals("D") && !continuare.equals("N")) {
                    System.out.println("Optiunile sunt D (da) si N (nu)");
                    continuare = citesteLinie(scanner);
                }
                
                if (continuare.equals("N")) {
                    break;
                }
                
                afiseazaMeniuPrincipal();
                opt = citesteLong(scanner);
            } catch (Exception e) {
                System.err.println(e.getStackTrace());
                break;
            }    
        }

        scanner.close();
        System.out.println("Executie incheiata.");
    }
    
    /**
     * Executa o optiune data de utilizator de la tastatura.
     * 
     * @param int optiune
     * @param Scanner scanner
     * @param ServiciuFiltrare serviciuInterogare
     * @throws Exception 
     */
    public static void executaOptiune(
            int optiune, 
            Scanner scanner, 
            ArrayList<Haina> haine, 
            ServiciuFiltrare serviciuFiltrare
            ) throws Exception
    {
        switch (optiune) {
            case AFISARE_PRODUSE:                
                Tabel.afiseazaLista(haine);
                break;
            case AFISARE_TIP:
                System.out.println("Dupa ce tip doriti sa filtrati?");
                String tip = citesteLinie(scanner);
                
                ArrayList<Haina> rezultat = serviciuFiltrare.filtreaza(haine, tip);
                Tabel.afiseazaListaTip(rezultat);
                break;
            default:
                System.out.println("Optiune invalida.");
                
        }
    }

    /**
     * Citeste un sir de la tastatura.
     * 
     * @param Scanner scanner
     * @return String
     */
    private static String citesteLinie(Scanner scanner)
    {
        try {
            String rezultat = scanner.next();
            return rezultat;
        } catch (Exception e) {
            System.out.println("Introduceti un sir (cuvant).");
            scanner.next();
            return citesteLinie(scanner);
        }
    }

    /**
     * Citeste un numar de la tastatura.
     * 
     * @param Scanner scanner
     * @return long
     */
    private static long citesteLong(Scanner scanner)
    {
        try {
            long rezultat = scanner.nextInt();
            return rezultat;
        } catch (Exception e) {
            System.out.println("Introduceti un numar.");
            scanner.next();
            return citesteLong(scanner);
        }
    }

    /**
     * Afiseaza meniul aplicatiei.
     * 
     * @return void
     */
    private static void afiseazaMeniuPrincipal()
    {
        int lineLength = 80;
        
        System.out.println("=".repeat(lineLength));
        
        String optiune = String.format("%s. Afisare produse", AFISARE_PRODUSE);
        System.out.println(optiune);
        
        optiune = String.format("%s. Afisare vanzari pentru un tip", AFISARE_TIP);
        System.out.println(optiune);
        
        System.out.println("\n0. Incheiere program");

        System.out.println("=".repeat(lineLength));

    }
}

package final_project.Controller;

import java.time.LocalDate;
import java.util.Scanner;
import final_project.Model.*;
import final_project.Serviciu.*;
import final_project.View.*;

public class Main
{
    /*
     * CRUD proprietati.
     */
    public static final int AFISARE_PROPRIETATI    = 1;
    public static final int ADAUGARE_PROPRIETATE   = 2;
    public static final int STERGERE_PROPRIETATE   = 3;
    public static final int MODIFICARE_PROPRIETATE = 4;
    
    /*
     * CRUD proprietati inchiriate.
     */
    public static final int AFISARE_PROPRIETATI_INCHIRIATE_LUNA_CURENTA = 5;
    public static final int AFISARE_PROPRIETATI_INCHIRIATE_LUNA         = 6;
    public static final int AFISARE_PROPRIETATI_INCHIRIATE_AN           = 7;
    public static final int AFISARE_PROPRIETATI_INCHIRIATE_INTERVAL     = 8;
    public static final int AFISARE_PROPRIETATI_DISPONIBILE_INTERVAL    = 9;
    public static final int INCHIRIERE_PROPRIETATE                      = 10;
    public static final int ANULARE_INCHIRIERE                          = 11;
    
    /*
     * Informatii aditionale.
     */
    public static final int AFISARE_VENIT_PROPRIETATE = 12;
    public static final int AFISARE_VENIT_TOTAL       = 13;
    
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

        ServiciuInterogare serviciuInterogare = new ServiciuInterogare();
        
        /*
         * Executam optiunea utilizatorului.
         */
        while (opt != 0) {
            try {
                executaOptiune((int) opt, scanner, serviciuInterogare);
                
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
                System.err.println(e.getMessage());
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
     * @param ServiciuInterogare serviciuInterogare
     * @throws Exception 
     */
    public static void executaOptiune(int optiune, Scanner scanner, ServiciuInterogare serviciuInterogare) throws Exception
    {
        switch (optiune) {
            case AFISARE_PROPRIETATI:
                Tabel.afiseazaProprietati(serviciuInterogare.cautaToateProprietatile(100, 0));
                break;
                
            case ADAUGARE_PROPRIETATE:
                Proprietate proprietate = new Proprietate();
                System.out.println("Introduceti codul proprietatii (de ex.: CVM16A2)");
                String codProprietate = citesteLinie(scanner);
                proprietate.setCod(codProprietate);
                
                System.out.println("Introduceti tipul proprietatii (de ex.: casa vacanta)");
                String optiuneProprietate = citesteLinie(scanner);
                proprietate.setTip(optiuneProprietate);
                
                System.out.println("Introduceti numarul de camere (de ex.: 3)");
                int camere = (int) citesteLong(scanner);
                proprietate.setCamere(camere);
                
                System.out.println("Introduceti cartierul (de ex.: Micro 19)");
                optiuneProprietate = citesteLinie(scanner);
                proprietate.setCartier(optiuneProprietate);
                
                System.out.println("Introduceti adresa (de ex.: Strada Armata Poporului nr 20)");
                optiuneProprietate = citesteLinie(scanner);
                proprietate.setAdresa(optiuneProprietate);
                
                System.out.println("Introduceti pretul in lei (de ex.: 180)");
                Long pret = citesteLong(scanner);
                proprietate.setPret(pret);

                Rezultat.afiseazaRezultatBoolean(serviciuInterogare.adaugaProprietate(proprietate));

                proprietate = serviciuInterogare.cauta(codProprietate);
                Tabel.afiseazaProprietate(proprietate);
                break;
                
            case MODIFICARE_PROPRIETATE:
                System.out.println("Introduceti codul proprietatii (de ex.: CVM16A2)");
                optiuneProprietate = citesteLinie(scanner);
                
                System.out.println("Introduceti noul pret (de ex.: 180)");
                pret = citesteLong(scanner);

                Rezultat.afiseazaRezultatBoolean(serviciuInterogare.modificaProprietate(optiuneProprietate, pret));
                break;

            case STERGERE_PROPRIETATE:              
                System.out.println("Introduceti codul proprietatii (de ex.: CVM16A2)");
                optiuneProprietate = citesteLinie(scanner);
                
                Rezultat.afiseazaRezultatBoolean(serviciuInterogare.stergeProprietate(optiuneProprietate));
                break;
                
            case AFISARE_PROPRIETATI_INCHIRIATE_LUNA_CURENTA:
                Tabel.afiseazaProprietatiInchiriate(serviciuInterogare.cautaToateProprietatileInchiriateLunaCurenta(100, 0));
                break;
                
            case AFISARE_PROPRIETATI_INCHIRIATE_LUNA:
                System.out.println("Introduceti luna pentru care doriti sa se afiseze datele (1-12)");
                int optiuneLuna = (int) citesteLong(scanner);
                Tabel.afiseazaProprietatiInchiriate(serviciuInterogare.cautaToateProprietatileInchiriateLuna(optiuneLuna, 100, 0));
                
                break;
                
            case AFISARE_PROPRIETATI_DISPONIBILE_INTERVAL:
                System.out.println("Introduceti inceputul intervalului (format YYYY-MM-DD)");              
                String optiuneInceput = citesteLinie(scanner);
                LocalDate inceput = ServiciuConvertireDate.dinStringInLocalDate(optiuneInceput);             
                
                System.out.println("Introduceti sfarsitul intervalului (format YYYY-MM-DD)");
                String optiuneSfarsit = citesteLinie(scanner);
                LocalDate sfarsit = ServiciuConvertireDate.dinStringInLocalDate(optiuneSfarsit);
                
                Tabel.afiseazaProprietati(serviciuInterogare.cautaToateProprietatileDisponibileInterval(inceput, sfarsit, 100, 0));
                break;
                
            case AFISARE_PROPRIETATI_INCHIRIATE_AN:
                Tabel.afiseazaProprietatiInchiriate(serviciuInterogare.cautaToateProprietatileInchiriateAnCurent(100, 0));              
                break;
                
            case AFISARE_PROPRIETATI_INCHIRIATE_INTERVAL:
                System.out.println("Introduceti inceputul intervalului (format YYYY-MM-DD)");
                optiuneInceput = citesteLinie(scanner);
                inceput = ServiciuConvertireDate.dinStringInLocalDate(optiuneInceput);
                
                System.out.println("Introduceti sfarsitul intervalului (format YYYY-MM-DD)");
                optiuneSfarsit = citesteLinie(scanner);
                sfarsit = ServiciuConvertireDate.dinStringInLocalDate(optiuneSfarsit);
                
                Tabel.afiseazaProprietatiInchiriate(serviciuInterogare.cautaToateProprietatileInchiriateInterval(inceput, sfarsit, 100, 0));
                break;
                
            case AFISARE_VENIT_PROPRIETATE:
                System.out.println("Introduceti codul proprietatii (de ex.: CVM16A2)");
                optiuneProprietate = citesteLinie(scanner);

                Rezultat.afiseazaRezultatIntreg(serviciuInterogare.calculeazaVenitPentruProprietate(optiuneProprietate));
                break;
                
            case AFISARE_VENIT_TOTAL:
                Rezultat.afiseazaRezultatIntreg(serviciuInterogare.calculeazaVenitTotal());
                break;
                
            case INCHIRIERE_PROPRIETATE:
                System.out.println("Introduceti codul proprietatii (de ex.: CVM16A2)");
                optiuneProprietate = citesteLinie(scanner);
                
                System.out.println("Introduceti chiriasul, identificat prin email (de ex.: ana.pop@gmail.com)");
                String chirias = citesteLinie(scanner);
                
                System.out.println("Introduceti inceputul intervalului (format YYYY-MM-DD)");
                optiuneInceput = citesteLinie(scanner);
                inceput = ServiciuConvertireDate.dinStringInLocalDate(optiuneInceput);
                
                System.out.println("Introduceti sfarsitul intervalului (format YYYY-MM-DD)");
                optiuneSfarsit = citesteLinie(scanner);
                sfarsit = ServiciuConvertireDate.dinStringInLocalDate(optiuneSfarsit);
                
                Rezultat.afiseazaRezultatBoolean(serviciuInterogare.inchiriazaProprietate(optiuneProprietate, chirias, inceput, sfarsit));
                Tabel.afiseazaProprietatiInchiriate(serviciuInterogare.cautaToateProprietatileInchiriateInterval(inceput, sfarsit, 100, 0));
                break;
                
            case ANULARE_INCHIRIERE:
                System.out.println("Introduceti codul proprietatii (de ex.: CVM16A2)");
                optiuneProprietate = citesteLinie(scanner);
                
                System.out.println("Introduceti inceputul intervalului (format YYYY-MM-DD)");
                optiuneInceput = citesteLinie(scanner);
                inceput = ServiciuConvertireDate.dinStringInLocalDate(optiuneInceput);
                
                System.out.println("Introduceti sfarsitul intervalului (format YYYY-MM-DD)");
                optiuneSfarsit = citesteLinie(scanner);
                sfarsit = ServiciuConvertireDate.dinStringInLocalDate(optiuneSfarsit);

                Rezultat.afiseazaRezultatBoolean(serviciuInterogare.anuleazaInchiriere(optiuneProprietate, inceput, sfarsit));
                Tabel.afiseazaProprietatiInchiriate(serviciuInterogare.cautaToateProprietatileInchiriateInterval(inceput, sfarsit, 100, 0));
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
            System.out.println("Introdu un sir (cuvant).");
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
            System.out.println("Introdu un numar.");
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
        System.out.println("CRUD proprietati:");
        System.out.println("-".repeat(lineLength));

        String optiune = String.format("%s. Afisare proprietati", AFISARE_PROPRIETATI);
        System.out.println(optiune);
        
        optiune = String.format("%s. Adaugare a unei proprietati", ADAUGARE_PROPRIETATE);
        System.out.println(optiune);
        
        optiune = String.format("%s. Stergere a unei proprietati", STERGERE_PROPRIETATE);
        System.out.println(optiune);
        
        optiune = String.format("%s. Modificare a unei proprietati", MODIFICARE_PROPRIETATE);
        System.out.println(optiune);
        
        System.out.println("\nCRUD proprietati inchiriate:");
        System.out.println("-".repeat(lineLength));
        
        optiune = String.format("%s. Afisare proprietati inchiriate in luna curenta",
                AFISARE_PROPRIETATI_INCHIRIATE_LUNA_CURENTA);
        System.out.println(optiune);

        optiune = String.format("%s. Afisare proprietati inchiriate intr-o luna anume",
                AFISARE_PROPRIETATI_INCHIRIATE_LUNA);
        System.out.println(optiune);

        optiune = String.format("%s. Afisare proprietati inchiriate in anul curent",
                AFISARE_PROPRIETATI_INCHIRIATE_AN);
        System.out.println(optiune);
        
        optiune = String.format("%s. Afisare proprietati inchiriate intr-un interval",
                AFISARE_PROPRIETATI_INCHIRIATE_INTERVAL);
        System.out.println(optiune);
        
        optiune = String.format("%s. Afisare proprietati disponibile intr-un interval",
                AFISARE_PROPRIETATI_DISPONIBILE_INTERVAL);
        System.out.println(optiune);
        
        optiune = String.format("%s. Inchiriere a unei proprietati", INCHIRIERE_PROPRIETATE);
        System.out.println(optiune);
        
        optiune = String.format("%s. Anulare a unei inchirieri", ANULARE_INCHIRIERE);
        System.out.println(optiune);
        
        System.out.println("\nInformatii aditionale:");
        System.out.println("-".repeat(lineLength));
        optiune = String.format("%s. Afisare venit total pentru o proprietate (pana in ziua curenta)", AFISARE_VENIT_PROPRIETATE);
        System.out.println(optiune);

        optiune = String.format("%s. Afisare venit total (pana in ziua curenta)", AFISARE_VENIT_TOTAL);
        System.out.println(optiune);

        System.out.println("\n0. Incheiere program");

        System.out.println("=".repeat(lineLength));

    }
}
package final_project.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;

import final_project.Model.*;
import final_project.Serviciu.*;
import final_project.View.*;

/**
 * Controller de test.
 * Valideaza operatiile.
 */
public class MainTest
{
    static ServiciuInterogare serviciuInterogare;

    public static void main(String[] args)
    {
        serviciuInterogare = new ServiciuInterogare();

        try {
            testCrudProprietati();
            testCrudProprietatiInchiriate();
            testInformatiiAditionale();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Testeaza operatiile de CRUD pe proprietati.
     * 
     * @throws Exception 
     */
    private static void testCrudProprietati() throws Exception
    {
        int lineLength = 80;

        System.out.println("=".repeat(lineLength));
        System.out.println("CRUD proprietati:");
        System.out.println("-".repeat(lineLength));

        /*
         * Afisare tabel proprietati.
         */
        String optiune = String.format("%s. Afisare proprietati", Main.AFISARE_PROPRIETATI);
        System.out.println(optiune);
        HashMap<Integer, Proprietate> proprietati = serviciuInterogare.cautaToateProprietatile(100, 0);
        Tabel.afiseazaProprietati(proprietati);
        System.out.println("\n");

        /*
         * Adaugare proprietate si interogare baza de date pentru a confirma inserarea.
         */
        optiune = String.format("%s. Adaugare a unei proprietati", Main.ADAUGARE_PROPRIETATE);
        System.out.println(optiune);

        String cod = "codTest";
        System.out.println(String.format("Adaugare proprietate cu codul %s.", cod));
        Proprietate proprietate = new Proprietate();
        proprietate.setCod(cod);
        proprietate.setTip("apartament");
        proprietate.setCamere(2);
        proprietate.setCartier("Aeroport");
        proprietate.setAdresa("Aviatorilor nr 70");
        proprietate.setPret(15);

        Rezultat.afiseazaRezultatBoolean(serviciuInterogare.adaugaProprietate(proprietate));

        System.out.println(String.format("Cautare proprietate cu codul %s.", cod));
        proprietate = serviciuInterogare.cauta(cod);
        Tabel.afiseazaProprietate(proprietate);
        System.out.println("\n");

        /*
         * Stergere proprietate.
         */
        optiune = String.format("%s. Stergere a unei proprietati", Main.STERGERE_PROPRIETATE);
        System.out.println(optiune);
        System.out.println(String.format("Stergere proprietate cu codul %s.", cod));
        Rezultat.afiseazaRezultatBoolean(serviciuInterogare.stergeProprietate(cod));
        System.out.println(String.format("Cautare proprietate cu codul %s.", cod));
        Tabel.afiseazaProprietate(serviciuInterogare.cauta(cod));
        System.out.println("\n");

        /*
         * Modificare proprietate.
         */
        optiune = String.format("%s. Modificare a unei proprietati", Main.MODIFICARE_PROPRIETATE);
        System.out.println(optiune);
        cod = "AM2C2";
        Long pret = (long) new Random().nextInt(Short.MAX_VALUE + 1);
        System.out.println(String.format("Setare pret al proprietatii cu codul %s: %d", cod, pret));
        Rezultat.afiseazaRezultatBoolean(serviciuInterogare.modificaProprietate(cod, pret));
        System.out.println("\n");

        System.out.println("Starea finala a proprietatilor:");
        Tabel.afiseazaProprietati(serviciuInterogare.cautaToateProprietatile(100, 0));
    }

    /**
     * Testeaza operatiile de CRUD pe proprietati inchiriate.
     * 
     * @throws Exception 
     */
    private static void testCrudProprietatiInchiriate() throws Exception
    {
        int lineLength = 80;

        System.out.println("\nCRUD proprietati inchiriate:");
        System.out.println("-".repeat(lineLength));
        
        System.out.println("Starea initiala a inchirierilor: ");
        LocalDate inceput = ServiciuConvertireDate.dinStringInLocalDate("2020-01-01");
        LocalDate sfarsit = ServiciuConvertireDate.dinStringInLocalDate("2050-01-01");
        HashMap<Integer, ProprietateInchiriata> proprietatiInchiriate = serviciuInterogare.cautaToateProprietatileInchiriateInterval(inceput, sfarsit, 100, 0);
        Tabel.afiseazaProprietatiInchiriate(proprietatiInchiriate);
        System.out.println("\n");
        
        /*
         * Afisare luna curenta
         */
        String optiune = String.format("%s. Afisare proprietati inchiriate in luna curenta",
                Main.AFISARE_PROPRIETATI_INCHIRIATE_LUNA_CURENTA);
        System.out.println(optiune);
        Tabel.afiseazaProprietatiInchiriate(serviciuInterogare.cautaToateProprietatileInchiriateLunaCurenta(100, 0));
        System.out.println("\n");

        /*
         * Afisare luna specifica
         */
        optiune = String.format("%s. Afisare proprietati inchiriate intr-o luna anume",
                Main.AFISARE_PROPRIETATI_INCHIRIATE_LUNA);
        System.out.println(optiune);
        int optiuneLuna = 3;
        System.out.println(String.format("Afisare inchiriere pentru luna %d:", optiuneLuna));
        Tabel.afiseazaProprietatiInchiriate(
                serviciuInterogare.cautaToateProprietatileInchiriateLuna(optiuneLuna, 100, 0));
        System.out.println("\n");

        /*
         * Afisare an curent
         */
        optiune = String.format("%s. Afisare proprietati inchiriate in anul curent",
                Main.AFISARE_PROPRIETATI_INCHIRIATE_AN);
        System.out.println(optiune);
        Tabel.afiseazaProprietatiInchiriate(serviciuInterogare.cautaToateProprietatileInchiriateAnCurent(100, 0));
        System.out.println("\n");

        /*
         * Afisare inchiriate interval
         */
        optiune = String.format("%s. Afisare proprietati inchiriate intr-un interval",
                Main.AFISARE_PROPRIETATI_INCHIRIATE_INTERVAL);
        System.out.println(optiune);
        inceput = ServiciuConvertireDate.dinStringInLocalDate("2020-01-01");
        sfarsit = ServiciuConvertireDate.dinStringInLocalDate("2020-04-02");
        System.out.println(String.format("Interval: %s - %s.", inceput, sfarsit));
        
        proprietatiInchiriate = serviciuInterogare.cautaToateProprietatileInchiriateInterval(inceput, sfarsit, 100, 0); 
        Tabel.afiseazaProprietatiInchiriate(proprietatiInchiriate);
        System.out.println("\n");

        /*
         * Afisare disponibile interval
         */
        optiune = String.format("%s. Afisare proprietati disponibile intr-un interval",
                Main.AFISARE_PROPRIETATI_DISPONIBILE_INTERVAL);
        System.out.println(optiune);
        System.out.println(String.format("Interval: %s - %s", inceput, sfarsit));
        HashMap<Integer, Proprietate> proprietati = serviciuInterogare.cautaToateProprietatileDisponibileInterval(inceput, sfarsit, 100, 0); 
        Tabel.afiseazaProprietati(proprietati);   
        System.out.println("\n");

        /*
         * Inchiriere
         */
        optiune = String.format("%s. Inchiriere a unei proprietati", Main.INCHIRIERE_PROPRIETATE);
        System.out.println(optiune);
        String optiuneProprietate = "CVM20C21";
        String chirias = "vcioban@cs.ubbcluj.ro";
        inceput = ServiciuConvertireDate.dinStringInLocalDate("2020-05-02");
        sfarsit = ServiciuConvertireDate.dinStringInLocalDate("2020-05-06");
        
        try {
            Rezultat.afiseazaRezultatBoolean(
                serviciuInterogare.inchiriazaProprietate(optiuneProprietate, chirias, inceput, sfarsit));
        } catch (Exception e) {
            System.err.println(e.getMessage() + " " + optiuneProprietate + " " + inceput.toString() + " - " + sfarsit.toString());
        }
            
        Tabel.afiseazaProprietatiInchiriate(
                serviciuInterogare.cautaToateProprietatileInchiriateInterval(inceput, sfarsit, 100, 0));
        
        /*
         * Anulare inchiriere 
         */
        System.out.println("\n");
        optiune = String.format("%s. Anulare a unei inchirieri", Main.ANULARE_INCHIRIERE);
        System.out.println(optiune);
        Rezultat.afiseazaRezultatBoolean(serviciuInterogare.anuleazaInchiriere(optiuneProprietate, inceput, sfarsit)); 
        System.out.println("Cautare inchirieri in perioada respectiva.");
        proprietatiInchiriate = serviciuInterogare.cautaToateProprietatileInchiriateInterval(inceput, sfarsit, 100, 0); 
        Tabel.afiseazaProprietatiInchiriate(proprietatiInchiriate);
        System.out.println("\n");

        System.out.println("Starea finala a inchirierilor: ");
        inceput = ServiciuConvertireDate.dinStringInLocalDate("2020-01-01");
        sfarsit = ServiciuConvertireDate.dinStringInLocalDate("2030-12-31");
        proprietatiInchiriate = serviciuInterogare.cautaToateProprietatileInchiriateInterval(inceput, sfarsit, 100, 0);
        Tabel.afiseazaProprietatiInchiriate(proprietatiInchiriate);
    }

    /**
     * Afiseaza venitul total si pentru o proprietate specifica.
     * @throws Exception 
     */
    private static void testInformatiiAditionale() throws Exception
    {
        int lineLength = 80;

        System.out.println("\nInformatii aditionale:");
        System.out.println("-".repeat(lineLength));
        String optiune = String.format("%s. Afisare venit total pentru o proprietate", Main.AFISARE_VENIT_PROPRIETATE);
        System.out.println(optiune);
        String optiuneProprietate = "AM2Q12";
        System.out.println(String.format("Venitul pentru proprietatea %s (pana in ziua curenta):", optiuneProprietate));
        Rezultat.afiseazaRezultatIntreg(serviciuInterogare.calculeazaVenitPentruProprietate(optiuneProprietate));
        System.out.println("\n");

        optiune = String.format("%s. Afisare venit total (pana in ziua curenta):", Main.AFISARE_VENIT_TOTAL);
        System.out.println(optiune);
        Rezultat.afiseazaRezultatIntreg(serviciuInterogare.calculeazaVenitTotal());
        
        System.out.println("Inchirieri pana in ziua curenta:");
        HashMap<Integer, ProprietateInchiriata> proprietatiInchiriate = serviciuInterogare.cautaToateInchiriatePanaAzi(100, 0);
        Tabel.afiseazaProprietatiInchiriate(proprietatiInchiriate);

        System.out.println("=".repeat(lineLength));
    }
}

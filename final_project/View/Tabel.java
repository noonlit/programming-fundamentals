package final_project.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import final_project.Model.ObiectMagic;
import final_project.Model.Proprietate;
import final_project.Model.ProprietateInchiriata;

public class Tabel
{
    private final static int LUNGIME_LINIE_PROPRIETATI = 87;
    private final static int LUNGIME_LINIE_PROPRIETATI_INCHIRIATE = 138;
    
    /**
     * Afiseaza capul de tabel pentru tabelele de proprietati.
     */
    private static void afiseazaCapTabelProprietati()
    {
        String[] coloane = { "ID", "Cod", "Cartier", "Tip", "Camere", "Adresa", "Pret" };     
        String formatCapTabel = "|%-5s|%-8s|%-15s|%-15s|%-6s|%-20s|%-10s|";

        /*
         * Afisam capul de tabel.
         */
        System.out.println("=".repeat(LUNGIME_LINIE_PROPRIETATI));
        System.out.println(String.format(formatCapTabel, coloane[0], coloane[1], coloane[2], coloane[3], coloane[4], coloane[5], coloane[6]));
        System.out.println("=".repeat(LUNGIME_LINIE_PROPRIETATI));
    }
    
    /**
     * Afiseaza un HashMap de proprietati in forma tabelara.
     * 
     * @param HashMap<Proprietate> proprietati
     */
    public static void afiseazaProprietati(HashMap<Integer, Proprietate> proprietati)
    {
        if (proprietati.isEmpty()) {
            System.out.println("Nu exista rezultate.");
            return;
        }
        
        afiseazaCapTabelProprietati();
        
        Iterator iterator = proprietati.entrySet().iterator();   
        while (iterator.hasNext()) { 
            HashMap.Entry<Integer, Proprietate> element = (Entry<Integer, Proprietate>) iterator.next();        
            afiseazaRandProprietate((Proprietate) element.getValue());           
        }
        
        System.out.println("Total: " + proprietati.size());
    }
    
    /**
     * Afiseaza in format tabelar o singura proprietate.
     * 
     * @param Proprietate proprietate
     */
    public static void afiseazaProprietate(Proprietate proprietate)
    {
        if (proprietate == null) {
            System.out.println("Nu exista rezultat.");
            return;
        }
        
        afiseazaCapTabelProprietati();
        afiseazaRandProprietate(proprietate);
    }
    
    /**
     * Afiseaza o linie de tabel cu datele unei proprietati. 
     * 
     * @param Proprietate proprietate
     */
    private static void afiseazaRandProprietate(Proprietate proprietate)
    {
        String formatDate = "|%5s|%-8s|%-15s|%-15s|%6s|%-20s|%10s|";
        
        System.out.println(
                String.format(
                        formatDate, 
                        proprietate.getId(), 
                        proprietate.getCod(), 
                        proprietate.getCartier(), 
                        proprietate.getTip(), 
                        proprietate.getCamere(), 
                        proprietate.getAdresa(), 
                        proprietate.getPret()
                        )
                );
        System.out.println("-".repeat(LUNGIME_LINIE_PROPRIETATI));
    }
    
    /**
     * Afiseaza capul de tabel pentru tabelele de proprietati inchiriate.
     */
    private static void afiseazaCapTabelProprietatiInchiriate()
    {
        String[] coloane = { "ID", "Cod", "Cartier", "Tip", "Camere", "Adresa", "Pret", "Chirias", "Inceput", "Sfarsit" };     
        String formatCapTabel = "|%-5s|%-8s|%-15s|%-15s|%-6s|%-20s|%-10s|%-28s|%-10s|%-10s|";

        /*
         * Afisam capul de tabel.
         */
        System.out.println("=".repeat(LUNGIME_LINIE_PROPRIETATI_INCHIRIATE));
        System.out.println(String.format(formatCapTabel, coloane[0], coloane[1], coloane[2], coloane[3], coloane[4], coloane[5], coloane[6], coloane[7], coloane[8], coloane[9]));
        System.out.println("=".repeat(LUNGIME_LINIE_PROPRIETATI_INCHIRIATE));
    }
    
    /**
     * Afiseaza un HashMap de proprietati in forma tabelara.
     * 
     * @param HashMap<Proprietate> proprietati
     */
    public static void afiseazaProprietatiInchiriate(HashMap<Integer, ProprietateInchiriata> proprietati)
    {
        if (proprietati.isEmpty()) {
            System.out.println("Nu exista rezultate.");
            return;
        }
        
        afiseazaCapTabelProprietatiInchiriate();
        
        Iterator iterator = proprietati.entrySet().iterator();   
        while (iterator.hasNext()) { 
            HashMap.Entry<Integer, ProprietateInchiriata> element = (Entry<Integer, ProprietateInchiriata>) iterator.next();        
            afiseazaRandProprietateInchiriata((ProprietateInchiriata) element.getValue());           
        }  
        
        System.out.println("Total: " + proprietati.size());
    }
    
    /**
     *  Afiseaza o linie de tabel cu datele unei proprietati inchiriate. 
     *  
     * @param ProprietateInchiriata proprietate
     */
    private static void afiseazaRandProprietateInchiriata(ProprietateInchiriata proprietate)
    {
        String formatDate = "|%5s|%-8s|%-15s|%-15s|%6s|%-20s|%10s|%-28s|%-10s|%-10s|";
        
        System.out.println(
                String.format(
                        formatDate, 
                        proprietate.getId(), 
                        proprietate.getCod(), 
                        proprietate.getCartier(), 
                        proprietate.getTip(), 
                        proprietate.getCamere(), 
                        proprietate.getAdresa(), 
                        proprietate.getPret(),
                        proprietate.getChirias(),
                        proprietate.getInceput(),
                        proprietate.getSfarsit()
                        )
                );
        System.out.println("-".repeat(LUNGIME_LINIE_PROPRIETATI_INCHIRIATE));
    }
    
    /**
     * Afiseaza in format tabelar o singura proprietate inchiriata.
     * 
     * @param Proprietate Inchiriata proprietate
     */
    public static void afiseazaProprietateInchiriata(ProprietateInchiriata proprietate)
    {
        if (proprietate == null) {
            System.out.println("Nu exista rezultat.");
            return;
        }
        
        afiseazaCapTabelProprietatiInchiriate();
        afiseazaRandProprietateInchiriata(proprietate);
    } 
}

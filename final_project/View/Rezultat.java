package final_project.View;

/**
 * Afiseaza date care nu necesita un format tabelar (boolean, int).
 */
public class Rezultat
{
    public static void afiseazaRezultatBoolean(boolean rezultat)
    {
        String r = rezultat == true ? "succes" : "insucces";
        System.out.println(String.format("Rezultatul este: %s.", r));
    }
    
    public static void afiseazaRezultatIntreg(int rezultat)
    {
        System.out.println(String.format("Numarul rezultat este: %d.", rezultat));
    }
}

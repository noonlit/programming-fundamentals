package final_project.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import final_project.Model.Proprietate;
import final_project.Model.ProprietateInchiriata;
import final_project.Model.Db.DbConn;
import final_project.Model.Hidrator.HidratorProprietate;
import final_project.Serviciu.ServiciuConvertireDate;

public class ProprietateRepository extends AbstractRepository<Proprietate>
{
    public static final String TABELA = "proprietate";
    public static final String[] CAMPURI = { "id", "cod", "tip", "camere", "cartier", "adresa", "pret" }; // TODO
    private HidratorProprietate hidrator;

    public ProprietateRepository()
    {
        this.hidrator = new HidratorProprietate();
    }

    /**
     * Modifica proprietatea data.
     * 
     * Momentan doar pretul poate fi modificat.
     * 
     * @param Proprietate e
     * @return boolean
     * @throws Exception
     * 
     */
    public boolean modifica(Proprietate e) throws Exception
    {
        if (e == null || e.getId() == null) {
            return false;
        }
        
        String stmt = String.format("UPDATE %s SET pret = %f WHERE id = %s", TABELA, e.getPret(), e.getId());

        return executaDML(stmt) > 0;
    }
    
    /**
     * Modifica pretul proprietatii cu codul dat.
     * 
     * @param String cod
     * @param Long pret
     * @return boolean
     * @throws Exception
     */
    public boolean modifica(String cod, Long pret) throws Exception
    {
        Proprietate e = cautaDupaCod(cod);
        
        if (e == null) {
            return false;
        }
        
        e.setPret(pret);
        
        return modifica(e);
    }

    /**
     * Salveaza proprietatea data.
     * 
     * @param Proprietate e
     * @return boolean
     * @throws Exception
     */
    public boolean insereaza(Proprietate e) throws Exception
    {
        if (e == null || e.getId() != null) {
            return false;
        }

        String stmt = String.format("INSERT INTO %s (%s) VALUES (%s)", TABELA, construiesteStringColoane(e),
                construiesteStringValori(e));

        return executaDML(stmt) > 0;
    }

    /**
     * Sterge proprietatea cu id-ul dat.
     * 
     * @param Long id
     * @return boolean
     * @throws Exception
     * 
     */
    public boolean sterge(Long id) throws Exception
    {
        String stmt = String.format("DELETE FROM %s WHERE id = %s", TABELA, id);

        return executaDML(stmt) > 0;
    }
    
    /**
     * Sterge proprietatea cu codul dat.
     * 
     * @param String cod
     * @return boolean
     * @throws Exception
     * 
     */
    public boolean sterge(String cod) throws Exception
    {
        String stmt = String.format("DELETE FROM %s WHERE cod = '%s'", TABELA, cod);

        return executaDML(stmt) > 0;
    }

    /**
     * Sterge toate proprietatile.
     * 
     * @return int Randurile afectate
     * @throws Exception
     */
    public int stergeToate() throws Exception
    {
        String stmt = String.format("DELETE FROM %s", TABELA);
        return executaDML(stmt);
    }

    /**
     * Returneaza proprietatea cu ID-ul dat.
     * 
     * @param int id
     * @return Proprietate
     * @throws Exception
     */
    public Proprietate cauta(int id) throws Exception
    {
        String select = String.format("SELECT * FROM %s WHERE id = %s", TABELA, id);

        ResultSet rezultat = executaSQL(select);
        return construiesteRezultat(rezultat);
    }

    /**
     * Returneaza proprietatea cu codul dat.
     * 
     * @param String cod
     * @return Proprietate
     * @throws Exception
     */
    public Proprietate cautaDupaCod(String cod) throws Exception
    {
        String select = String.format("SELECT * FROM %s WHERE cod = '%s'", TABELA, cod);
        ResultSet rezultat = executaSQL(select);

        return construiesteRezultat(rezultat);
    }
    
    /**
     * Returneaza toate proprietatile libere in intervalul dat.
     * 
     * @param String inceputInterval
     * @param String sfarsitInterval
     * @param int limita
     * @param int inceput
     * @return
     * @throws Exception
     */
    public HashMap<Integer, Proprietate> cautaToateLibereInterval(LocalDate inceputInterval,
            LocalDate sfarsitInterval, int limita, int inceput) throws Exception
    {
        String optiuneInceput = ServiciuConvertireDate.dinLocalDateInString(inceputInterval);
        String optiuneSfarsit = ServiciuConvertireDate.dinLocalDateInString(sfarsitInterval);
        
        String select = String.format("SELECT * FROM %s "
                + "LEFT JOIN proprietate_inchiriata ON proprietate.id = proprietate "
                + "WHERE (inceput IS NULL or sfarsit IS NULL) "
                + "OR (proprietate.id NOT IN "
                + "(SELECT proprietate FROM proprietate_inchiriata "
                + "WHERE '%s' < sfarsit AND '%s' > inceput)) "
                + "LIMIT %d OFFSET %d", TABELA, optiuneInceput, optiuneSfarsit, limita, inceput);
        
        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }

    /**
     * Returneaza un numar de proprietati mai mic sau egal cu limita (LIMIT), de la inceput (OFFSET).
     * 
     * @return HashMap<Integer, Proprietate>
     * @throws Exception
     */
    public HashMap<Integer, Proprietate> cautaToate(int limita, int inceput) throws Exception
    {
        String select = String.format("SELECT * FROM %s LIMIT %d OFFSET %d", TABELA, limita, inceput);
        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }

    /**
     * Converteste rezultatul dat intr-o lista de proprietati. Fiecare proprietate
     * se gaseste pe cheia corespunzatoare id-ului ei.
     * 
     * @return HashMap<Integer, Proprietate>
     * @throws Exception
     */
    private HashMap<Integer, Proprietate> construiesteRezultate(ResultSet rezultat) throws Exception
    {
        HashMap<Integer, Proprietate> proprietati = new HashMap<Integer, Proprietate>();

        while (rezultat.next()) {
            Proprietate proprietate = hidrator.hidrateaza(rezultat, CAMPURI);
            int index = proprietate.getId().intValue();
            proprietati.put(index, proprietate);
        }

        return proprietati;
    }

    /**
     * Converteste rezultatul dat intr-o proprietate.
     * 
     * @param ResultSet rezultat
     * @return Proprietate
     * @throws Exception
     */
    private Proprietate construiesteRezultat(ResultSet rezultat) throws Exception
    {
        if (!rezultat.next()) {
            return null;
        }

        Proprietate proprietate = hidrator.hidrateaza(rezultat, CAMPURI);

        try {
            rezultat.close();
        } catch (SQLException ignore) {
        }
        
        return proprietate;
    }
    
    /**
     * Returneaza un string care reprezinta lista de coloane 
     * pentru care avem valori nenule in instanta curenta de proprietate,
     * separate prin virgula.
     * 
     * Se foloseste la INSERT.
     * 
     * @param Proprietate proprietate
     * @return String
     */
    protected String construiesteStringColoane(Proprietate e)
    {
        String coloane = "";

        for (int i = 0, n = CAMPURI.length; i < n; i++) {
            Object valoare = e.get(CAMPURI[i]);
            if (valoare != null) {
                coloane += CAMPURI[i] + ",";
            }
        }

        coloane = coloane.substring(0, coloane.length() - 1);

        return coloane;
    }
    
    /**
     * Returneaza un string care reprezinta lista de valori nenule
     * din instanta curenta de proprietate,
     * separate prin virgula.
     * 
     * Se foloseste la INSERT.
     * 
     * @param Proprietate proprietate
     * @return String
     */
    private String construiesteStringValori(Proprietate e)
    {
        String valori = "";

        for (int i = 0, n = CAMPURI.length; i < n; i++) {
            Object valoare = e.get(CAMPURI[i]);
            if (valoare != null) {
                valori += "'" + valoare.toString() + "'" + ",";
            }
        }

        valori = valori.substring(0, valori.length() - 1);

        return valori;
    }
}

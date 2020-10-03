package final_project.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import final_project.Model.Proprietate;
import final_project.Model.ProprietateInchiriata;
import final_project.Model.Hidrator.HidratorProprietateInchiriata;
import final_project.Serviciu.ServiciuConvertireDate;

/**
 * Clasa care executa query-uri care tin de inchirieri.
 */
public class ProprietateInchiriataRepository extends AbstractRepository<ProprietateInchiriata>
{
    public static final String TABELA_SCRIERE = "proprietate_inchiriata";
    public static final String TABELA_CITIRE = "proprietate_inchiriata_complet";
    public static final String TABELA_CITIRE_LUNA_CURENTA = "proprietate_inchiriata_luna_curenta";
    public static final String TABELA_CITIRE_AN_CURENT = "proprietate_inchiriata_an_curent";

    protected static final String[] CAMPURI = { "id", "proprietate", "cod", "chirias", "tip", "camere", "cartier", "adresa",
            "pret", "inceput", "sfarsit" };
    private HidratorProprietateInchiriata hidrator;

    public ProprietateInchiriataRepository()
    {
        this.hidrator = new HidratorProprietateInchiriata();
    }

    /**
     * Salveaza o noua proprietate inchiriata.
     * 
     * @param ProprietateInchiriata e
     */
    public boolean insereaza(ProprietateInchiriata e) throws Exception
    {
        String coloane = "proprietate, chirias, inceput, sfarsit";
        String valori = String.format("%s, '%s', '%s', '%s'", e.getProprietate(), e.getChirias(), e.getInceput().toString(), e.getSfarsit().toString());
                
        String stmt = String.format("INSERT INTO %s (%s) VALUES (%s)", TABELA_SCRIERE, coloane, valori);

        return executaDML(stmt) > 0;
    }

    /**
     * Modifica o inchiriere.
     * Momentan se poate modifica doar intervalul in care este inchiriata proprietatea.
     * 
     * @param ProprietataInchiriata e
     */
    public boolean modifica(ProprietateInchiriata e) throws Exception
    {       
        String stmt = String.format("UPDATE %s SET inceput = '%s', sfarsit = '%s' WHERE id = %s", TABELA_SCRIERE, e.getInceput(), e.getSfarsit(), e.getId());

        return executaDML(stmt) > 0;
    }

    /**
     * Sterge o inchiriere dupa ID-ul randului din baza de date.
     * 
     * @param Long id
     */
    public boolean sterge(Long id) throws Exception
    {
        String stmt = String.format("DELETE FROM %s WHERE id = ", TABELA_CITIRE);
        return executaDML(stmt) > 0;
    }

    /**
     * Sterge toate inchirierile.
     */
    public int stergeToate() throws Exception
    {
        String stmt = String.format("DELETE FROM %s", TABELA_CITIRE);
        return executaDML(stmt);
    }

    /**
     * Cauta inchirierea cu ID-ul dat.
     * 
     * @param Long id
     * @return ProprietateInchiriata
     * @throws Exception
     */
    public ProprietateInchiriata cauta(Long id) throws Exception
    {
        String select = String.format(
                "SELECT %s.id, cod, chirias, tip, camere, cartier, adresa, pret, proprietate, inceput, sfarsit "
                        + "FROM %s " + "JOIN proprietate ON proprietate = proprietate.id "
                        + "WHERE %s.id = %s",
                TABELA_SCRIERE, TABELA_SCRIERE, TABELA_SCRIERE, id);
        ResultSet rezultat = executaSQL(select);
        
       return construiesteRezultat(rezultat);
    }
    
    /**
     * Cauta proprietate cu codul dat inchiriata in intervalul dat.
     *  
     * @param String cod
     * @param LocalDate inceputInterval
     * @param LocalDate sfarsitInterval
     * @return
     * @throws Exception
     */
    public ProprietateInchiriata cauta(String cod, LocalDate inceputInterval, LocalDate sfarsitInterval) throws Exception
    {
        String optiuneInceput = ServiciuConvertireDate.dinLocalDateInString(inceputInterval);
        String optiuneSfarsit = ServiciuConvertireDate.dinLocalDateInString(sfarsitInterval);
        
        String select = String.format(
                "SELECT %s.id, cod, chirias, tip, camere, cartier, adresa, pret, proprietate, inceput, sfarsit "
                        + "FROM %s " + "JOIN proprietate ON proprietate = proprietate.id " + "WHERE '%s' < sfarsit "
                        + "AND '%s' > inceput AND proprietate.cod = '%s' ",
                TABELA_SCRIERE, TABELA_SCRIERE, optiuneInceput, optiuneSfarsit, cod);

        ResultSet rezultat = executaSQL(select);
        
        return construiesteRezultat(rezultat);
    }
    
    /**
     * Construieste o singura proprietate inchiriata din rezultatul dat.
     * 
     * @param ResultSet rezultat
     * @return ProprietateInchiriata
     * @throws Exception
     */
    private ProprietateInchiriata construiesteRezultat(ResultSet rezultat) throws Exception
    {
        if (!rezultat.next()) {
            return null;
        }

        ProprietateInchiriata proprietate = hidrator.hidrateaza(rezultat, CAMPURI);

        try {
            rezultat.close();
        } catch (SQLException ignore) {
        }
        
        return proprietate;
    }

    /**
     * Returneaza toate proprietatile inchiriate de la inceput (OFFSET) la limita (LIMIT).
     * 
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToate(int limita, int inceput) throws Exception
    {
        String select = String.format(
                "SELECT %s.id, cod, chirias, tip, camere, cartier, adresa, pret, proprietate, inceput, sfarsit "
                        + "FROM proprietate " + "JOIN %s ON proprietate = proprietate.id "
                        + "LIMIT %d OFFSET %d",
                TABELA_SCRIERE, TABELA_SCRIERE, limita, inceput);
        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }

    /**
     * Construieste un HashMap de proprietati inchiriate pe baza unor randuri din baza de date.
     * 
     * @param ResultSet rezultat
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws SQLException
     * @throws Exception
     */
    private HashMap<Integer, ProprietateInchiriata> construiesteRezultate(ResultSet rezultat)
            throws SQLException, Exception
    {
        HashMap<Integer, ProprietateInchiriata> proprietati = new HashMap<Integer, ProprietateInchiriata>();

        while (rezultat.next()) {
            ProprietateInchiriata proprietate = hidrator.hidrateaza(rezultat, CAMPURI);
            int index = proprietate.getId().intValue();
            proprietati.put(index, proprietate);
        }

        return proprietati;
    }

    /**
     * Returneaza toate proprietatile inchiriate in luna curenta.
     * 
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws Exception
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateInchiriateLunaCurenta(int limita, int inceput)
            throws Exception
    {
        String select = String.format("SELECT * FROM %s LIMIT %d OFFSET %d", TABELA_CITIRE_LUNA_CURENTA, limita,
                inceput);
        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }

    /**
     * Returneaza toate proprietatile inchiriata in luna data.
     * 
     * @param int luna (1-12)
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws Exception
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateInchiriateLuna(int luna, int limita, int inceput)
            throws Exception
    {
        String select = String.format(
                "SELECT proprietate_inchiriata.id, cod, chirias, tip, camere, cartier, adresa, pret, proprietate, inceput, sfarsit "
                        + "FROM proprietate " + "JOIN %s ON proprietate = proprietate.id "
                        + "WHERE MONTH(inceput) = %s OR MONTH(sfarsit) = %s " + "AND YEAR(inceput) = YEAR(CURDATE()) " + "LIMIT %d OFFSET %d",
                TABELA_SCRIERE, luna, luna, limita, inceput);
        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }

    /**
     * Returneaza toate proprietatile inchiriate in anul curent.
     * 
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws Exception
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateInchiriateAnCurent(int limita, int inceput)
            throws Exception
    {
        String select = String.format("SELECT * FROM %s LIMIT %d OFFSET %d", TABELA_CITIRE_AN_CURENT, limita, inceput);
        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }

    /**
     * Returneaza toate proprietatile inchiriate in intervalul dat.
     * 
     * @param LocalDate inceputInterval
     * @param LocalDate sfarsitInterval
     * @param int limita 
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws Exception
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateInchiriateInterval(LocalDate inceputInterval,
            LocalDate sfarsitInterval, int limita, int inceput) throws Exception
    {
        String optiuneInceput = ServiciuConvertireDate.dinLocalDateInString(inceputInterval);
        String optiuneSfarsit = ServiciuConvertireDate.dinLocalDateInString(sfarsitInterval);
        
        String select = String.format(
                "SELECT %s.id, cod, chirias, tip, camere, cartier, adresa, pret, proprietate, inceput, sfarsit "
                        + "FROM proprietate " + "JOIN %s ON proprietate = proprietate.id " + "WHERE '%s' < sfarsit "
                        + "AND '%s' > inceput " + "LIMIT %d OFFSET %d",
                TABELA_SCRIERE, TABELA_SCRIERE, optiuneInceput, optiuneSfarsit, limita, inceput);

        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }
    
    /**
     * Returneaza toate proprietatile inchiriate pana la data curenta.
     * 
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata> 
     * @throws Exception
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateInchiriatePanaAzi(int limita, int inceput) throws Exception
    {  
        String select = String.format(
                "SELECT %s.id, cod, chirias, tip, camere, cartier, adresa, pret, proprietate, inceput, sfarsit "
                        + "FROM proprietate " + "JOIN %s ON proprietate = proprietate.id WHERE sfarsit <= CURDATE() " 
                        + "LIMIT %d OFFSET %d",
                TABELA_SCRIERE, TABELA_SCRIERE, limita, inceput);

        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }
    
    /**
     * Returneaza toate proprietatile inchiriate de la data curenta.
     * 
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata> 
     * @throws Exception
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateInchiriateDeAzi(int limita, int inceput) throws Exception
    {        
        String select = String.format(
                "SELECT %s.id, cod, chirias, tip, camere, cartier, adresa, pret, proprietate, inceput, sfarsit "
                        + "FROM proprietate JOIN %s ON proprietate = proprietate.id WHERE inceput >= CURDATE() "
                        + "AND '%s' > inceput " + "LIMIT %d OFFSET %d",
                TABELA_SCRIERE, TABELA_SCRIERE, limita, inceput);

        ResultSet rezultat = executaSQL(select);

        return construiesteRezultate(rezultat);
    }

    /**
     * Calculeaza venitul obtinut pentru proprietatea cu codul dat pana la data curenta.
     * 
     * @param codProprietate
     * @return int
     * @throws Exception
     */
    public int calculeazaVenit(String codProprietate) throws Exception
    {
        String select = String.format("SELECT pret * " + "(SELECT COUNT(id) FROM %s "
                + "WHERE proprietate = (SELECT id FROM proprietate WHERE cod = '%s') AND sfarsit <= CURDATE()) AS total "
                + "FROM proprietate WHERE cod = '%s'", TABELA_SCRIERE, codProprietate, codProprietate);

        ResultSet rezultat = executaSQL(select);

        rezultat.first();
        return rezultat.getInt("total");
    }
    
    /**
     * Returneaza venitul total obtinut din inchirieri pana la data curenta.
     * 
     * @return int
     * @throws Exception
     */
    public int calculeazaVenitTotal() throws Exception
    {
        String select = String.format("SELECT SUM(pret) AS total FROM proprietate "
                + "JOIN %s ON proprietate.id = %s.proprietate WHERE sfarsit <= CURDATE()", TABELA_SCRIERE, TABELA_SCRIERE);

        ResultSet rezultat = executaSQL(select);

        rezultat.first();
        return rezultat.getInt("total");
    }

    /**
     * Inchiriaza proprietatea cu codul dat, pe perioada delimitata de inceput si sfarsit.
     * 
     * @param String proprietate
     * @param String inceput
     * @param String sfarsit
     * @return boolean
     * @throws Exception
     */
    public boolean inchiriazaProprietate(String cod, String chirias, LocalDate inceput, LocalDate sfarsit) throws Exception
    {
        Proprietate proprietate = new ProprietateRepository().cautaDupaCod(cod); 
        
        if (proprietate == null || proprietate.getId() == null) {
            throw new Exception("Proprietatea nu exista!");
        }
        
        String dataInceput = ServiciuConvertireDate.dinLocalDateInString(inceput);
        String dataSfarsit = ServiciuConvertireDate.dinLocalDateInString(sfarsit);
        String coloane = "proprietate, chirias, inceput, sfarsit";
        String valori = String.format("%s, '%s', '%s', '%s'", proprietate.getId().toString(), chirias, dataInceput, dataSfarsit);
                
        String stmt = String.format("INSERT INTO %s (%s) VALUES (%s)", TABELA_SCRIERE, coloane, valori);

        return executaDML(stmt) > 0;
    }

    /**
     * Anuleaza inchirierea pentru proprietatea cu codul dat, in intervalul dat.
     * 
     * @param String cod
     * @param LocalDate inceput
     * @param LocalDate sfarsit
     * @return boolean
     * @throws Exception
     */
    public boolean anuleazaInchirere(String cod, LocalDate inceput, LocalDate sfarsit) throws Exception
    {
        Proprietate proprietate = new ProprietateRepository().cautaDupaCod(cod); 
        
        if (proprietate == null || proprietate.getId() == null) {
            throw new Exception("Proprietatea nu exista!");
        }
        
        String dataInceput = ServiciuConvertireDate.dinLocalDateInString(inceput);
        String dataSfarsit = ServiciuConvertireDate.dinLocalDateInString(sfarsit);
        
        String stmt = String.format("DELETE FROM %s WHERE proprietate = %s AND inceput = '%s' AND sfarsit = '%s'", TABELA_SCRIERE, proprietate.getId().toString(), dataInceput, dataSfarsit);
        
        return executaDML(stmt) > 0;
    }

    /**
     * Modifica o inchiriere.
     * 
     * Momentan se poate modifica doar perioada inchirierii.
     * 
     * @param String cod
     * @param LocalDate inceput
     * @param LocalDate sfarsit
     * @param LocalDate nouInceput
     * @param LocalDate nouSfarsit
     * @return boolean
     * @throws Exception
     */
    public boolean modificaInchirere(String cod, LocalDate inceput, LocalDate sfarsit, LocalDate nouInceput, LocalDate nouSfarsit) throws Exception
    {
        Proprietate proprietate = new ProprietateRepository().cautaDupaCod(cod); 
        
        if (proprietate == null || proprietate.getId() == null) {
            throw new Exception("Proprietatea nu exista!");
        }
        
        String dataInceput = ServiciuConvertireDate.dinLocalDateInString(inceput);
        String dataSfarsit = ServiciuConvertireDate.dinLocalDateInString(sfarsit);
        String dataNouInceput = ServiciuConvertireDate.dinLocalDateInString(nouInceput);
        String dataNouSfarsit = ServiciuConvertireDate.dinLocalDateInString(nouSfarsit);
        
        String stmt = String.format("UPDATE %s "
                + "SET inceput = '%s', sfarsit = '%s' "
                + "WHERE proprietate = %s "
                + "AND inceput = '%s' AND sfarsit = '%s'", TABELA_SCRIERE, dataNouInceput, dataNouSfarsit, proprietate.getId().toString(), dataInceput, dataSfarsit);
        
        return executaDML(stmt) > 0;
    }
}

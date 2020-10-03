package final_project.Serviciu;

import java.time.LocalDate;
import java.util.*;
import final_project.Model.*;
import final_project.Repository.*;

/**
 * Apelat din controller (Main). Foloseste Repository-uri pentru operatii de tip CRUD.
 */
public class ServiciuInterogare
{
    private ProprietateRepository repoProprietati;
    private ProprietateInchiriataRepository repoProprietatiInchiriate;
    
    public ServiciuInterogare()
    {
        repoProprietati = new ProprietateRepository();
        repoProprietatiInchiriate = new ProprietateInchiriataRepository();
    }
    
    /**
     * Returneaza toate proprietatile dintre limita (LIMIT), de la inceput (OFFSET). 
     * 
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, Proprietate>
     * @throws Exception 
     */
    public HashMap<Integer, Proprietate> cautaToateProprietatile(int limita, int inceput) throws Exception
    {
        return repoProprietati.cautaToate(limita, inceput); 
    }
    
    /**
     * Returneaza proprietatea cu codul dat.
     * 
     * @param String cod
     * @return Proprietate
     * @throws Exception 
     */
    public Proprietate cauta(String cod) throws Exception
    {
        return repoProprietati.cautaDupaCod(cod); 
    }
    
    /**
     * Returneaza toate proprietatile inchiriate dintre limita (LIMIT), de la inceput (OFFSET).
     *  
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws Exception 
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateProprietatileInchiriateLunaCurenta(int limita, int inceput) throws Exception
    {        
        return repoProprietatiInchiriate.cautaToateInchiriateLunaCurenta(limita, inceput);
    }
    
    /**
     * Returneaza toate proprietatile inchiriate in luna data dintre limita (LIMIT), de la inceput (OFFSET).
     * 
     * @param int luna
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws Exception 
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateProprietatileInchiriateLuna(int luna, int limita, int inceput) throws Exception
    {       
        if (luna <= 0 || luna > 12) {
            throw new Exception("Luna invalida!");
        }
        
        return repoProprietatiInchiriate.cautaToateInchiriateLuna(luna, limita, inceput);
    }
    
    /**
     * Returneaza toate proprietatile disponibile in intervalul dat dintre limita (LIMIT), de la inceput (OFFSET).
     *  
     * @param LocalDate optiuneInceput
     * @param LocalDate optiuneSfarsit
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, Proprietate>
     * @throws Exception 
     */
    public HashMap<Integer, Proprietate> cautaToateProprietatileDisponibileInterval(LocalDate optiuneInceput, LocalDate optiuneSfarsit, int limita, int inceput) throws Exception
    {     
        return repoProprietati.cautaToateLibereInterval(optiuneInceput, optiuneSfarsit, limita, inceput);
    }

    /**
     * Returneaza toate proprietatile disponibile in anul curent dintre limita (LIMIT), de la inceput (OFFSET).
     *  
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws Exception 
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateProprietatileInchiriateAnCurent(int limita, int inceput) throws Exception
    {
        return repoProprietatiInchiriate.cautaToateInchiriateAnCurent(limita, inceput);
    }
    
    /**
     * Returneaza toate proprietatile inchiriate in in intervalul dat dintre limita (LIMIT), de la inceput (OFFSET).
     * 
     * @param LocalDate optiuneInceput
     * @param LocalDate optiuneSfarsit
     * @param int limita
     * @param int inceput
     * @return HashMap<Integer, ProprietateInchiriata>
     * @throws Exception 
     */
    public HashMap<Integer, ProprietateInchiriata> cautaToateProprietatileInchiriateInterval(LocalDate optiuneInceput, LocalDate optiuneSfarsit, int limita, int inceput) throws Exception
    {        
        return repoProprietatiInchiriate.cautaToateInchiriateInterval(optiuneInceput, optiuneSfarsit, limita, inceput);
    }

    /**
     * Returneaza venitul total pentru proprietatea cu codul dat.
     * 
     * @param String codProprietate
     * @return int
     * @throws Exception 
     */
    public int calculeazaVenitPentruProprietate(String codProprietate) throws Exception
    {
        return repoProprietatiInchiriate.calculeazaVenit(codProprietate);
    }
    
    /**
     * Returneaza venitul total obtinut din inchirieri.
     * 
     * @return int
     * @throws Exception 
     */
    public int calculeazaVenitTotal() throws Exception
    {
        return repoProprietatiInchiriate.calculeazaVenitTotal();
    }

    /**
     * Inchiriaza proprietatea cu codul dat.
     * 
     * @param String cod
     * @param String chirias
     * @param inceput
     * @param sfarsit
     * @return boolean
     * @throws Exception 
     */
    public boolean inchiriazaProprietate(String cod, String chirias, LocalDate inceput, LocalDate sfarsit) throws Exception
    {
        valideazaInterval(inceput, sfarsit);
        valideazaInchiriere(cod, inceput, sfarsit);
        
        return repoProprietatiInchiriate.inchiriazaProprietate(cod, chirias, inceput, sfarsit);
    }

    /**
     * Anuleaza inchirierea pentru proprietatea cu codul dat
     * in intervalul dat.
     * 
     * @param String cod
     * @param LocalDate inceput
     * @param LocalDate sfarsit
     * @return boolean
     * @throws Exception
     */
    public boolean anuleazaInchiriere(String cod, LocalDate inceput, LocalDate sfarsit) throws Exception
    {
        return repoProprietatiInchiriate.anuleazaInchirere(cod, inceput, sfarsit);
    }

    /**
     * Modifica intervalul in care o proprietate e inchiriata.
     * 
     * @param String cod
     * @param LocalDate inceput
     * @param LocalDate sfarsit
     * @param LocalDate nouInceput
     * @param LocalDate nouSfarsit
     * @return boolean
     * @throws Exception
     */
    public boolean modificaInchiriere(String cod, LocalDate inceput, LocalDate sfarsit,
            LocalDate nouInceput, LocalDate nouSfarsit) throws Exception
    {
        valideazaInterval(nouInceput, nouSfarsit);
        valideazaInchiriere(cod, nouInceput, nouSfarsit);
        
        return repoProprietatiInchiriate.modificaInchirere(cod, inceput, sfarsit, nouInceput, nouSfarsit);
    }

    /**
     * Sterge proprietatea cu codul dat.
     * 
     * @param String cod
     * @return boolean
     * @throws Exception
     */
    public boolean stergeProprietate(String cod) throws Exception
    {
        return repoProprietati.sterge(cod); 
    }

    /**
     * Salveaza proprietatea data.
     * 
     * @param Proprietate proprietate
     * @return boolean
     * @throws Exception
     */
    public boolean adaugaProprietate(Proprietate proprietate) throws Exception
    {
        valideazaProprietate(proprietate);
        
        return repoProprietati.insereaza(proprietate); 
    }

    /**
     * Modifica pretul proprietatii cu codul dat.
     * 
     * @param String cod
     * @param Long pret
     * @return
     * @throws Exception
     */
    public boolean modificaProprietate(String cod, Long pret) throws Exception
    {
        if (pret <= 0) {
            throw new Exception("Pretul trebuie sa fie mai mare decat 0.");
        }
        
        return repoProprietati.modifica(cod, pret); 
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
        return repoProprietatiInchiriate.cautaToateInchiriatePanaAzi(limita, inceput);
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
        return repoProprietatiInchiriate.cautaToateInchiriateDeAzi(limita, inceput);
    }
    
    /**
     * Verifica daca datele primite ca parametri reprezinta un interval valid.
     * 
     * @param LocalDate inceput
     * @param LocalDate sfarsit
     * @throws Exception
     */
    private void valideazaInterval(LocalDate inceput, LocalDate sfarsit) throws Exception
    {
        if (sfarsit.isBefore(inceput)) {
            throw new Exception("Inceputul intervalului nu poate fi mai tarziu decat sfarsitul.");
        }
        
        if (sfarsit.equals(inceput)) {
            throw new Exception("Intervalul nu poate fi egal cu 0.");
        } 
        
        if (inceput.isBefore(LocalDate.now())) {
            throw new Exception("Nu se pot face inchirieri incepand din trecut.");
        }
    }
    
    /**
     * Verifica daca proprietatea are date valide.
     * 
     * @param Proprietate proprietate
     * @throws Exception
     */
    private void valideazaProprietate(Proprietate proprietate) throws Exception 
    {     
        String mesaj = "";
        
        if (proprietate.getCod().length() < 2) {
            mesaj += "Codul trebuie sa aiba cel putin 2 caractere. ";
        }
        
        if (proprietate.getCamere() <= 0) {
            mesaj += "O proprietate trebuie sa aiba cel putin o camera. ";
        }
        
        if (proprietate.getAdresa().length() < 5) {
            mesaj += "Adresa trebuie sa aiba cel putin 5 caractere. ";
        }
        
        if (proprietate.getCartier().length() < 2) {
            mesaj += "Cartierul trebuie sa aiba cel putin 2 caractere. ";
        }
        
        if (!(proprietate.getTip().equals("apartament") || proprietate.getTip().equals("casa de vacanta"))) {
            mesaj += "Acest tip de proprietate nu exista. ";
        }
        
        if (proprietate.getPret() <= 0) {
            mesaj += "Probabil nu vreti sa inchiriati proprietatea gratis. ";
        }
        
        if (!mesaj.isEmpty()) {
            throw new Exception(mesaj);
        }
    }
    
    /**
     * Valideaza ca proprietatea care trebuie inchiriata e disponibila in intervalul ales.
     * 
     * @param String cod
     * @param LocalDate inceput
     * @param LocalDate sfarsit
     * @throws Exception
     */
    public void valideazaInchiriere(String cod, LocalDate inceput, LocalDate sfarsit) throws Exception
    {
        ProprietateInchiriata proprietate = repoProprietatiInchiriate.cauta(cod, inceput, sfarsit);
        
        if (proprietate != null) {
            throw new Exception("Proprietatea este deja inchiriata in intervalul dat!");
        }
    }
}

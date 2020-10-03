package final_project.Model.Hidrator;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

import final_project.Model.ProprietateInchiriata;

public class HidratorProprietateInchiriata
{
    /**
     * Converteste un rand de rezultate din baza de date intr-un obiect de tip ProprietateInchiriata.
     *  
     * @param rezultat
     * @param campuri
     * @return
     * @throws Exception
     */
    public ProprietateInchiriata hidrateaza(ResultSet rezultat, String[] campuri) throws Exception
    {
        ProprietateInchiriata proprietate = new ProprietateInchiriata();
        
        try {
            for (int i = 0, n = campuri.length; i < n; i++) {
                String field = campuri[i];
                Object valoare = rezultat.getObject(campuri[i]);
                
                if (valoare instanceof Date) {
                    valoare = ((Date) valoare).toLocalDate();
                }

                proprietate.set(field, valoare);
            }
        } catch (Exception ex) {
            throw new Exception("Nu am putut hidrata proprietatea inchiriata!");
        }

        return proprietate;
    }
}

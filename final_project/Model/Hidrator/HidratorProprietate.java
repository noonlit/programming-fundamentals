package final_project.Model.Hidrator;

import java.sql.ResultSet;
import final_project.Model.Proprietate;

public class HidratorProprietate
{
    /**
     * Converteste un rand de rezultate din baza de date intr-un obiect de tip Proprietate.
     *  
     * @param rezultat
     * @param campuri
     * @return
     * @throws Exception
     */
    public Proprietate hidrateaza(ResultSet rezultat, String[] campuri) throws Exception
    {
        Proprietate proprietate = new Proprietate();
        
        try {
            for (int i = 0, n = campuri.length; i < n; i++) {
                String field = campuri[i];
                Object valoare = rezultat.getObject(campuri[i]);

                proprietate.set(field, valoare);
            }
        } catch (Exception ex) {
            throw new Exception("Nu am putut hidrata proprietatea!");
        }

        return proprietate;
    }
}

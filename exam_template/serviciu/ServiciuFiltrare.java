package exam_template.serviciu;

import java.util.ArrayList;
import java.util.Iterator;
import exam_template.model.Haina;

public class ServiciuFiltrare
{

    public ArrayList<Haina> filtreaza(ArrayList<Haina> entitati, String tip)
    {
        Iterator<Haina> iterator = entitati.iterator();
        ArrayList<Haina> rezultat = new ArrayList<Haina>();

        while (iterator.hasNext()) {
            Haina entitate = iterator.next();

            if (entitate.getTip().equals(tip)) {
                rezultat.add(entitate);
            }
        }

        return rezultat;
    }
}

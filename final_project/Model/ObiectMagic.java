package final_project.Model;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Clasa de baza pentru modele.
 *
 * Implementeaza metode care ne permit sa aflam 
 * si sa setam valoarea unei proprietati al carei nume il cunoastem.
 * 
 * De exemplu, obiect.get('nume') va returna valoarea proprietatii nume a instantei,
 * iar obiect.set('nume', valoare) va atribui proprietatii nume valoarea data.
 */
public class ObiectMagic
{
    /**
     * Getter magic.
     * 
     * @param String field
     * @return Valoarea pe care o are field-ul.
     */
    public Object get(String field)
    {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(field, Proprietate.class);
            Method getter = pd.getReadMethod();

            return getter.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Setter magic.
     * 
     * @param String field
     * @param value Valoarea pe care vrem sa o atribuim field-ului.
     * @return
     */
    public Object set(String field, Object value)
    {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(field, this.getClass());
            Method setter = pd.getWriteMethod();

            return setter.invoke(this, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

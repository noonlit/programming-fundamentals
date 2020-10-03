package exam_template.model;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import final_project.Model.Proprietate;

public class Entitate
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
            PropertyDescriptor pd = new PropertyDescriptor(field, Entitate.class);
            Method getter = pd.getReadMethod();

            return getter.invoke(this);
        } catch (Exception e) {
            //e.printStackTrace();
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
            //e.printStackTrace();
            return null;
        }
    }
}

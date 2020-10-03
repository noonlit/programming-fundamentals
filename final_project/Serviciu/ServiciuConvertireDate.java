package final_project.Serviciu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converteste un String intr-un LocalDate si viceversa.
 */
public class ServiciuConvertireDate
{
    /**
     * @param String data Asteptata in format yyyy-MM-dd.
     * @return LocalDate
     * @throws Exception 
     */
    public static LocalDate dinStringInLocalDate(String data) throws Exception
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        try {
            return LocalDate.parse(data, formatter);
        } catch (Exception e) {
            throw new Exception("Data invalida!");
        }
    }
    
    /**
     * @param LocalDate data
     * @return String
     */
    public static String dinLocalDateInString(LocalDate data)
    {
        return data.toString();
    }
}

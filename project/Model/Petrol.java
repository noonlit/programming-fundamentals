package project.Model;

/**
 * Clasa care asociaza o tara cu importul sau lunar de petrol.
 */
public class Petrol
{
    private String numeTara = "";
    private long importLunar = 0;

    /**
     * Constructor implicit.
     */
    public Petrol() {}
 
    /**
     * Constructor cu parametri.
     * 
     * @param numeTara
     * @param importLunar
     */
    public Petrol(String numeTara, long importLunar)
    {
        this.numeTara = numeTara;
        this.importLunar = importLunar;
    }
    
    /** 
     * @return String
     */
    public String getNumeTara()
    {
        return numeTara;
    }

    /**
     * @param String numeTara
     */
    public void setNumeTara(String numeTara)
    {
        this.numeTara = numeTara;
    }

    /**
     * @return long
     */
    public long getImportLunar()
    {
        return importLunar;
    }

    /**
     * @param importLunar
     */
    public void setImportLunar(long importLunar)
    {
        this.importLunar = importLunar;
    }
}

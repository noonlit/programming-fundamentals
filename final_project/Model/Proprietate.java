package final_project.Model;

/**
 * Reprezinta o proprietate (de inchiriat).
 */
public class Proprietate extends ObiectMagic
{
    protected Long id;
    protected String tip;
    protected int camere;
    protected String cartier;
    protected String adresa;
    protected float pret;
    protected String cod;
    
    /**
     * Constructor principal/implicit.
     */
    public Proprietate()
    {
        
    }

    /**
     * Constructor cu parametri.
     * 
     * @param id
     * @param cod
     * @param tip
     * @param camere
     * @param cartier
     * @param adresa
     * @param pret
     */
    public Proprietate(Long id, String cod, String tip, int camere, String cartier, String adresa, float pret)
    {
        this.id = id;
        this.setCod(cod);
        this.setTip(tip);
        this.setCamere(camere);
        this.setCartier(cartier);
        this.setAdresa(adresa);
        this.setPret(pret);
    }

    /**
     * @return Long
     */
    public Long getId()
    {
        return id;
    }
    
    /**
     * 
     * @param Long id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return String
     */
    public String getTip()
    {
        return tip;
    }

    /**
     * @param String tip
     */
    public void setTip(String tip)
    {
        this.tip = tip.toLowerCase();
    }

    /**
     * @return int
     */
    public int getCamere()
    {
        return camere;
    }

    /**
     * @param int nrCamere
     */
    public void setCamere(int nrCamere)
    {
        this.camere = nrCamere;
    }

    /**
     * @return String
     */
    public String getCartier()
    {
        return cartier;
    }

    /**
     * @param String cartier
     */
    public void setCartier(String cartier)
    {
        this.cartier = cartier.toLowerCase();
    }
    
    /**
     * @return String
     */
    public String getAdresa()
    {
        return adresa;
    }

    /**
     * @param String adresa
     */
    public void setAdresa(String adresa)
    {       
        this.adresa = adresa.toLowerCase();
    }

    /**
     * @return float
     */
    public float getPret()
    {
        return pret;
    }

    /**
     * @param float pret
     */
    public void setPret(float pret)
    {
        this.pret = pret;
    }

    /**
     * @return String
     */
    public String getCod()
    {
        return cod;
    }

    /**
     * @param String cod
     */
    public void setCod(String cod)
    {
        this.cod = cod.toUpperCase();
    }
    
    /**
     * @return String
     */
    public String toString()
    {
        return id.toString();
    }
}

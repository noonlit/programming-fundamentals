package exam_template.model;

public class Haina extends Entitate
{
    private String denumire;
    private String tip;
    private int stocInitial;
    private int vandute;
    private int pretUnitar;
    
    public Haina(String denumire, String tip, int stocInitial, int vandute, int pretUnitar)
    {
        this.denumire = denumire;
        this.tip = tip;
        this.stocInitial = stocInitial;
        this.vandute = vandute;
        this.pretUnitar = pretUnitar;
    }
    
    public Haina(String denumire, String tip, int stocInitial, int pretUnitar)
    {
        this(denumire, tip, stocInitial, 0, pretUnitar);
    }
    
    public String getDenumire()
    {
        return denumire;
    }
    public void setDenumire(String denumire)
    {
        this.denumire = denumire;
    }
    public int getStocInitial()
    {
        return stocInitial;
    }
    public void setStocInitial(int stocInitial)
    {
        this.stocInitial = stocInitial;
    }
    public int getVandute()
    {
        return vandute;
    }
    public void setVandute(int vandute)
    {
        this.vandute = vandute;
    }
    public int getPretUnitar()
    {
        return pretUnitar;
    }
    public void setPretUnitar(int pretUnitar)
    {
        this.pretUnitar = pretUnitar;
    }
    public String getTip()
    {
        return this.tip;
    }
    public void setTip(String tip)
    {
        this.tip = tip;
    }
}

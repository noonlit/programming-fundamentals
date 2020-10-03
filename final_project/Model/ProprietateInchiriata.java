package final_project.Model;

import java.time.LocalDate;

/**
 * Reprezinta o proprietate inchiriata.
 */
public class ProprietateInchiriata extends Proprietate
{
    protected Long proprietate;
    protected String chirias;
    protected LocalDate inceput;
    protected LocalDate sfarsit;
    
    /**
     * Constructor principal/implicit.
     */
    public ProprietateInchiriata()
    {
    }

    /**
     * Constructor cu parametri. 
     * 
     * @param id
     * @param proprietate
     * @param inceput
     * @param sfarsit
     */
    public ProprietateInchiriata(Long id, Proprietate proprietate, LocalDate inceput, LocalDate sfarsit)
    {
        super(id, proprietate.getCod(), proprietate.getTip(), 
                proprietate.getCamere(), proprietate.getCartier(), 
                proprietate.getAdresa(), proprietate.getPret());
        
        this.setProprietate(proprietate.getId());
        this.setInceput(inceput);
        this.setSfarsit(sfarsit);
    }

    /**
     * @return Long ID-ul proprietatii originale
     */
    public Long getProprietate()
    {
        return proprietate;
    }

    /**
     * @param Long proprietate_id
     */
    public void setProprietate(Long proprietate)
    {
        this.proprietate = proprietate;
    }

    /**
     * @return LocalDate
     */
    public LocalDate getInceput()
    {
        return inceput;
    }

    /**
     * @param LocalDate inceput
     */
    public void setInceput(LocalDate inceput)
    {
        this.inceput = inceput;
    }

    /**
     * @return LocalDate
     */
    public LocalDate getSfarsit()
    {
        return sfarsit;
    }

    /**
     * @param LocalDate sfarsit
     */
    public void setSfarsit(LocalDate sfarsit)
    {
        this.sfarsit = sfarsit;
    }
    
    /**
     * @return String
     */
    public String getChirias()
    {
        return chirias;
    }

    /**
     * @param String chirias
     */
    public void setChirias(String chirias)
    {
        this.chirias = chirias;
    }
}

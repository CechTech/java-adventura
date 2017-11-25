package logika;

/**
 * Třída Postava - vytváří postavy, se kterými může hráč pracovat
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jiří Čech
 * @version 8.1.2017
 */
public class Postava
{
    private String jmeno;
    private String rec;
    private String obrazek;

    /***************************************************************************
     * Konstruktor třídy
     */
    public Postava(String jmeno, String rec, String obrazek)
    {
        this.jmeno = jmeno;
        this.rec = rec;
        this.obrazek = obrazek;
    }

    /**
     * Vrací jméno postavy.
     */
    public String getJmeno() {
        return jmeno; 
    }
    
    /**
     * Vrací dialog s postavou.
     */
    public String getRec() {
        return rec; 
    }

    /**
     * Vrací obrázek postavy.
     */
    public String getObrazek() {
        return obrazek;
    }
}

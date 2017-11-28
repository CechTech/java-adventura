package logika;

/**
 * Třída Vec - vytváří věci, se kterými může hráč pracovat
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jiří Čech
 * @version 8.1.2017
 */
public class Vec
{
    private String nazev;
    private boolean prenositelna;
    boolean pouzitelna;
    private String obrazek;

    /***************************************************************************
     *  Konstruktor třídy
     */
    Vec(String nazev, boolean prenositelna, boolean pouzitelna, String obrazek)
    {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.pouzitelna = pouzitelna;
        this.obrazek = obrazek;
    }
    
    /**
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * @return přenositelnost
     */
    boolean isPrenositelna() {
        return prenositelna;
    }

    /**
     * @return obrázek věci
     */
    public String getObrazek() {
        return obrazek;
    }
}

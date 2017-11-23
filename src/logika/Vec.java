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
    private String popis;
    private boolean prenositelna;
    public boolean pouzitelna;
    private String obrazek;
    
    /***************************************************************************
     *  Konstruktor třídy
     */
    public Vec(String nazev, String popis, boolean prenositelna, boolean pouzitelna, String obrazek)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.pouzitelna = pouzitelna;
        this.obrazek = obrazek;
    }
    
    /**
     * Vrací název věci
     */
    public String getNazev() {
        return nazev;
    }
    
    /**
     * Vrací popis věci
     */
    public String getPopis() {
        return popis;
    }
    
    /**
     * Vrací, zda je věc přenositelná
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }
    
    /**
     * Vrací, zda je věc použitelná
     */
    public boolean isPouzitelna() {
        return pouzitelna;
    }

    /**
     * @return the obrazek
     */
    public String getObrazek() {
        return obrazek;
    }
}

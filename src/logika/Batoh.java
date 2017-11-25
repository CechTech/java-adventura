package logika;

import java.util.Map;
import java.util.HashMap;

/**
 * Třída Batoh - vytváří použitelný inventář pro hráče
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jiří Čech
 * @version 8.1.2017
 */
public class Batoh {
    // Nastaví počet volných míst v batohu
    private static final int SLOTY = 2;
    public Map<String, Vec> seznamVeci;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public Batoh()
    {
        seznamVeci = new HashMap<>();
    }

    /**
     * @return je místo v batohu
     */
    public boolean jeMistoVBatohu() {
        if(getSeznamVeci().size() < SLOTY) {
            return true;
        } else {
            return false;
        }
    }

    public boolean vlozVec(Vec vec) {
        if (jeMistoVBatohu() && vec.isPrenositelna()) {
            getSeznamVeci().put(vec.getNazev(), vec);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Vyhledá věc v batohu
     * 
     * @return true když ji najde
     */
    public boolean obsahujeVecVBatohu (String jmenoVeci) {
        return getSeznamVeci().containsKey(jmenoVeci);
    }

    /**
     * Vypíše věci, které jsou v batohu
     */
    public String nazvyVeciVBatohu() {
        String nazvy = "Věci v batohu: ";
        for (String jmenoVeci : getSeznamVeci().keySet()) {
            nazvy += jmenoVeci + " ";
        }
        return nazvy;
    }

    /**
    * Odebere věc z batohu
    */
    public Vec vyhodZBatohu(String nazev) {        
        Vec vyhozenaVec = null;
        if (getSeznamVeci().containsKey(nazev)) {
            vyhozenaVec = getSeznamVeci().get(nazev);
            getSeznamVeci().remove(nazev);
        }
        return vyhozenaVec;
    }

    /**
     * @return the seznamVeci
     */
    public Map<String, Vec> getSeznamVeci() {
        return seznamVeci;
    }
}

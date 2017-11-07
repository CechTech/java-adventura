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
public class Batoh
{    
    private static final int SLOTY = 2; // Nastaví počet volných míst v batohu
    public Map<String, Vec> seznamVeci;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public Batoh()
    {
        seznamVeci = new HashMap<>();
    }

    /**
     * Přidá věc do batohu, pokud se tam vejde
     * 
     * @return 1 když se věc vloží
     * @return 2 pokud je věc nepřenositelná
     * @return 3 pokud není dostatek místa v batohu
     */
    public int vlozVec(Vec vec) {
        if (seznamVeci.size() < SLOTY && vec.isPrenositelna()) {
            seznamVeci.put(vec.getNazev(), vec);
            return 1;
        } else if (!vec.isPrenositelna()) {
            return 2;
        }
        return 3;
    }

    /**
     * Vyhledá věc v batohu
     * 
     * @return true když ji najde
     */
    public boolean obsahujeVecVBatohu (String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }

    /**
     * Vypíše věci, které jsou v batohu
     */
    public String nazvyVeciVBatohu() {
        String nazvy = "Věci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()) {
            nazvy += jmenoVeci + " ";
        }
        return nazvy;
    }
    
    /**
    * Odebere věc z batohu
    */
    public Vec vyhodZBatohu(String nazev) {        
        Vec vyhozenaVec = null;
        if (seznamVeci.containsKey(nazev)) {
            vyhozenaVec = seznamVeci.get(nazev);
            seznamVeci.remove(nazev);
        }
        return vyhozenaVec;
    }
}

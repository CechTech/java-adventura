package logika;

/*******************************************************************************
 * Třída PrikazVezmi implementuje pro hru příkaz vezmi.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jiří Čech
 * @version   8.1.2017
 */
public class PrikazVezmi implements IPrikaz
{
    private static final String NAZEV = "vezmi";
    
    private HerniPlan plan;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazVezmi(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "vezmi". Zkouší vzít zadanou věc a vložit ji do batohu. Pokud
     *  je věc nepřenositelná, nebo není v aktuálním prostoru, vypíše se chybové hlášení.
     *  
     *  @param parametry - jméno věci, kterou chceme vzít
     *  
     *  @return zpráva, která vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Kontroluje uživatelem zadaný parametr k příkazu vezmi
        if (parametry.length == 0) {
            return "Nevím, co vzít.\n";
        }

        // Získá uživatelem zadanou věc
        String nazev = parametry[0];
        
        // Získá batoh a věc z aktuálního prostoru
        Vec vec = plan.getAktualniProstor().odeberVec(nazev);
        Batoh batoh = plan.getBatoh();
        
        // Vrátí hlášku, pokud věc nenajde
        if (vec == null) {
            return "To tady není";
        }

        /*
        * Volá metodu batoh.vlozVec() a na základě její návratové hodnoty
        * vyhodí hlášku:
        * @return 1 když se věc vloží
        * @return 2 pokud je věc nepřenositelná
        * @return 3 pokud není dostatek místa v batohu 
        */
        if (batoh.vlozVec(vec) == 1) {
            return nazev + " vloženo do batohu \n"
                + batoh.nazvyVeciVBatohu(); // Vypíše věci v batohu
        } else if (batoh.vlozVec(vec) == 2){
            plan.getAktualniProstor().vlozVec(vec);
            return nazev + " si nemůžeš vzít";          
        } else {
            plan.getAktualniProstor().vlozVec(vec);
            return "Tvůj batoh je plný. Něco vyhoď. \n"
                + batoh.nazvyVeciVBatohu(); // Vypíše věci v batohu
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}

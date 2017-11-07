package logika;

/*******************************************************************************
 * Třída PrikazVyhod implementuje pro hru příkaz vyhod.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jiří Čech
 * @version   8.1.2017
 */
public class PrikazVyhod implements IPrikaz
{
    private static final String NAZEV = "vyhod";
    private HerniPlan plan;
    private Batoh batoh;
    
    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazVyhod(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }
    
    /**
     *  Provádí příkaz "vyhod". Zkouší vyhodit zadanou věc z batohu. Pokud
     *  je věc v batohu, odebere se a vloží do aktualního prostoru.
     *  Pokud zadaná věc není v batohu, vypíše se chybové hlášení.
     *  
     *  @param parametry - jméno věci, kterou chceme vyhodit
     *  
     *  @return zpráva, která vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Kontroluje uživatelem zadaný parametr k příkazu vyhod
        if (parametry.length == 0) {
            return "Nevím co mám vyhodit";
        }

        // Získá uživatelem zadanou věc
        String nazev = parametry[0];
        
        // Získá batoh a pokusí se vyhodit zadanou věc
        this.batoh = plan.getBatoh();
        Vec vec = batoh.vyhodZBatohu(nazev);
        
        // Kontroluje přítomnost věci v batohu a vyhazuje věc
        if (vec == null) {
            return "Tohle v batohu nemáš";
        } else {
            plan.getAktualniProstor().vlozVec(vec); // Vyhodí věc z batohu
            return "Vyhodil si " + nazev;
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

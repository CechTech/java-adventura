package logika;

/*******************************************************************************
 * Třída PrikazBatoh implementuje pro hru příkaz batoh.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jiří Čech
 * @version   8.1.2017
 */
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";    
    private HerniPlan plan;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazBatoh(HerniPlan plan)
    {
        this.plan = plan;
    }
    
    /**
     *  Provádí příkaz "batoh". Vypisuje seznam věcí uložených v batohu
     *
     *  @return obsah batohu
     */
    @Override
    public String proved(String... parametry) {
        Batoh batoh = plan.getBatoh(); // Získá batoh
        return batoh.nazvyVeciVBatohu(); // Zavolá metodu nazvyVeciVBatohu()
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
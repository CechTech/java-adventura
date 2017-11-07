package logika;

/*******************************************************************************
 * Třída PrikazMluv implementuje pro hru příkaz mluv.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jiří Čech
 * @version   8.1.2017
 */
public class PrikazMluv implements IPrikaz
{
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    
    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazMluv(HerniPlan plan)
    {
        this.plan = plan;
    }
    
    /**
     *  Provádí příkaz "mluv". Zkouší promluvit se zadanou postavou. Pokud postava
     *  existuje, vrátí její řeč. Pokud zadaná postava není
     *  v prostoru, vypíše se chybové hlášení.
     *
     *  @param parametry - jméno postavy, se kterou chceme mluvit.
     *  
     *  @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Kontroluje uživatelem zadaný parametr k příkazu mluv
        if (parametry.length == 0) {
            return "S kým chceš mluvit?";
        }
        
        // Získá zadané jméno postavy
        String jmeno = parametry[0]; 
        
        // Získá aktuální prostor a pokusí se najít zadanou postavu
        Prostor aktualniProstor = plan.getAktualniProstor();
        Postava postava = aktualniProstor.najdiPostavu(jmeno);
        
        // Vrátí hlášku, pokud postavu nenajde
        if (postava == null) {
            return "To nejde. Není tu.";
        }
        
        // Mění řeč přesunutých postav v prostoru hrad
        if (aktualniProstor.getNazev().equals("hrad")) {
            return "Společenstvo Králoství Lihovin: Ještě tu nejsme všichni \n"
                + "Společenstvo Králoství Lihovin: Sežeň ostatní, my tu počkáme";
        }
        
        // Zavolá metodu getRec() pro uživatelem zadanou postavu
        return postava.getRec();
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

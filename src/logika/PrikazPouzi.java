package logika;

/*******************************************************************************
 * Třída PrikazPouzi implementuje pro hru příkaz pouzi.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jiří Čech
 * @version   8.1.2017
 */
public class PrikazPouzi implements IPrikaz
{
    private static final String NAZEV = "pouzi";
    private HerniPlan plan;
    private Batoh batoh;
    private Hra hra;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazPouzi(HerniPlan plan, Hra hra)
    {
        this.plan = plan;
        this.batoh = batoh;
        this.hra = hra;
    }
    
    /**
     *  Provádí příkaz "pouzi". Zkouší použít zadanou věc v batohu. Pokud předmět
     *  existuje, vrátí příslušnou operaci, případně hlášku. Pokud zadaná věc není
     *  v batohu, vypíše se chybové hlášení.
     *
     *  @param parametry - název věci, která se má použít
     *  
     *  @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Kontroluje uživatelem zadaný parametr k příkazu pouzi
        if (parametry.length == 0) {            
            return "Nevím co použít";
        }
        
        // Získá uživatelem zadanou věc
        String vecKPouziti = parametry[0];
        
        // Získá aktuální prostor
        Prostor aktualniProstor = plan.getAktualniProstor();
        
        // Kontroluje přítomnost věci v batohu
        if (!plan.batoh.obsahujeVecVBatohu(vecKPouziti)) {
            return "Tuhle věc v batohu nemáš \n" 
                + plan.batoh.nazvyVeciVBatohu();
        }
        
        // Kontroluje, jestli jde daná věc použít
        if (!plan.batoh.seznamVeci.get(vecKPouziti).pouzitelna)
        {
            return "Tahle věc nejde použít";
        }

        // Následuje seznam věcí, které při použití spustí nějakou činnost
        
        // Pro věc kouzelnyRoh, který přidá postavu jelenJagermeister
        // Funguje pouze v prostoru kouzenyLes
        if (vecKPouziti.equals("kouzelnyRoh"))
        {
            if (aktualniProstor.getNazev().equals("kouzelnyLes")) {               
                aktualniProstor.vlozPostavu(plan.jelenJagermeister); // Vloží postavu
                plan.batoh.vyhodZBatohu("kouzelnyRoh"); // Po použití věc vyhodí
                return "Nyní můžeš mluvit s postavou jelenJagermeister";              
            } else {
                return "Zatroubil jsi, ale nic se nestalo. Jsi na správném místě?";                
            }                                
        }
        
        // Pro věc piratskaMince, která odemče prostor ledoveOstrovy
        // Funguje pouze v prostoru pristavPiratu
        if (vecKPouziti.equals("piratskaMince"))
        {
            if (aktualniProstor.getNazev().equals("pristavPiratu")) {                
                plan.ledoveOstrovy.zamceno = false; // Odemče prostor
                plan.batoh.vyhodZBatohu("piratskaMince"); // Po použití věc vyhodí
                return "Předal jsi minci Kapitánu Bucanerovi. \n"
                    + "Nyní tě může odvézt na Ledové ostrovy.";              
            } else {
                return "Tady nikdo o tvoji minci nestojí. Zkus to jinde.";                
            }                                
        }
        
        // Pro věc horolezeckeVybaveni, která odemče prostor mlzneHory
        // Funguje v každém prostoru
        if (vecKPouziti.equals("horolezeckeVybaveni"))
        {              
            plan.mlzneHory.zamceno = false;  // Odemče prostor
            plan.batoh.vyhodZBatohu("horolezeckeVybaveni"); // Po použití věc vyhodí
            return "Nasadil sis horolezeckou výbavu. \n"
                + "Nyní můžeš lézt Mlžnými horami.";                              
        }
        
        // Pro věc mapa, která textově vypíše prostory hry
        if (vecKPouziti.equals("mapa"))
        {              
            return "Od hradu \n"
                + "Sever: Přístav pirátů -> Ledové ostrovy \n"
                + "Západ: Ovocné sady \n"
                + "Východ: Kouzelný les -> Mlžné hory \n"
                + "Jižně od Kouzelného lesu: Poušt zapomnění";                              
        }
        
        // Pro věc mec, která kvůli nehodě ukončí hru
        if (vecKPouziti.equals("mec"))
        {              
            hra.setKonecHry(true); // Ukončí hru
            return "Hrál sis s mečem, nemotorně zavrávoral a napíchl se na něj. \n"
                + "Nalitému zbraně do ruky nepatří!";                              
        }   
        return "Nevím, jak tohle použít";
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

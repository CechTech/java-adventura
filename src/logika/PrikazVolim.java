package logika;

/*******************************************************************************
 * Třída PrikazVolim implementuje pro hru příkaz volim.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jiří Čech
 * @version   8.1.2017
 */
public class PrikazVolim implements IPrikaz
{
    private static final String NAZEV = "volim";
    private static final String PAUZA = "Po pár hodinách...\n";
    private static final String MOZNOSTI = "Vyber A, B, nebo C";
    private HerniPlan plan;
    private Hra hra;
    public int ziskaniVazalove = 0;

    /***************************************************************************
     *  Konstruktor třídy
     */
    public PrikazVolim(HerniPlan plan, Hra hra)
    {
        this.plan = plan;
        this.hra = hra;
    }
    
    /**
     *  Provádí příkaz "volim". Hráč zvolí odpověď na položenou otázku. 
     *  Správné odpovědi jsou určené na základě aktuálního prostoru. 
     *  Pokud hráč uhádne, připočítá se mu získaný vazal a hra vypíše hlášení a postup.
     *  Pokud hráč špatně odpoví, prohraje, hra se ukončí a vypíše hlášení.
     *       
     *  @param parametry - Možnosti odpovědí A, B, nebo C
     *  
     *  @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String proved(String... parametry) {
        // Kontroluje uživatelem zadaný parametr k příkazu volim
        if (parametry.length == 0) {
            return "Co chceš zvolit?";
        }
        
        // Získá zadanou odpověď na otázku
        String moznost = parametry[0];
        
        // Získá aktuální prostor
        Prostor aktualniProstor = plan.getAktualniProstor();
        
        // Následují podmínky, které určí správné odpovědi a následné činnosti
        
        // Pro prostor pristavPiratu je správná možnost B
        // Funguje pouze v prostoru pristavPiratu
        if (aktualniProstor.getNazev().equals("pristavPiratu")) {
            if (moznost.equals("B")) {
                plan.pristavPiratu.odeberPostavu("kapitanMorgan");
                plan.hrad.vlozPostavu(plan.kapitanMorgan);
                ziskaniVazalove += 1;
                return "Kapitán Morgan: No výborně! Tak se spolu hned napijem. \n\n"
                    + PAUZA
                    + "Kapitán Morgan: Něco ti povím. Vždycky jsem tě měl rád. \n"
                    + "Kapitán Morgan: Byl by z tebe ten nejprohnilejší pirát pod sluncem. \n"
                    + "Kapitán Morgan: Ale tvůj tatík mi nikdy nedovolil se tě ujmout. \n"
                    + "Kapitán Morgan: Třeba v příštím životě. Teď si musím na chvilku odskočit. \n"
                    + "Kapitán Morgan: Čekej mě na hradě. Ahoj! \n\n"
                    + hracVyhral();
            } else if (moznost.equals("A") || moznost.equals("C")) {
                hra.setKonecHry(true); // Ukončí hru
                return "Kapitán Morgan: Ty nevíš?! Tak jednoduchý a naše princátko to neví! \n"
                    + "Kapitán Morgan: Nikdy nebudeš králem! Ha! \n";
            } else {
                return MOZNOSTI;
            }
        }
        
        // Pro prostor ovocneSady je správná možnost A
        // Funguje pouze v prostoru ovocneSady
        if (aktualniProstor.getNazev().equals("ovocneSady")) {
            if (moznost.equals("A")) {
                plan.ovocneSady.odeberPostavu("rudolfJelinek");
                plan.hrad.vlozPostavu(plan.rudolfJelinek);
                ziskaniVazalove += 1;
                return "Rudolf Jelínek: Věděl jsem, že uspějete! To se musí hned zapít! \n\n"
                    + PAUZA
                    + "Rudolf Jelínek: Pamatuji už spoustu generací. Každý zde objevil pravé kouzlo domácí pálenky. \n"
                    + "Rudolf Jelínek: Vaši prarodiče, rodiče a nakonec i vy. Vzpomínám, jak jsem vás našel v bezvědomí pod třešní. \n"
                    + "Rudolf Jelínek: Vskutku nádherné vzpomínky. Doufám, že i vaše děti zde najdou chuť do života, tak jako vy. \n"
                    + "Rudolf Jelínek: Děkuji princi za návštěvu a uvidíme se na hradě. \n\n"
                    + hracVyhral();
            } else if (moznost.equals("B") || moznost.equals("C")) {
                hra.setKonecHry(true); // Ukončí hru
                return "Rudolf Jelínek: Ale to ne! Copak jsem vás nic nenaučil? \n"
                    + "Rudolf Jelínek: Je mi líto, ale nemůžu vás uznat za svého krále. \n";
            } else {
                return MOZNOSTI;
            }
        }
       
        // Pro prostor kouzelnyLes je správná možnost B
        // Funguje pouze v prostoru kouzelnyLes
        if (aktualniProstor.getNazev().equals("kouzelnyLes")) {
            if (moznost.equals("B")) {
                plan.kouzelnyLes.odeberPostavu("jelenJagermeister");
                plan.hrad.vlozPostavu(plan.jelenJagermeister);
                ziskaniVazalove += 1;
                return "Jelen Jägermeister: Samozřejmě! Myslivče! Dones všechny kouzelné likéry, jde se slavit! \n\n"
                    + PAUZA
                    + "Jelen Jägermeister: Abrelour měl vždycky slabost pro bylinky. \n"
                    + "Jelen Jägermeister: Vidím, že jsi skutečně synem svého otce. \n"
                    + "Jelen Jägermeister: Jsem rád, že naše spojenectví dále přetrvá. \n"
                    + "Jelen Jägermeister: Brzi se shledáme na hradě. \n\n"
                    + hracVyhral();
            } else if (moznost.equals("A") || moznost.equals("C")) {
                hra.setKonecHry(true); // Ukončí hru
                return "Jelen Jägermeister: Tolik určitě ne. Nemůžeš být králem tohoto lesa. Sbohem \n";
            } else {
                return MOZNOSTI;
            }
        }
        
        // Pro prostor poustZapomneni je správná možnost C
        // Funguje pouze v prostoru poustZapomneni
        if (aktualniProstor.getNazev().equals("poustZapomneni")) {
            if (moznost.equals("C")) {
                plan.poustZapomneni.odeberPostavu("joseCuervo");
                plan.hrad.vlozPostavu(plan.joseCuervo);
                ziskaniVazalove += 1;
                return "José Cuervo: Skvělé! Hned ji ochutnáme! \n\n"
                    + PAUZA
                    + "José Cuervo: V těchto pouštích se zapomnělo jíž mnoho udatných mužů. \n"
                    + "José Cuervo: Tvůj otec ale nikdy. Ať se vydal sebedál, vždy se vrátil. \n"
                    + "José Cuervo: V pouštích nikdy nebude zapomenut a ty také ne. \n"
                    + "José Cuervo: Uvidíme se na hradě. \n\n"
                    + hracVyhral();
            } else if (moznost.equals("A") || moznost.equals("B")) {
                hra.setKonecHry(true); // Ukončí hru
                return "José Cuervo: To myslíš vážně?! Běž pryč a už se nevracej! \n";
            } else {
                return MOZNOSTI;
            }
        }
        
        // Pro prostor ledoveOstrovy je správná možnost C
        // Funguje pouze v prostoru ledoveOstrovy
        if (aktualniProstor.getNazev().equals("ledoveOstrovy")) {
            if (moznost.equals("C")) {
                plan.ledoveOstrovy.odeberPostavu("roaldAmundsen");
                plan.hrad.vlozPostavu(plan.roaldAmundsen);
                ziskaniVazalove += 1;
                return "Roald Amundsen: Jistě! Hned ji přinesu a vodku taky! \n\n"
                    + PAUZA
                    + "Roald Amundsen: Tyto končiny jsou velmi opuštěné. \n"
                    + "Roald Amundsen: Je tady málo příležitostí se s kým dobře napít. \n"
                    + "Roald Amundsen: O to jsou tyto chvíle silnější a magičtější. \n"
                    + "Roald Amundsen: Děkuji princi. Uvidíme se na hradě. \n\n"
                    + hracVyhral();                                 
            } else if (moznost.equals("A") || moznost.equals("B")) {
                hra.setKonecHry(true); // Ukončí hru
                return "Roald Amundsen: Tohle není správně. Jsem smutný a nikam nepudu. \n";
            } else {
                return MOZNOSTI;
            }
        }
        
        // Pro prostor mlzneHory je správná možnost A
        // Funguje pouze v prostoru mlzneHory
        if (aktualniProstor.getNazev().equals("mlzneHory")) {
            if (moznost.equals("A")) {
                plan.mlzneHory.odeberPostavu("strazceBeefeater");
                plan.hrad.vlozPostavu(plan.strazceBeefeater);
                ziskaniVazalove += 1;
                return "Strážce Beefeater: Samožřejmě! Vydechni po cestě, já zatím dojdu pro panáky. \n\n"
                    + PAUZA
                    + "Strážce Beefeater: Málokdo se odváží vylézt na tyto hory. \n"
                    + "Strážce Beefeater: Jedním z návštěvníků byl i tvůj otec, tak jako dnes ty. \n"
                    + "Strážce Beefeater: Velice se těším, na toho kdo přijde po tobě. \n"
                    + "Strážce Beefeater: Shledáme se na hradě princi. \n\n"
                    + hracVyhral();
            } else if (moznost.equals("B") || moznost.equals("C")) {
                hra.setKonecHry(true); // Ukončí hru
                return "Strážce Beefeater: Neuspěl jsi. Tvá náročná cesta byla nadarmo. \n";
            } else {
                return MOZNOSTI;
            }
        }        
        return "Nevím co tím myslíš";
    }   
    
    /**
     *  Metoda kontroluje průběh hry. Jakmile hráč získá 6 vazalů, vyhraje
     *  a hra se ukončí.
     *  
     *  @return zpráva, kterou vypíše hra hráči
     */
    public String hracVyhral() {
        int zbyvajiciVazalove = 6 - ziskaniVazalove; // Spočítá, kolik chybí vazalů
        if (ziskaniVazalove == 6) {
            hra.setKonecHry(true); // Ukončí hru
            return "Vyhrál jsi. Stal jses největším ožralou v králoství a taky králem.";
        }
        return "Získal jsi vazala! Ještě ti chybí " + zbyvajiciVazalove + ".";
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

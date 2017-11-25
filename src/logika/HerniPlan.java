package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 *
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject {
    // Postavy a prostory jsou využíváný i v jiných třídách
    // Musí být veřejné

    private Prostor viteznyProstor;
    private List<Observer> listObserveru = new ArrayList<Observer>();

    public Prostor aktualniProstor;
    public Prostor ledoveOstrovy;
    public Prostor mlzneHory;
    public Prostor hrad;
    public Prostor pristavPiratu;
    public Prostor ovocneSady;
    public Prostor kouzelnyLes;
    public Prostor poustZapomneni;
    public Postava jelenJagermeister;
    public Postava rudolfJelinek;
    public Postava kapitanMorgan;
    public Postava roaldAmundsen;
    public Postava strazceBeefeater;
    public Postava joseCuervo;

    Batoh batoh;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

        batoh = new Batoh();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // Vytvoří jednotlivé prostory
        hrad = new Prostor("hrad","hrad, kde sídlí královská rodina", 120, 129);
        kouzelnyLes = new Prostor("kouzelnyLes", "Kouzelný les, kde můžeš potkat všechna kouzelná zvířata", 365, 209);
        mlzneHory = new Prostor("mlzneHory","Mlžné hory, vysoké pohoří navěky zahalené v mlze kvalitního Absinthu a Ginu", 398, 399);
        poustZapomneni = new Prostor("poustZapomneni","Poušť zapomnění, kde tequila připravila o rozum již mnoho cestovatelů", 139, 109);
        ovocneSady = new Prostor("ovocneSady","Ovocné sady, domov a zásobárna domácí pálenky", 89, 198);
        pristavPiratu = new Prostor("pristavPiratu","Přístav pirátů, místo kde existuje jen jedinné pravidlo: Pij rum!", 39, 289);
        ledoveOstrovy = new Prostor("ledoveOstrovy","Ledové ostrovy, nejlepší vychlazenou whiskey a vodku najdeš na severu", 10, 387);

        // Zamkne vybrané prostory
        ledoveOstrovy.setZamek(true);
        mlzneHory.setZamek(true);

        // Přiřazují se průchody mezi prostory (sousedící prostory)
        hrad.setVychod(kouzelnyLes);
        hrad.setVychod(pristavPiratu);
        hrad.setVychod(ovocneSady);
        kouzelnyLes.setVychod(hrad);
        kouzelnyLes.setVychod(mlzneHory);
        kouzelnyLes.setVychod(poustZapomneni);
        mlzneHory.setVychod(kouzelnyLes);
        poustZapomneni.setVychod(kouzelnyLes);
        ovocneSady.setVychod(hrad);
        pristavPiratu.setVychod(hrad);
        pristavPiratu.setVychod(ledoveOstrovy);
        ledoveOstrovy.setVychod(pristavPiratu);

        aktualniProstor = hrad;  // hra začíná ve hradě

        // Vytvoříme věci
        Vec penize = new Vec("penize", "Peněz není nikdy dost", true, false, "/zdroje/mec.png");
        Vec mapa = new Vec("mapa", "Mapa Králoství Lihovin", true, true, "/zdroje/mec.png");
        Vec koruna = new Vec("koruna", "Korunovační klenoty", false, false, "/zdroje/mec.png");
        Vec piratskaMince = new Vec("piratskaMince", "Pro tuhle jsou piráti ochotni riskovat život", true, true, "/zdroje/mec.png");
        Vec kouzelnyRoh = new Vec("kouzelnyRoh", "Přivolá kouzelného jelena", true, true, "/zdroje/mec.png");
        Vec horolezeckeVybaveni = new Vec("horolezeckeVybaveni", "Bez něj se do hor nedá dostat", true, true, "/zdroje/mec.png");
        Vec prazdnaLahev = new Vec("prazdnaLahev", "Láhev od pálenky se špuntem", true, false, "/zdroje/mec.png");
        Vec svestky = new Vec("svestky", "Švestky k jídlu, nebo na slivovici", false, false, "/zdroje/mec.png");
        Vec lahevMyslivce = new Vec("lahevMyslivce", "Stará myslivecká", false, false, "/zdroje/mec.png");
        Vec lahevJagermeistera = new Vec("lahevJagermeistera", "Jägermeister", false, false, "/zdroje/mec.png");
        Vec sudyRumu = new Vec("sudyRumu", "Obří zásoby pirátského nápoje", false, false, "/zdroje/mec.png");
        Vec kaktusy = new Vec("kaktusy", "Všude jsou samé kaktusy", false, false, "/zdroje/mec.png");
        Vec mec = new Vec("mec", "Smrtonosná zbraň", true, true, "/zdroje/mec.png");

        // Vložíme věci do prostorů
        hrad.vlozVec(penize);
        hrad.vlozVec(mapa);
        hrad.vlozVec(koruna);
        hrad.vlozVec(prazdnaLahev);
        hrad.vlozVec(mec);
        ovocneSady.vlozVec(prazdnaLahev);
        ovocneSady.vlozVec(svestky);
        kouzelnyLes.vlozVec(lahevMyslivce);
        kouzelnyLes.vlozVec(lahevJagermeistera);
        poustZapomneni.vlozVec(piratskaMince);
        poustZapomneni.vlozVec(kaktusy);
        pristavPiratu.vlozVec(sudyRumu);
        ledoveOstrovy.vlozVec(kouzelnyRoh);
        ledoveOstrovy.vlozVec(horolezeckeVybaveni);

        // Vytvorime nové postavy
        jelenJagermeister = new Postava ("jelenJagermeister", "Jelen Jägermeister: Proč mě voláš princi Pivoni? \n"
                + "Pivoň: Pane lesů, můj otec král Abrelour zemřel a já mám nastoupit na jeho místo. \n"
                + "Pivoň: Žádám tě proto, aby jsi se dostavil na korunovaci a odpřísáhl mi věrnost. \n"
                + "Jelen Jägermeister: Tvůj otec vždy chránil kouzla a tajemství tohoto lesa. \n"
                + "Jelen Jägermeister: Doufám, že toto vzájemně prospěšné spojenectví nadále vydrží. \n"
                + "Jelen Jägermeister: Musím ti ale položit důležitou otázku. \n\n"
                + "Jelen Jägermeister: Z kolika bylinek vyrábím svůj kouzelný likér? \n"
                + "Možnosti: A - 32, B - 56, C - 64 \n");
        Postava myslivec = new Postava ("myslivec", "Pivoň: Myslivče! Kde najdu pána lesa? \n"
                + "Starý Myslivec: Je hluboko hluboko v lese, drahý princi. Vratí se až za dlouho. \n"
                + "Starý Myslivec: Jestli ho potřebuješ hned, budeš muset zatroubit na kouzelný roh. \n"
                + "Pivoň: Kde ho najdu? \n"
                + "Starý Myslivec: Jeden by měl být někde na severu. V zemi Ledových ostrovů \n"
                + "Pivoň: Děkuji ti za radu \n");
        strazceBeefeater = new Postava ("strazceBeefeater", "Strážce Beefeater: Vítej princi. Již tě očekávám. \n"
                + "Strážce Beefeater: Velice mě mrzí smrt tvého otce. Byl to dobrý člověk a skvělý král. \n"
                + "Strážce Beefeater: Jistě jsi velmi vyčerpaný po cestě. Zde můžeš setrvat, jak dlouho budeš chtít. \n"
                + "Pivoň: Děkuji ti, ale popravdě mám dost na spěch. \n"
                + "Strážce Beefeater: Samozřejmě, Králoství Lihovin potřebuje svého krále. \n"
                + "Strážce Beefeater: Mlžné hory jsou tvé, stačí mi jen zodpovědět jednu otázku. \n\n"
                + "Strážce Beefeater: Čím se vyznačuje typ ginu zvaný Genever? \n"
                + "Možnosti: A - vyráběn periodickou destilací, B - pochází z Ženevy, C - vyráběn kontinuální destilací \n");
        joseCuervo = new Postava ("joseCuervo", "José Cuervo: Sám pan princ zavítal do nehostinných pouští. \n"
                + "José Cuervo: Co tu chceš? Ten starej mezek, na jehož jméno si nevzpomínám, zase něco potřebuje? \n"
                + "Pivoň: Nechce, je mrtvej. Ale já chci, aby jsi přišel na mou korunovaci, poklekl a odpřísáhl věrnost. \n"
                + "José Cuervo: Á, to mě mrzí. Nevěděl jsem. Nuže dobrá, ale potřebuji něco vědět. \n\n"
                + "José Cuervo: Z jakého kaktusu se vyrábí pravá tequila? \n"
                + "Možnosti: A - peyote, B - hatiora, C - agáve \n");
        rudolfJelinek = new Postava ("rudolfJelinek", "Rudolf Jelínek: Vítejte princi. Čemu vděčím za tak vzácnou návštěvu? \n"
                + "Rudolf Jelínek: Jaká pálenka to dnes bude? Hruška? Švestka? Nebo snad ořech? \n"
                + "Pivoň: Drahý Rudolfe. Beru všechny, jako vždy. Tohle ale není důvod mé návštěvy. \n"
                + "Rudolf Jelínek: No ovšem. Úpřimnou soustrast, všichni jsme vašeho otce milovali. \n"
                + "Rudolf Jelínek: Produkce prvotřídních pálenek nebude narušena, ale stejně se musím zeptat. \n\n"
                + "Rudolf Jelínek: Proč má mít správná pálenka alespoň 45 %? \n"
                + "Možnosti: A - slabší může být kyselá, B - slabší nejde vypálit, C - slabší by nikdo nepil \n");
        kapitanMorgan = new Postava ("kapitanMorgan", "Kapitán Morgan: Ale ale není to naše princátko? Co mi chceš? \n"
                + "Pivoň: Jistě víš, že náš král zemřel a já nastoupím na jeho místo. \n"
                + "Pivoň: Určitě si budeš chtít zachovat svou vládu nad Přístavem. \n"
                + "Pivoň: Proto dotáhneš na hrad, poklekneš a odpřísáhneš mi věrnost. \n"
                + "Kapitán Morgan: Ha!! Rovnou k věci. Víš jak se bavit s piráty. \n"
                + "Kapitán Morgan: Teď ti něco povím. Přístav byl, je a bude můj. Nepotřebuji tebe ani králoství. \n"
                + "Kapitán Morgan: Teď táhni, než tě pošlu krmit ryby. \n"
                + "Pivoň: Ale ty se podvolíš. Královská armáda dobude přístav během dne. \n"
                + "Pivoň: S pomocí Kapitána Bucanera do dvou hodin. Hádej, kdo se pak stane pánem přístavu. \n"
                + "Kapitán Morgan: Ten navoněný zmetek nikdy nebude pánem přístavu! \n"
                + "Kapitán Morgan: Jsi nekompromisní, jako tvůj otec. Vážil jsem si ho. \n"
                + "Kapitán Morgan: Možná i ty si zasloužíš můj respekt. Dokaž to! \n\n"
                + "Kapitán Morgan: Z jaké suroviny se vyrábí pravý pirátský rum? \n"
                + "Možnosti: A - brambory, B - cukrová třtina, C - obilniny \n");
        Postava kapitanBucanero = new Postava ("kapitanBucanero", "Pivoň: Čau Bukanýre, nějakou dobu jsme se neviděli. \n"
                + "Kapitán Bucanero: Nazdár Pivoni, je to tak. Tak dáme hned rum? \n"
                + "Pivoň: Jasně že dáme, ale nejdřív od tebe něco potřebuju. \n"
                + "Kapitán Bucanero: Vo co de? \n"
                + "Pivoň: Potřebuju se dostat na Ledové ostrovy. \n"
                + "Kapitán Bucanero: Zbláznil ses? V tomhle ročním období ti nikdo krk nenasadí. \n"
                + "Pivoň: Proto jdu rovnou za tebou. Vím že většího blázna jinde nenajdu. \n"
                + "Kapitán Bucanero: To je pravda, ale tohle je sebevražda. \n"
                + "Kapitán Bucanero: Pokud do toho mám jít, musí mi z toho něco kápnout. Znáš mě. \n"
                + "Pivoň: Co chceš? Peníze? \n"
                + "Kapitán Bucanero: Peníze?! Těch mám tolik, že nevím co s nima. \n"
                + "Kapitán Bucanero: Ale je tady něco jinýho. Někde v Poušti zapomnění by měla být jistá mince. \n"
                + "Kapitán Bucanero: Dones mi ji. Já naložím sudy rumu na cestu a můžeme vyrazit. \n");
        roaldAmundsen = new Postava ("roaldAmundsen", "Roald Amundsen: Vítej cestovateli. V tomto ročním období tu nemáme moc návštěvníků. \n"
                + "Pivoň: Nepřijel bych, kdyby to nebylo nutné. Rozhodně ne v tomto počasí. \n"
                + "Pivoň: Král Abrelour zemřel a já, jeho syn, mám nastoupit na trůn. \n"
                + "Pivoň: Proto tě žádám, aby jsi se vypravil na hrad ke korunovaci. \n"
                + "Roald Amundsen: Ale jistě! Nadevše rád cestuji. \n"
                + "Roald Amundsen: Než se ale vydám na tuto dlouho pouť, musíš mi něco říct. \n\n"
                + "Roald Amundsen: V jakých sudech nejčastěji zraje nejkvalitnější whiskey? \n"
                + "Možnosti: A - bukových, B - akátový, C - dubový \n");

        // Vloží postavy do prostorů
        kouzelnyLes.vlozPostavu(myslivec);
        mlzneHory.vlozPostavu(strazceBeefeater);
        poustZapomneni.vlozPostavu(joseCuervo);
        ovocneSady.vlozPostavu(rudolfJelinek);
        pristavPiratu.vlozPostavu(kapitanMorgan);
        pristavPiratu.vlozPostavu(kapitanBucanero);
        ledoveOstrovy.vlozPostavu(roaldAmundsen);
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    public Batoh getBatoh() {
        return batoh;
    }

    public Prostor getViteznyProstor() {
        return viteznyProstor;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}

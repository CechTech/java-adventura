package GUI;

import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * Třída PanelVeciVProstoru - vytváří UI věcí v prostoru
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jiří Čech
 * @version 28.11.2017
 */
public class PanelVeciVProstoru extends VBox implements Observer {
    private IHra hra;
    private Map<String, Vec> mapaVeciVProstoru;
    private Button tlacitkoVeci;
    private Label vecLabel;
    private TextArea centralText;

    /**
     * Konstruktor věcí v prostoru
     * @param hra -
     * @param text -
     */
    public PanelVeciVProstoru(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.centralText = text;
        init();
    }

    /**
     * Vytváří panelVeciVProstoru se seznamem věcí v aktuálním prostoru
     * Věci jde sebrat
     */
    private void init() {
        vecLabel = new Label("Věci v prostoru:");
        vecLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vecLabel.setPrefWidth(200);
        mapaVeciVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();

        this.getChildren().clear();

        vytvorTlacitkaProVeci(mapaVeciVProstoru);

        update();
    }

    /**
     * Aktualizuje PanelVeciVProstoru
     */
    @Override
    public void update() {
        this.getChildren().clear();
        mapaVeciVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();

        vytvorTlacitkaProVeci(mapaVeciVProstoru);
    }

    /**
     * Vytvoří tlačítka pro věci v aktuálním prostoru
     * @param mapaVeciVProstoru - věci v aktuálním prostoru
     */
    private void vytvorTlacitkaProVeci(Map<String, Vec> mapaVeciVProstoru) {
        for (String vec : mapaVeciVProstoru.keySet()) {
            Vec pomocnaVec = mapaVeciVProstoru.get(vec);
            tlacitkoVeci = new Button(pomocnaVec.getNazev(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocnaVec.getObrazek()), 30, 30, false, false))
            );

            this.getChildren().add(tlacitkoVeci);

            nastavTlacitkoVeci(pomocnaVec);
        }
    }

    /**
     * Nastaví tlačítko pro věc
     * @param pomocnaVec -
     */
    private void nastavTlacitkoVeci(Vec pomocnaVec) {
        tlacitkoVeci.setOnMouseClicked(click -> {
            String vstupniPrikaz = "vezmi " + pomocnaVec.getNazev();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

            centralText.appendText("\n" + vstupniPrikaz + "\n");
            centralText.appendText("\n" + odpovedHry + "\n");

            hra.getHerniPlan().notifyObservers();
        });
    }

    /**
     * @return vecLabel
     */
    public Label getVecLabel() {
        return vecLabel;
    }
}

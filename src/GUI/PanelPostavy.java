package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Postava;
import main.Main;
import utils.Observer;
import java.util.HashMap;

/**
 * Třída PanelPostavy - vytváří UI postav v prostoru
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jiří Čech
 * @version 28.11.2017
 */
public class PanelPostavy extends VBox implements Observer {
    private IHra hra;
    private HashMap<String, Postava> mapaPostavVProstoru;
    private Button tlacitkoPostavy;
    private Label postavaLabel;
    private TextArea centralText;

    /**
     * Konstruktor postav v prostoru
     * @param hra -
     * @param text -
     */
    public PanelPostavy(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.centralText = text;
        init();
    }

    /**
     * Vytváří panelPostavy se seznamem postav v aktuálním prostoru
     * S postavami lze mluvit
     */
    private void init() {
        postavaLabel = new Label("PanelPostavy v prostoru:");
        postavaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        postavaLabel.setPrefWidth(200);
        mapaPostavVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();

        this.getChildren().clear();

        vytvorTlacitkaProPostavy(mapaPostavVProstoru);
        update();
    }

    /**
     * Aktualizuje panelPostavy
     */
    @Override
    public void update() {
        this.getChildren().clear();
        mapaPostavVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();
        vytvorTlacitkaProPostavy(mapaPostavVProstoru);
    }

    /**
     * Vytvoří tlačítka pro postavy v aktuálním prostoru
     * @param mapaPostavVProstoru - postavy v aktuálním prostoru
     */
    private void vytvorTlacitkaProPostavy(HashMap<String, Postava> mapaPostavVProstoru) {
        for (String postava : mapaPostavVProstoru.keySet()) {
            Postava pomocnaPostava = mapaPostavVProstoru.get(postava);
            tlacitkoPostavy = new Button(pomocnaPostava.getJmeno(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocnaPostava.getObrazek()), 0, 40, true, false))
            );

            this.getChildren().add(tlacitkoPostavy);

            nastavTlacitkoPostavy(pomocnaPostava);
        }
    }

    /**
     * Nastaví tlačítko pro postavu
     * @param pomocnaPostava -
     */
    private void nastavTlacitkoPostavy(Postava pomocnaPostava) {
        tlacitkoPostavy.setOnMouseClicked(click -> {
            String vstupniPrikaz = "mluv " + pomocnaPostava.getJmeno();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

            centralText.appendText("\n" + vstupniPrikaz + "\n");
            centralText.appendText("\n" + odpovedHry + "\n");

            hra.getHerniPlan().notifyObservers();
        });
    }

    /**
     * @return postavaLabel
     */
    public Label getPostavaLabel() {
        return postavaLabel;
    }
}

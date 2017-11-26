package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Postava;
import utils.Observer;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Jiří Čech
 */
public class PanelVolby extends HBox implements Observer {
    private IHra hra;
    private Button tlacitkoVolbyA;
    private Button tlacitkoVolbyB;
    private Button tlacitkoVolbyC;
    private HashMap<String, Postava> mapaPostavVProstoru;
    private Label volbaLabel;
    private TextArea centralText;

    /**
     * Konstruktor postav v prostoru
     * @param hra -
     * @param text -
     */
    public PanelVolby(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.centralText = text;
        init();
    }

    private void init() {
        volbaLabel = new Label("Možné volby:");
        volbaLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        volbaLabel.setPrefWidth(200);

        mapaPostavVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();

        this.getChildren().clear();

        tlacitkoVolbyA = new Button("A");
        tlacitkoVolbyB = new Button("B");
        tlacitkoVolbyC = new Button("C");

        tlacitkoVolbyA.getStyleClass().add("tlacitko-volim");
        tlacitkoVolbyB.getStyleClass().add("tlacitko-volim");
        tlacitkoVolbyC.getStyleClass().add("tlacitko-volim");

        for(String postava : mapaPostavVProstoru.keySet()) {
            Postava pomocnaPostava = mapaPostavVProstoru.get(postava);
            String auktualniProstor = hra.getHerniPlan().getAktualniProstor().getNazev();

            if(pomocnaPostava.isVazal() && !Objects.equals(auktualniProstor, "hrad")) {
                this.getChildren().addAll(tlacitkoVolbyA, tlacitkoVolbyB, tlacitkoVolbyC);
            }
        }

        nastavTlacitkoVolby(tlacitkoVolbyA);
        nastavTlacitkoVolby(tlacitkoVolbyB);
        nastavTlacitkoVolby(tlacitkoVolbyC);

        update();
    }

    @Override
    public void update() {
        mapaPostavVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();

        this.getChildren().clear();

        tlacitkoVolbyA = new Button("A");
        tlacitkoVolbyB = new Button("B");
        tlacitkoVolbyC = new Button("C");

        tlacitkoVolbyA.getStyleClass().add("tlacitko-volim");
        tlacitkoVolbyB.getStyleClass().add("tlacitko-volim");
        tlacitkoVolbyC.getStyleClass().add("tlacitko-volim");

        for(String postava : mapaPostavVProstoru.keySet()) {
            Postava pomocnaPostava = mapaPostavVProstoru.get(postava);
            String auktualniProstor = hra.getHerniPlan().getAktualniProstor().getNazev();

            if(pomocnaPostava.isVazal() && !Objects.equals(auktualniProstor, "hrad")) {
                this.getChildren().addAll(tlacitkoVolbyA, tlacitkoVolbyB, tlacitkoVolbyC);
            }
        }

        nastavTlacitkoVolby(tlacitkoVolbyA);
        nastavTlacitkoVolby(tlacitkoVolbyB);
        nastavTlacitkoVolby(tlacitkoVolbyC);
    }

    private void nastavTlacitkoVolby(Button tlacitkoVolby) {
        tlacitkoVolby.setOnMouseClicked(click -> {
            String vstupniPrikaz = "volim " + tlacitkoVolby.getText();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

            centralText.appendText("\n" + vstupniPrikaz + "\n");
            centralText.appendText("\n" + odpovedHry + "\n");

            hra.getHerniPlan().notifyObservers();
        });
    }

    /**
     * @return the volbaLabel
     */
    public Label getVolbaLabel() {
        return volbaLabel;
    }
}

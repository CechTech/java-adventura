package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Postava;
import utils.Observer;

import java.util.HashMap;

/**
 * @author Jiří Čech
 */
public class Volby extends HBox implements Observer {
    private IHra hra;
    private Button tlacitkoVolbyA;
    private Button tlacitkoVolbyB;
    private Button tlacitkoVolbyC;
    private HashMap<String, Postava> mapaPostavVProstoru;
    private Label volbaNazev;
    private TextArea centralText;

    /**
     * Konstruktor postav v prostoru
     * @param hra -
     * @param text -
     */
    public Volby(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.centralText = text;
        init();
    }

    private void init() {;
        volbaNazev = new Label("Možné volby:");
        getVolbaNazev().setFont(Font.font("Arial", FontWeight.BOLD, 14));
        getVolbaNazev().setPrefWidth(200);

        mapaPostavVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();
        System.out.println(mapaPostavVProstoru);

        this.getChildren().clear();

        tlacitkoVolbyA = new Button("A");
        tlacitkoVolbyB = new Button("B");
        tlacitkoVolbyC = new Button("C");

        tlacitkoVolbyA.getStyleClass().add("tlacitko-volim");
        tlacitkoVolbyB.getStyleClass().add("tlacitko-volim");
        tlacitkoVolbyC.getStyleClass().add("tlacitko-volim");

        for (String postava : mapaPostavVProstoru.keySet()) {
            Postava pomocnaPostava = mapaPostavVProstoru.get(postava);
            System.out.println(postava);
            if(pomocnaPostava.isVazal() && hra.getHerniPlan().getAktualniProstor().getNazev() != "hrad") {
                this.getChildren().addAll(tlacitkoVolbyA, tlacitkoVolbyB, tlacitkoVolbyC);
            }
        }

        tlacitkoVolbyA.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                String vstupniPrikaz = "volim " + tlacitkoVolbyA.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                hra.getHerniPlan().notifyObservers();
            }
        });

        tlacitkoVolbyB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                String vstupniPrikaz = "volim " + tlacitkoVolbyB.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                hra.getHerniPlan().notifyObservers();
            }
        });

        tlacitkoVolbyC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                String vstupniPrikaz = "volim " + tlacitkoVolbyC.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                hra.getHerniPlan().notifyObservers();
            }
        });
        update();
    }

    @Override
    public void update() {
        mapaPostavVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();
        System.out.println(mapaPostavVProstoru);

        this.getChildren().clear();

        tlacitkoVolbyA = new Button("A");
        tlacitkoVolbyB = new Button("B");
        tlacitkoVolbyC = new Button("C");

        tlacitkoVolbyA.getStyleClass().add("tlacitko-volim");
        tlacitkoVolbyB.getStyleClass().add("tlacitko-volim");
        tlacitkoVolbyC.getStyleClass().add("tlacitko-volim");

        for (String postava : mapaPostavVProstoru.keySet()) {
            Postava pomocnaPostava = mapaPostavVProstoru.get(postava);
            System.out.println(postava);
            if(pomocnaPostava.isVazal() && hra.getHerniPlan().getAktualniProstor().getNazev() != "hrad") {
                this.getChildren().addAll(tlacitkoVolbyA, tlacitkoVolbyB, tlacitkoVolbyC);
            }
        }

        tlacitkoVolbyA.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                String vstupniPrikaz = "volim " + tlacitkoVolbyA.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                hra.getHerniPlan().notifyObservers();
            }
        });

        tlacitkoVolbyB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                String vstupniPrikaz = "volim " + tlacitkoVolbyB.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                hra.getHerniPlan().notifyObservers();
            }
        });

        tlacitkoVolbyC.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                String vstupniPrikaz = "volim " + tlacitkoVolbyC.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                hra.getHerniPlan().notifyObservers();
            }
        });
    }

    /**
     * @return the volbaNazev
     */
    public Label getVolbaNazev() {
        return volbaNazev;
    }
}

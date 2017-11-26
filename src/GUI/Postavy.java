package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Postava;
import main.Main;
import utils.Observer;

import java.util.HashMap;


/**
 * @author Jiří Čech
 */
public class Postavy extends VBox implements Observer {
    private IHra hra;
    private HashMap<String, Postava> mapaPostavVProstoru;
    private Button tlacitkoPostavy;
    private Label postavaNazev;
    private TextArea centralText;

    /**
     * Konstruktor postav v prostoru
     * @param hra -
     * @param text -
     */
    public Postavy(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.centralText = text;
        init();
    }

    private void init() {;
        postavaNazev = new Label("Postavy v prostoru:");
        getPostavaNazev().setFont(Font.font("Arial", FontWeight.BOLD, 14));
        getPostavaNazev().setPrefWidth(200);
        mapaPostavVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();

        this.getChildren().clear();

        for (String postava : mapaPostavVProstoru.keySet()) {
            Postava pomocnaPostava = mapaPostavVProstoru.get(postava);
            tlacitkoPostavy = new Button(pomocnaPostava.getJmeno(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocnaPostava.getObrazek()), 0, 40, true, false))
            );

            this.getChildren().add(getTlacitkoPostavy());

            tlacitkoPostavy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    String vstupniPrikaz = "mluv " + tlacitkoPostavy.getText();
                    String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");

                    hra.getHerniPlan().notifyObservers();
                }
            });
            update();
        }
    }

    @Override
    public void update() {
        this.getChildren().clear();
        mapaPostavVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamPostav();

        for (String postava : mapaPostavVProstoru.keySet()) {
            try {
                Postava pomocnaPostava = mapaPostavVProstoru.get(postava);
                tlacitkoPostavy = new Button(pomocnaPostava.getJmeno(), new ImageView(new Image(
                        Main.class.getResourceAsStream(pomocnaPostava.getObrazek()), 0, 40, true, false))
                );

                this.getChildren().add(getTlacitkoPostavy());
                tlacitkoPostavy.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent click) {
                        String vstupniPrikaz = "mluv " + pomocnaPostava.getJmeno();
                        String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                        centralText.appendText("\n" + vstupniPrikaz + "\n");
                        centralText.appendText("\n" + odpovedHry + "\n");

                        hra.getHerniPlan().notifyObservers();
                    }
                });
            } catch (Exception exception) {
            }
        }
    }

    /**
     * @return the postavaNazev
     */
    public Label getPostavaNazev() {
        return postavaNazev;
    }

    /**
     * @return the tlacitkoPostavy
     */
    public Button getTlacitkoPostavy() {
        return tlacitkoPostavy;
    }

    public HashMap<String, Postava> getMapaPostavVProstoru() {
        return mapaPostavVProstoru;
    }
}

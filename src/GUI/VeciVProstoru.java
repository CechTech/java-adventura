package GUI;

import java.util.Map;
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
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * @author Jiří Čech
 */
public class VeciVProstoru extends VBox implements Observer {
    private IHra hra;
    private Map<String, Vec> mapaVeciVProstoru;
    private Button tlacitkoVeci;
    private Label vecNazev;
    private TextArea centralText;

    /**
     * Konstruktor věcí v prostoru
     * @param hra -
     * @param text -
     */
    public VeciVProstoru(IHra hra, TextArea text) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        this.centralText = text;
        init();
    }

    private void init() {;
        vecNazev = new Label("Věci v prostoru:");
        getVecNazev().setFont(Font.font("Arial", FontWeight.BOLD, 14));
        getVecNazev().setPrefWidth(200);
        mapaVeciVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();

        this.getChildren().clear();

        for (String vec : mapaVeciVProstoru.keySet()) {
            Vec pomocnaVec = mapaVeciVProstoru.get(vec);
            tlacitkoVeci = new Button(pomocnaVec.getNazev(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocnaVec.getObrazek()), 30, 30, false, false))
            );

            this.getChildren().add(getTlacitkoVeci());

            tlacitkoVeci.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {
                    String vstupniPrikaz = "vezmi " + tlacitkoVeci.getText();
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
        mapaVeciVProstoru = hra.getHerniPlan().getAktualniProstor().getSeznamVeci();

        for (String vec : mapaVeciVProstoru.keySet()) {
            try {
                Vec pomocnaVec = mapaVeciVProstoru.get(vec);
                tlacitkoVeci = new Button(pomocnaVec.getNazev(), new ImageView(new Image(
                        Main.class.getResourceAsStream(pomocnaVec.getObrazek()), 30, 30, false, false))
                );

                this.getChildren().add(getTlacitkoVeci());
                tlacitkoVeci.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent click) {
                        String vstupniPrikaz = "vezmi " + pomocnaVec.getNazev();
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
     * @return the vecNazev
     */
    public Label getVecNazev() {
        return vecNazev;
    }

    /**
     * @return the tlacitkoVeci
     */
    public Button getTlacitkoVeci() {
        return tlacitkoVeci;
    }
}

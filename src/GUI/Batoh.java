package GUI;

import java.util.Map;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.HerniPlan;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * @author Jiří Čech
 */
public class Batoh extends VBox implements Observer {
    private HerniPlan plan;
    private IHra hra;
    private Label batohNazev;
    private Button tlacitkoBatohu;
    private Button tlacitkoZahod;
    private TextArea centralText;

    /**
     * Konstruktor batohu
     * @param plan -
     * @param text -
     * @param hra -
     */
    public Batoh(HerniPlan plan, TextArea text, IHra hra) {
        this.plan = plan;
        plan.registerObserver(this);
        centralText = text;
        this.hra = hra;
        init();
    }

    private void init() {
        setBatohNazev(new Label("Batoh:"));
        getBatohNazev().setFont(Font.font("Arial", FontWeight.BOLD, 14));
        getBatohNazev().setPrefWidth(200);

        Map<String, Vec> seznamVeci = plan.getBatoh().getSeznamVeci();

        for (String vec : seznamVeci.keySet()) {
            Vec pomocnaVec = seznamVeci.get(vec);
            tlacitkoBatohu = new Button(pomocnaVec.getNazev(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocnaVec.getObrazek()), 30, 30, false, false))
            );
            tlacitkoZahod = new Button("X");

            this.getChildren().addAll(getTlacitkoBatohu(), getTlacitkoZahod());

            tlacitkoBatohu.setOnMouseClicked(event -> {
                String vstupniPrikaz = "pouzi " + pomocnaVec.getNazev();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                plan.notifyObservers();
            });

            tlacitkoZahod.setOnMouseClicked(event -> {
                String vstupniPrikaz = "vyhod " + pomocnaVec.getNazev();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                plan.notifyObservers();
            });
            update();
        }
    }

    @Override
    public void update() {
        this.getChildren().clear();
        Map<String, Vec> seznamVeci;
        seznamVeci = plan.getBatoh().getSeznamVeci();
        for (String vec : seznamVeci.keySet()) {
            Vec pomocnaVec = seznamVeci.get(vec);
            tlacitkoBatohu = new Button(pomocnaVec.getNazev(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocnaVec.getObrazek()), 30, 30, false, false))
            );
            tlacitkoZahod = new Button("X");
            tlacitkoZahod.getStyleClass().add("button-zahod");

            this.getChildren().addAll(getTlacitkoBatohu(), getTlacitkoZahod());

            tlacitkoBatohu.setOnMouseClicked(event -> {
                String vstupniPrikaz = "pouzi " + pomocnaVec.getNazev();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                plan.notifyObservers();
            });

            tlacitkoZahod.setOnMouseClicked(event -> {
                String vstupniPrikaz = "vyhod " + pomocnaVec.getNazev();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                plan.notifyObservers();
            });
        }
    }

    /**
     * @return the batohNazev
     */
    public Label getBatohNazev() {
        return batohNazev;
    }

    /**
     * @param batohNazev the batohNazev to set
     */
    public void setBatohNazev(Label batohNazev) {
        this.batohNazev = batohNazev;
    }

    /**
     * @return the tlacitkoBatohu
     */
    public Button getTlacitkoBatohu() {
        return tlacitkoBatohu;
    }


    /**
     * @return the tlacitkoZahod
     */
    public Button getTlacitkoZahod() {
        return tlacitkoZahod;
    }
}

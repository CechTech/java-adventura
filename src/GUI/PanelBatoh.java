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
import logika.HerniPlan;
import logika.IHra;
import logika.Vec;
import main.Main;
import utils.Observer;

/**
 * @author Jiří Čech
 */
public class PanelBatoh extends VBox implements Observer {
    private HerniPlan plan;
    private IHra hra;
    private Label batohLabel;
    private Button tlacitkoBatohu;
    private Button tlacitkoVyhod;
    private TextArea centralText;

    /**
     * Konstruktor batohu
     * @param plan -
     * @param text -
     * @param hra -
     */
    public PanelBatoh(HerniPlan plan, TextArea text, IHra hra) {
        this.plan = plan;
        plan.registerObserver(this);
        centralText = text;
        this.hra = hra;
        init();
    }

    private void init() {
        batohLabel = new Label("PanelBatoh:");
        batohLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        batohLabel.setPrefWidth(200);

        Map<String, Vec> seznamVeci = plan.getBatoh().getSeznamVeci();

        for (String vec : seznamVeci.keySet()) {
            Vec pomocnaVec = seznamVeci.get(vec);
            tlacitkoBatohu = new Button(pomocnaVec.getNazev(), new ImageView(new Image(
                    Main.class.getResourceAsStream(pomocnaVec.getObrazek()), 30, 30, false, false))
            );
            tlacitkoVyhod = new Button("X");
            tlacitkoVyhod.getStyleClass().add("tlacitko-zahod");
            
            this.getChildren().addAll(tlacitkoBatohu, tlacitkoVyhod);

            tlacitkoBatohu(pomocnaVec);
            tlacitkoVyhod(pomocnaVec);

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
            tlacitkoVyhod = new Button("X");
            tlacitkoVyhod.getStyleClass().add("tlacitko-zahod");

            this.getChildren().addAll(tlacitkoBatohu, tlacitkoVyhod);

            tlacitkoBatohu(pomocnaVec);
            tlacitkoVyhod(pomocnaVec);
        }
    }

    private void tlacitkoBatohu(Vec pomocnaVec) {
        tlacitkoBatohu.setOnMouseClicked(event -> {
            String vstupniPrikaz = "pouzi " + pomocnaVec.getNazev();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

            centralText.appendText("\n" + vstupniPrikaz + "\n");
            centralText.appendText("\n" + odpovedHry + "\n");

            plan.notifyObservers();
        });
    }

    private void tlacitkoVyhod(Vec pomocnaVec) {
        tlacitkoVyhod.setOnMouseClicked(event -> {
            String vstupniPrikaz = "vyhod " + pomocnaVec.getNazev();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

            centralText.appendText("\n" + vstupniPrikaz + "\n");
            centralText.appendText("\n" + odpovedHry + "\n");

            plan.notifyObservers();
        });
    }

    /**
     * @return the batohLabel
     */
    public Label getBatohLabel() {
        return batohLabel;
    }
}

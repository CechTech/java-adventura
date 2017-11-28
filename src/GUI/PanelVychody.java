package GUI;

import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logika.IHra;
import logika.Prostor;
import utils.Observer;

/**
 * Třída PanelVychody - vytváří UI aktuálních východů
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jiří Čech
 * @version 28.11.2017
 */
public class PanelVychody extends ListView implements Observer {
    private IHra hra;
    private ListView<String> seznamVychodu;
    private ObservableList<String> vychody;
    private Label vychodLabel;

    /**
     * Konstruktor východů
     * @param hra -
     */
    public PanelVychody(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    /**
     * Vytváří panelVychody se seznamem aktuálních východů
     * Prostory lze procházet
     */
    private void init() {
        Collection<Prostor> sousedniVychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        vychody = FXCollections.observableArrayList();
        seznamVychodu = new ListView<>();
        vychodLabel = new Label("Východy:");

        seznamVychodu.setItems(vychody);
        seznamVychodu.setPrefWidth(200);
        seznamVychodu.setMaxHeight(120);
        vychodLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vychodLabel.setPrefWidth(200);

        for (Prostor prostor : sousedniVychody) {
            vychody.add(prostor.getNazev());
        }
        update();
    }

    /**
     * Aktualizuje PanelVychody
     */
    @Override
    public void update() {
        Collection<Prostor> sousedniProstory = hra.getHerniPlan().getAktualniProstor().getVychody();
        vychody.clear();

        sousedniProstory.forEach((prostor) -> vychody.add(prostor.getNazev()));
    }

    /**
     * @return seznamVychodu
     */
    public ListView<String> getSeznamVychodu() {
        return seznamVychodu;
    }

    /**
     * @return vychodLabel
     */
    public Label getVychodLabel() {
        return vychodLabel;
    }
}

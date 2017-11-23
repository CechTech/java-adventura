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
 * @author cecj02
 */
public class Vychody extends ListView implements Observer {
    private IHra hra;
    private ListView<String> seznamVychodu;
    private ObservableList<String> vychody;
    private Label vychodNazev;

    /**
     * Konstruktor východů
     * @param hra -
     */
    public Vychody(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    private void init() {
        Collection<Prostor> sousedniVychody = hra.getHerniPlan().getAktualniProstor().getVychody();
        seznamVychodu = new ListView<>();
        vychody = FXCollections.observableArrayList();
        getSeznamVychodu().setItems(vychody);
        getSeznamVychodu().setPrefWidth(200);
        getSeznamVychodu().setMaxHeight(120);
        vychodNazev = new Label("Východy:");
        getVychodNazev().setFont(Font.font("Avenir Next", FontWeight.BOLD, 16));
        getVychodNazev().setPrefWidth(200);

        for (Prostor prostor : sousedniVychody) {
            vychody.add(prostor.getNazev());
        }
        update();
    }

    @Override
    public void update() {
        Collection<Prostor> sousedniProstory = hra.getHerniPlan()
                .getAktualniProstor().getVychody();
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
     * @return vychodNazev
     */
    public Label getVychodNazev() {
        return vychodNazev;
    }
}

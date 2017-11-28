package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 * Třída PanelMapa - vytváří UI mapy
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author xzenj02
 * @version 28.11.2017
 */
public class PanelMapa extends AnchorPane implements Observer {
    private IHra hra;
    private ImageView pivon = new ImageView(new Image(
            Main.class.getResourceAsStream("/zdroje/pivo.png"),
            30,30,true,false));

    /**
     * Konstruktor mapy
     * @param hra -
     */
    public PanelMapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    /**
     * Vytváří mapu s pohyblivým obrázkem
     */
    private void init() {
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa/mapa_kralostvi_lihovin.jpg"), 450, 635, false, true));

        this.getChildren().addAll(obrazekImageView, pivon);
        update();
    }

    /**
     * Aktualizuje PanelMapa
     */
    @Override
    public void update() {
        setTopAnchor(pivon, hra.getHerniPlan().getAktualniProstor().getPosX());
        setLeftAnchor(pivon, hra.getHerniPlan().getAktualniProstor().getPosY());
    }
}
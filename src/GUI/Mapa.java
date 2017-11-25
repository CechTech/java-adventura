/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logika.Hra;
import logika.IHra;
import main.Main;
import utils.Observer;

public class Mapa extends AnchorPane implements Observer {
    private IHra hra;
    ImageView pivon = new ImageView(new Image(
            Main.class.getResourceAsStream("/zdroje/pivo.png"),
            40,40,true,true));

    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }

    private void init() {
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/adventura_mapa.jpg"), 400, 635, false, true));

        this.getChildren().addAll(obrazekImageView, pivon);
        update();
    }

    @Override
    public void update() {
        this.setTopAnchor(pivon, hra.getHerniPlan().getAktualniProstor().getPosX());
        this.setLeftAnchor(pivon, hra.getHerniPlan().getAktualniProstor().getPosY());
    }
}
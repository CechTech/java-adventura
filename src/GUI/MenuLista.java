/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.Main;

/**
 * @author xzenj02
 */
public class MenuLista extends MenuBar {
    private Main main;
    private Stage stage;

    public MenuLista(Main main, Stage stage){
        this.main = main;
        this.stage = stage;
        init();
    }

    private void init(){
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Help");

        MenuItem novaHra = new MenuItem("Nova hra");
        novaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        MenuItem konecHry = new MenuItem("Konec hry");
        konecHry.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));

        MenuItem oProgramu = new MenuItem("O programu");
        oProgramu.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));

        MenuItem napovedaItem = new MenuItem("Napoveda");
        napovedaItem.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));

        novySoubor.getItems().addAll(novaHra, konecHry);
        napoveda.getItems().addAll(oProgramu, napovedaItem);

        this.getMenus().addAll(novySoubor, napoveda);

        konecHry.setOnAction(event -> System.exit(0));
        novaHra.setOnAction(event -> main.start(stage));

        oProgramu.setOnAction(event -> {
            Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);

            oProgramuAlert.setTitle("O pragramu");
            oProgramuAlert.setHeaderText("Adventura Králoství Lihovin");
            oProgramuAlert.setContentText("v28-11-17");
            oProgramuAlert.initOwner(main.getStage());

            oProgramuAlert.showAndWait();
        });

        napovedaItem.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Napoveda");

            WebView webView = new WebView();

            webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());

            stage.setScene(new Scene(webView, 500,500));
            stage.show();
        });
    }
}
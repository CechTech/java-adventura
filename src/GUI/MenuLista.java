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
 * Třída MenuLista - vytváří UI menu s nabídkou možností hry
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author xzenj02
 * @version 28.11.2017
 */
public class MenuLista extends MenuBar {
    private Main main;
    private Stage stage;

    /**
     * Konstruktor menu
     * @param main -
     * @param stage -
     */
    public MenuLista(Main main, Stage stage){
        this.main = main;
        this.stage = stage;
        init();
    }

    /**
     * Vytváří menuLista s nabídkou možností hry
     */
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

        // nastavuje položku konec hry
        konecHry.setOnAction(event -> System.exit(0));
        novaHra.setOnAction(event -> main.start(stage));

        // nastavuje položku informace o programu
        oProgramu.setOnAction(event -> {
            Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);

            oProgramuAlert.setTitle("O pragramu");
            oProgramuAlert.setHeaderText("Adventura Králoství Lihovin");
            oProgramuAlert.setContentText("v28-11-17");
            oProgramuAlert.initOwner(main.getStage());

            oProgramuAlert.showAndWait();
        });

        // nastavuje položku nápověda
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
package main;

import GUI.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 * @author xzenj02, cecj02
 */
public class Main extends Application {
    private TextArea centralText;
    private IHra hra;

    public void setHra(IHra hra) {
        this.hra = hra;
    }
    private TextField zadejPrikazTextArea;

    private PanelMapa panelMapa;
    private MenuLista menuLista;
    private PanelVychody panelVychody;
    private PanelVeciVProstoru panelVeciVProstoru;
    private PanelBatoh panelBatoh;
    private PanelPostavy panelPostavy;
    private PanelVolby panelVolby;

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);

        hra = new Hra();
        panelMapa = new PanelMapa(hra);
        menuLista = new MenuLista(this, stage);
        panelVychody = new PanelVychody(hra);

        BorderPane borderPane = new BorderPane();

        // Text s prubehem hry
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);

        //label s textem zadej prikaz
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // text area do ktere piseme prikazy
        zadejPrikazTextArea = new TextField("");
        zadejPrikazTextArea.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String vstupniPrikaz = zadejPrikazTextArea.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);

                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");

                zadejPrikazTextArea.setText("");

                if (hra.konecHry()) {
                    zadejPrikazTextArea.setEditable(false);
                    panelVychody.getSeznamVychodu().setDisable(true);
                    panelVeciVProstoru.setDisable(true);
                    panelBatoh.setDisable(true);
                    panelPostavy.setDisable(true);
                    panelVolby.setDisable(true);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });

        getPanelVychody().getSeznamVychodu().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String nazevVychodu = getPanelVychody().getSeznamVychodu().getSelectionModel().getSelectedItem();
                String vstupniPrikaz = "jdi " + nazevVychodu;
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                appendCentralText(odpovedHry);
            }
        });

        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);

        panelVeciVProstoru = new PanelVeciVProstoru(hra, centralText);
        panelPostavy = new PanelPostavy(hra, centralText);
        panelBatoh = new PanelBatoh(hra.getHerniPlan(), centralText, hra);
        panelVolby = new PanelVolby(hra, centralText);

        FlowPane pravaLista = new FlowPane();
        pravaLista.setAlignment(Pos.TOP_CENTER);
        pravaLista.setPrefWidth(200);
        pravaLista.getChildren().addAll(
                getPanelVychody().getVychodLabel(),
                getPanelVychody().getSeznamVychodu(),
                getPanelVeciVProstoru().getVecLabel(),
                getPanelVeciVProstoru(),
                getPanelBatoh().getBatohLabel(),
                getPanelBatoh(),
                getPanelPostavy().getPostavaLabel(),
                getPanelPostavy(),
                getPanelVolby().getVolbaLabel(),
                getPanelVolby());

        borderPane.setLeft(panelMapa);
        borderPane.setRight(pravaLista);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);

        Scene scene = new Scene(borderPane, 1330, 720);
        scene.getStylesheets().add("zdroje/styles.css");
        primaryStage.setTitle("Adventura");

        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    public TextArea getCentralText() {
        return centralText;
    }

    public void appendCentralText(String vstupniPrikaz) {
        this.getCentralText().appendText("\n" + vstupniPrikaz + "\n");
    }

    public PanelMapa getPanelMapa() {
        return panelMapa;
    }

    /**
     * @return the panelVychody
     */
    public PanelVychody getPanelVychody() {
        return panelVychody;
    }

    /**
     * @return the panelVeciVProstoru
     */
    public PanelVeciVProstoru getPanelVeciVProstoru() {
        return panelVeciVProstoru;
    }

    /**
     * @return the panelBatoh
     */
    public PanelBatoh getPanelBatoh() {
        return panelBatoh;
    }

    /**
     * @return the panelPostavy
     */
    public PanelPostavy getPanelPostavy() {
        return panelPostavy;
    }

    /**
     * @return the panelVolby
     */
    public PanelVolby getPanelVolby() {
        return panelVolby;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
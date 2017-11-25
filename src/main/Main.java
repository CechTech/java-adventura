package main;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.Vychody;
import GUI.VeciVProstoru;
import GUI.Batoh;
import GUI.Postavy;
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

    private Mapa mapa;
    private MenuLista menuLista;
    private Vychody vychody;
    private VeciVProstoru veciVProstoru;
    private Batoh batoh;
    private Postavy postavy;

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);

        hra = new Hra();
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this, stage);
        vychody = new Vychody(hra);

        BorderPane borderPane = new BorderPane();

        // Text s prubehem hry
        centralText = new TextArea();
        veciVProstoru = new VeciVProstoru(hra, centralText);
        postavy = new Postavy(hra, centralText);
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
                    vychody.getSeznamVychodu().setDisable(true);
                    veciVProstoru.setDisable(true);
                    batoh.setDisable(true);
                    postavy.setDisable(true);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });

        getVychody().getSeznamVychodu().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String nazevVychodu = getVychody().getSeznamVychodu().getSelectionModel().getSelectedItem();
                String vstupniPrikaz = "jdi " + nazevVychodu;
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                appendCentralText(odpovedHry);
            }
        });

        //dolni lista s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);

        batoh = new Batoh(hra.getHerniPlan(), centralText, hra);

        FlowPane pravaLista = new FlowPane();
        pravaLista.setAlignment(Pos.TOP_CENTER);
        pravaLista.setPrefWidth(200);
        pravaLista.getChildren().addAll(
                getVychody().getVychodNazev(),
                getVychody().getSeznamVychodu(),
                getVeciVProstoru().getVecNazev(),
                getVeciVProstoru(),
                getBatoh().getBatohNazev(),
                getBatoh(),
                getPostavy().getPostavaNazev(),
                getPostavy());

        borderPane.setLeft(mapa);
        borderPane.setRight(pravaLista);
        borderPane.setBottom(dolniLista);
        borderPane.setTop(menuLista);

        Scene scene = new Scene(borderPane, 750, 450);
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

    public Mapa getMapa() {
        return mapa;
    }

    /**
     * @return the vychody
     */
    public Vychody getVychody() {
        return vychody;
    }

    /**
     * @return the veciVProstoru
     */
    public VeciVProstoru getVeciVProstoru() {
        return veciVProstoru;
    }

    /**
     * @return the batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }

    /**
     * @return the postavy
     */
    public Postavy getPostavy() {
        return postavy;
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
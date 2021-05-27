package GUI;

import javafx.fxml.FXML;

import java.beans.EventHandler;
import java.io.FileReader;
import java.util.Iterator;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import pipeandfilter.*;
import Store.*;
import pipeandfilter.Snippet;
import pipeandfilter.Store_GUIFilter;
import javafx.fxml.FXMLLoader;

import java.util.*;

public class InterfaceController {

    private String choice;
    private String param="";

    @FXML
    private void RadioClick1() throws Exception {
        this.choice = "1";
    }

    @FXML
    private void RadioClick2() throws Exception {
        this.choice = "2";
    }

    @FXML
    private void RadioClick3() throws Exception {
        this.choice = "3";
    }

    @FXML
    private void RadioClick4() throws Exception {
        this.choice = "4";
    }

    @FXML
    private void RadioClick5() throws Exception {
        this.choice = "5";
    }

    @FXML
    private void RadioClick6() throws Exception {
        this.choice = "6";
    }

    @FXML
    private void RadioClick7() throws Exception {
        this.choice = "7";
    }

    @FXML
    private void RadioClick8() throws Exception {
        this.choice = "8";
    }

    @FXML
    private void RadioClick9() throws Exception {
        this.choice = "9";
    }

    @FXML
    private void RadioClick10() throws Exception {
        this.choice = "10";
    }

    @FXML
    private void ExitButton(ActionEvent event) throws Exception {
        System.exit(0);
    }

    @FXML
    private TextField field;

    @FXML
    private AnchorPane ap;

    @FXML
    private void getInput() throws Exception {
        this.param = field.getText();
    }

    // @FXML
    // private Label label;

    @FXML
    private void SearchButtonClick(ActionEvent event) throws Exception {
        String req = choice + ',' + param;
        System.out.println(req);
        Store_GUIFilter Gui_store = Snippet.main(req);
        // Thread th1 = new Thread( Gui_store );
        // th1.start();
        // Gui_store.sendData(req);
        String txt = new String();
        txt = "hello";
        // label.setText("hello");
        try {

            String result = Gui_store.getData(req);
            System.out.println(result);
            switch (Integer.parseInt(choice)) {
                case 1:
                    txt=result;
                    break;

                case 2:
                    if (Boolean.parseBoolean((String) result)) {
                        txt = "Cet article est disponible à la location\n";
                    } else {
                        txt = "Cet article n'est pas disponible à la location\n";
                    }
                    break;

                case 3:
                    txt = result;
                    break;

                case 4:
                    txt = "Le solde du client est: " + result;
                    break;

                case 5:
                    txt = "Les articles en retard sont: \n" + result;
                    break;

                case 6:
                    System.out.println("\nListe des articles loués:\n");
                    for (RentedItem item : Snippet.RentedItems) {
                        System.out.println("Item " + item.getItemID() + " | par le client :" + item.getCustomerID()
                                + " | le " + item.getDueDate() + "\n");
                    }
                    break;

                case 7:
                    System.out.println("\nListe des articles loués mise à jour:\n");
                    for (RentedItem item : Snippet.RentedItems) {
                        System.out.println("Item " + item.getItemID() + " | par le client :" + item.getCustomerID()
                                + " | le " + item.getDueDate() + "\n");
                    }
                    break;

                case 8:
                String []params=param.split(",");
                txt ="Solde du client mise à jour: "
                            + Snippet.CustomerMap.get(params[0]).getAccountBalance() ;
                    break;

                case 9:
                    System.out.println("\nListe des articles en stock mise à jour:\n");
                    Set entrySet = Snippet.StockItemMap.entrySet();

                    // Obtaining an iterator for the entry set
                    Iterator it = entrySet.iterator();

                    // Iterate through HashMap entries(Key-Value pairs)
                    while (it.hasNext()) {
                        Map.Entry me = (Map.Entry) it.next();
                        System.out.println("Titre :" + ((StockItem) me.getValue()).getTitle() + " | Prix de location "
                                + ((StockItem) me.getValue()).getRentalPrice() + "\n");
                    }
                    break;

                case 10:
                    System.out.println("\nListe des clients mise à jour:\n");
                    Set entrySet2 = Snippet.CustomerMap.entrySet();

                    // Obtaining an iterator for the entry set
                    Iterator it2 = entrySet2.iterator();

                    // Iterate through HashMap entries(Key-Value pairs)
                    while (it2.hasNext()) {
                        Map.Entry me2 = (Map.Entry) it2.next();
                        System.out.println("Nom: " + ((Client) me2.getValue()).getName() + " | Solde: "
                                + ((Client) me2.getValue()).getAccountBalance());
                    }
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("results.fxml"));
        Parent res = fxmlLoader.load();
        resultsController resCtrl = fxmlLoader.getController();
        resCtrl.setLabelText(txt,choice);
        Scene scene = new Scene(res);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(scene);
        app_stage.show();

        // try {

        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }

        // System.out.println("Hello");

    }
}

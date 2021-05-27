package GUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Store.RentedItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pipeandfilter.Snippet;
import Store.*;

public class resultsController {

    @FXML
    private Label label;

    @FXML
    private ListView<String> list;

    @FXML
    public void setLabelText(String param, String choice) {
        ArrayList<String> l = new ArrayList<String>();
        if (choice.equals("6")) {
            label.setText("Liste des articles loués");
            l.clear();
            for (RentedItem item : Snippet.RentedItems) {

                l.add("Item " + item.getItemID() + " | par le client :" + item.getCustomerID() + " | le "
                        + item.getDueDate() + "\n");
            }
            ObservableList<String> items = FXCollections.observableArrayList(l);
            list.setItems(items);
        } else {
            if (choice.equals("7")) {
                l.clear();
                label.setText("Liste des articles loués mise à jour");
                for (RentedItem item : Snippet.RentedItems) {
                    l.add("Item " + item.getItemID() + " | par le client :" + item.getCustomerID() + " | le "
                            + item.getDueDate() + "\n");
                }
                ObservableList<String> items = FXCollections.observableArrayList(l);
                list.setItems(items);
            } else {
                l.clear();
                if (choice.equals("9")) {
                    label.setText("Liste des articles en stock mise à jour");
                    Set entrySet = Snippet.StockItemMap.entrySet();

                    // Obtaining an iterator for the entry set
                    Iterator it = entrySet.iterator();

                    // Iterate through HashMap entries(Key-Value pairs)
                    while (it.hasNext()) {
                        Map.Entry me = (Map.Entry) it.next();
                        l.add("Titre :" + ((StockItem) me.getValue()).getTitle() + " | Prix de location "
                                + ((StockItem) me.getValue()).getRentalPrice() + "\n");
                    }

                    ObservableList<String> items = FXCollections.observableArrayList(l);
                    list.setItems(items);
                } else {
                    l.clear();
                    if (choice.equals("10")) {
                        label.setText("Liste des clients mise à jour");
                        Set entrySet2 = Snippet.CustomerMap.entrySet();

                        // Obtaining an iterator for the entry set
                        Iterator it2 = entrySet2.iterator();

                        // Iterate through HashMap entries(Key-Value pairs)
                        while (it2.hasNext()) {
                            Map.Entry me2 = (Map.Entry) it2.next();
                            l.add("Nom: " + ((Client) me2.getValue()).getName() + " | Solde: "
                                    + ((Client) me2.getValue()).getAccountBalance());
                        }

                        ObservableList<String> items = FXCollections.observableArrayList(l);

                        list.setItems(items);
                    } else {
                        l.clear();
                        l.add((String) param);
                        ObservableList<String> items = FXCollections.observableArrayList(l);
                        list.setItems(items);
                        label.setText("");

                    }

                }

            }
        }

    }

    @FXML
    private void ExitButton() throws Exception {
        System.exit(0);
    }

    @FXML
    private void GoBackButton(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("interface.fxml"));
        Parent res = fxmlLoader.load();
        Scene scene = new Scene(res);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(scene);
        app_stage.show();
    }

}

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView<String> leftList;
    @FXML
    private ListView<String> rightList;

    public void createButtonClicked(){
        String to_add_string = new String();
        to_add_string = "";
        ListView<String> to_add_elements = new ListView<String> (leftList.getSelectionModel().getSelectedItems());
        for (int i = 0; i < to_add_elements.getItems().stream().count(); i++) {
            if (to_add_string == "") {
                to_add_string = to_add_elements.getItems().get(i);
            }
            else {
                to_add_string = to_add_string + ", "+ to_add_elements.getItems().get(i).toLowerCase(Locale.ROOT);
            }
        }
        if (to_add_string.length() > 20) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("String length is more then 20!");
            alert.showAndWait();
        }
        else {
            rightList.getItems().add(to_add_string);
        }
    }

    public void deleteButtonClicked(){
        ListView<Integer> to_del_elements = new ListView<Integer> (rightList.getSelectionModel().getSelectedIndices());
        for (int i=0; i<to_del_elements.getItems().stream().count(); i++) {
            rightList.getItems().remove(rightList.getItems().get(to_del_elements.getItems().get(i)));
            rightList.refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leftList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<String> strings = FXCollections.observableArrayList("First", "Second", "Third", "Fourth");
        leftList.setItems(strings);
        rightList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ObservableList<String> resultStrings = FXCollections.observableArrayList();
        rightList.setItems(resultStrings);
    }
}
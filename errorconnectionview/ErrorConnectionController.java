package errorconnectionview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class ErrorConnectionController implements Initializable {

    @FXML
    private Button btnOk;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleOk(ActionEvent actionEvent){
        Stage stage = (Stage)btnOk.getScene().getWindow();
        stage.hide();
    }
}

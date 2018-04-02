package welcomeview;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import managerview.ManagerViewController;
import ui.FillProgressIndicator;
import ui.RingProgressIndicator;


import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    public static FXMLLoader fxmlMain = new FXMLLoader();

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private StackPane stackPane;
    @FXML
    private Text txtLoading;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FillProgressIndicator ringProgressIndicator = new FillProgressIndicator();
        //  ringProgressIndicator.setRingWidth(50);
        ringProgressIndicator.makeIndeterminate();

        stackPane.getChildren().add(ringProgressIndicator);
        WelcomeThread wt = new WelcomeThread(ringProgressIndicator);
        wt.start();
    }


    public class WelcomeThread extends Thread {
        FillProgressIndicator rpi;
        int progress;

        public WelcomeThread(FillProgressIndicator rpi) {
            this.rpi = rpi;
            progress = 0;
        }

        @Override
        public void run() {
            while (true) {

                Platform.runLater(() ->
                {
                    rpi.setProgress(progress);
                });

                progress++;
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (progress>98) break;
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerview/ManagerStudent.fxml"));
                    fxmlMain = fxmlLoader;
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (fxmlLoader.<ManagerViewController>getController().listStudent.size() == 0) {
                        System.out.println("a");
                        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/errorconnectionview/ErrorConnection.fxml"));
                        Parent root1 = null;
                        try {
                            root1 = fxmlLoader1.load();
                            Stage primaryStage = new Stage();
                            primaryStage.setScene(new Scene(root1));
                            primaryStage.setTitle("Manager Student");
                            primaryStage.getIcons().add(new Image("image/Student-3-icon.png"));
                            primaryStage.show();

                            anchorPane.getScene().getWindow().hide();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {

                        Stage primaryStage = new Stage();
                        primaryStage.setScene(new Scene(root));
                        primaryStage.setTitle("Manager Student");
                        primaryStage.getIcons().add(new Image("image/Student-3-icon.png"));
                        primaryStage.show();

                        anchorPane.getScene().getWindow().hide();

                    }
                }
            });
        }
    }
}



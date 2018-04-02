package addstudentviews;


import controller.StudentController;
import entityPost.StudentPost;
import entityPost.StudentAttributes;
import entityPost.StudentData;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import managerview.ManagerViewController;
import progressindicator.ProgressIndicators;
import utilityImage.PostImage;
import validate.Validate;
import welcomeview.WelcomeController;
import validate.ErrMap;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddStudentController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtRollNumber;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAvatar;
    @FXML
    private Button btnSave, btnReset, btnChoose;

    private Button btnOk;

    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private ImageView imageView;
    @FXML
    private Label errName;
    @FXML
    private Label errRollNumber;
    @FXML
    private Label errPhone;
    @FXML
    private Label errEmail;
    @FXML
    private Label labelReport;

    private FileChooser fileChooser;
    private File file;
    private String uploadUrl;
    private String avatarUrl = "";
    private ArrayList<entity.Student> listStudents ;
    private Task uploadImageWorker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image("/image/avatar-empty.png"));
    }

    public void handleReset(ActionEvent actionEvent) {
        txtName.setText("");
        txtRollNumber.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAvatar.setText("");
        errRollNumber.setText("");
        errName.setText("");
        errPhone.setText("");
        errEmail.setText("");
        progressIndicator.setVisible(false);
        imageView.setImage(new Image("/image/avatar-empty.png"));
    }

    public void handleSave(ActionEvent actionEvent) {
        boolean valid = isInputValid();
        if (valid == true) {

            StudentAttributes studentAttributes = new StudentAttributes(1,txtRollNumber.getText(), txtName.getText(), txtEmail.getText(), txtPhone.getText(), avatarUrl);

            StudentData studentData = new StudentData(studentAttributes, "User");

            StudentPost student = new StudentPost(studentData);

            int respone = StudentController.postStudent(student);

            if(respone == 201){
                listStudents = StudentController.getListStudent();
                WelcomeController.fxmlMain.<ManagerViewController>getController().refresh(listStudents);

                anchorPane.getChildren().remove(btnSave);
                anchorPane.getChildren().remove(btnReset);
                btnOk = new Button("OK");
                anchorPane.getChildren().add(btnOk);
                btnOk.relocate(248,347);
                btnOk.setPrefSize(81,25);
                btnOk.setOnAction(event -> {
                    Stage stage = (Stage)btnOk.getScene().getWindow();
                    stage.hide();
                });
                labelReport.setText("Add Success !!!");
            }else if(respone == 409) {
                errRollNumber.setText(ErrMap.errMap.get("rollNumber").getErrDuplicate());
            }

        }else {
            System.out.println("false");
        }

    }

    public void handleChoose(ActionEvent actionEvent){
        progressIndicator.progressProperty().unbind();
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files","*.png", "*.jpg")
        );
        file = fileChooser.showOpenDialog((Stage)btnChoose.getScene().getWindow());
        if(file != null){

            progressIndicator.setVisible(true);

            txtAvatar.setText(file.getName());

            PostImage postImage = new PostImage();
            uploadUrl = postImage.loadUploadUrl();
            avatarUrl = postImage.uploadImage(file, uploadUrl);

            ProgressIndicators  progressIndicators = new ProgressIndicators();
            uploadImageWorker = progressIndicators.createWorker();
            progressIndicators.progressLoad(progressIndicator, uploadImageWorker, imageView, avatarUrl);
        }
    }

    Validate validate = new Validate();

    public boolean isInputValid() {
        boolean valid = false;
        boolean rollNumber = validate.validateRollNumber(txtRollNumber.getText(), errRollNumber);
        boolean name = validate.validateName(txtName.getText(), errName);
        boolean phone = validate.validatePhone(txtPhone.getText(), errPhone);
        boolean email = validate.validateEmail(txtEmail.getText(), errEmail);

        if(rollNumber == true && name == true && phone == true && email == true){
            valid = true;
        }
        return valid;
    }

    public void onKeyReleasedRollNumber(KeyEvent keyEvent){
         validate.validateRollNumber(txtRollNumber.getText(), errRollNumber);
    }
    public void onKeyReleasedName(KeyEvent keyEvent){
         validate.validateName(txtName.getText(), errName);
    }
    public void onKeyReleasedPhone(KeyEvent keyEvent){
         validate.validatePhone(txtPhone.getText(), errPhone);
    }
    public void onKeyReleasedEmail(KeyEvent keyEvent){
         validate.validateEmail(txtEmail.getText(), errEmail);
    }

}

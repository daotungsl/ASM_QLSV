package infostudentview;

import controller.StudentController;
import deletestudentviews.DeleteSudentController;
import entity.Student;
import entityPost.StudentAttributes;
import entityPost.StudentData;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InfoStudentController implements Initializable {

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
    private Label labelTitle;
    @FXML
    private Label errName;
    @FXML
    private Label errRollNumber;
    @FXML
    private Label errPhone;
    @FXML
    private Label errEmail;
    @FXML
    private Label errReport;

    @FXML
    private Button btnEdit, btnDelete, btnChoose;
    @FXML
    private ImageView imageView;
    @FXML
    private ProgressIndicator progressIndicator;

    private Button btnBack;

    private FileChooser fileChooser;
    private File file;
    private String uploadUrl;
    private String avatarUrl;
    private Task uploadImageWorker;
    private ArrayList<Student> listStudents;
    private int changebtn = 0;
    private Student student;
    public void setStudent(Student student) {
        this.student = student;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void info(){

        txtRollNumber.setText(student.getRollNumber());
        txtName.setText(student.getName());
        txtPhone.setText(student.getPhone());
        txtEmail.setText(student.getEmail());
        txtAvatar.setText(student.getAvatar());

        avatarUrl = student.getAvatar();

        errRollNumber.setText("");
        errName.setText("");
        errPhone.setText("");
        errEmail.setText("");


        txtRollNumber.setDisable(true);
        txtName.setDisable(true);
        txtPhone.setDisable(true);
        txtEmail.setDisable(true);
        txtAvatar.setDisable(true);
        btnChoose.setDisable(true);

        if(student.getAvatar().isEmpty()) {
            imageView.setImage(new Image("/image/avatar-empty.png"));
        }else {
            imageView.setImage(new Image(student.getAvatar()));
        }
    }

    public void handleEdit(ActionEvent actionEvent){

        if(changebtn == 0) {
//button edit
            txtRollNumber.setDisable(false);
            txtName.setDisable(false);
            txtPhone.setDisable(false);
            txtEmail.setDisable(false);
            txtAvatar.setDisable(false);
            btnChoose.setDisable(false);

            labelTitle.setText("EDIT STUDENT");
            btnEdit.setText("Save");
            btnDelete.setText("Reset");

            // button back
            btnBack = new Button("Back");
            btnBack.setPrefSize(81,25);
            btnBack.relocate(29,16);
            btnBack.setOnAction(event -> {
                labelTitle.setText("INFO STUDENT");
                info();
                anchorPane.getChildren().remove(btnBack);
                btnEdit.setText("Edit");
                btnDelete.setText("Delete");
                changebtn = 0;
            });
            anchorPane.getChildren().add(btnBack);

            changebtn = 1;

        }else {
// button save
            boolean valid = isInputValid();

            if (valid == true) {

                StudentAttributes studentAttributes = new StudentAttributes(1,txtRollNumber.getText(), txtName.getText(), txtEmail.getText(), txtPhone.getText(), avatarUrl);

                StudentData studentData = new StudentData(studentAttributes, "User");

                entityPost.StudentPost studentUpdate = new entityPost.StudentPost(studentData);

                int status = StudentController.updateStudent(studentUpdate, student.getId());

                if(status == 1){
                    listStudents = StudentController.getListStudent();
                    WelcomeController.fxmlMain.<ManagerViewController>getController().refresh(listStudents);
                    // view change to edit success
                    changeView("Edit Success !!!");
                    anchorPane.getChildren().remove(btnBack);
                }else{
                    // view change to edit fail
                    changeView("Edit Fail. Please try later. !!!");
                    anchorPane.getChildren().remove(btnBack);
                }

            }else {
                System.out.println("false");
            }
        }
    }

    public void handleDelete(ActionEvent actionEvent){

        if(changebtn == 0) {
//button delete
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/deletestudentviews/DeleteStudent.fxml"));
            try {
                Parent root = fxmlLoader.load();
                fxmlLoader.<DeleteSudentController>getController().setStudent(student);
                fxmlLoader.<DeleteSudentController>getController().setChoise(1);
                Stage stage = new Stage();
                stage.setTitle("Delete Student");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
// button reset
            txtRollNumber.setText(student.getRollNumber());
            txtName.setText(student.getName());
            txtPhone.setText(student.getPhone());
            txtEmail.setText(student.getEmail());
            txtAvatar.setText(student.getAvatar());

            errRollNumber.setText("");
            errName.setText("");
            errPhone.setText("");
            errRollNumber.setText("");
            progressIndicator.setVisible(false);

            if(student.getAvatar().isEmpty()) {
                imageView.setImage(new Image("image/avatar-empty.png"));
            }else {
                imageView.setImage(new Image(student.getAvatar()));
            }
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
            uploadUrl =  postImage.loadUploadUrl();
            avatarUrl = postImage.uploadImage(file, uploadUrl);

            ProgressIndicators progressIndicators = new ProgressIndicators();
            uploadImageWorker = progressIndicators.createWorker();
            progressIndicators.progressLoad(progressIndicator, uploadImageWorker, imageView, avatarUrl);
        }
    }

// change view to edit success or edit fail
    public void changeView(String report){
        anchorPane.getChildren().remove(btnDelete);
        anchorPane.getChildren().remove(btnEdit);

        errReport.setText(report);

        Button btnOk = new Button("OK");
        btnOk.relocate(277,350);
        btnOk.setPrefSize(81,25);
        btnOk.setOnAction(event -> {
            Stage stage = (Stage)btnOk.getScene().getWindow();
            stage.hide();
        });
        anchorPane.getChildren().add(btnOk);
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

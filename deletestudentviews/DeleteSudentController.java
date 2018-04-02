package deletestudentviews;

import controller.StudentController;
import entity.Student;
import entityPost.StudentAttributes;
import entityPost.StudentData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import managerview.ManagerViewController;
import infostudentview.InfoStudentController;
import welcomeview.WelcomeController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteSudentController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelDelete;
    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;

    private Button btnOk;

    private int choise = 0;

    public void setChoise(int choise) {
        this.choise = choise;
    }

    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    private ArrayList<Student> listStudents;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleYes(ActionEvent actionEvent){

        StudentAttributes studentAttributes = new StudentAttributes(1, student.getRollNumber(), student.getName(),student.getEmail(), student.getPhone(), student.getAvatar());
        StudentData studentData = new StudentData(studentAttributes, "User");
        entityPost.StudentPost studentDelete = new entityPost.StudentPost(studentData);

        int status = StudentController.deleteStudent(studentDelete, student.getId());

        if(status == -1){
            listStudents = StudentController.getListStudent();
            WelcomeController.fxmlMain.<ManagerViewController>getController().refresh(listStudents);

            if(choise == 0) {
                // choise = 0 change view delete when click button delete in colum Delete.
                changeView("Delete Success !!!");
            }else {
                // choise != 0 change view delete when click button delete in view infostudent.
                Stage stage = (Stage)btnYes.getScene().getWindow();
                stage.hide();
                ManagerViewController.fxmlInfo.<InfoStudentController>getController().changeView("Delete Success !!!");
            }
        }else {
            if(choise == 0){
                // choise = 0 change view delete when click button delete in colum Delete.
                changeView("Delete Fail !!!");
            }else {
                // choise != 0 change view delete when click button delete in view infostudent.
                Stage stage = (Stage)btnYes.getScene().getWindow();
                stage.hide();
                ManagerViewController.fxmlInfo.<InfoStudentController>getController().changeView("Delete Fail !!!");
            }
        }
    }

    public void handleNo(ActionEvent actionEvent){
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.hide();
    }

    // change view delete to delete success or delete fail
    public void changeView(String report){
        anchorPane.getChildren().remove(btnYes);
        anchorPane.getChildren().remove(btnNo);
        labelDelete.setText(report);
        btnOk = new Button("OK");
        btnOk.relocate(143,67);
        btnOk.setPrefSize(64,25);
        btnOk.setOnAction(event -> {
            Stage stage = (Stage)btnOk.getScene().getWindow();
            stage.hide();
        });
        anchorPane.getChildren().add(btnOk);
    }
}

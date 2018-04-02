package entity;

import deletestudentviews.DeleteSudentController;
import editstudentviews.EditStudentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stages.ListStage;

public class Student {

    private String id;
    private int stt;
    private String rollNumber;
    private String name;
    private String phone;
    private String email;
    private String avatar;
    private Button delete;
    private Button edit;

    public Student() {

    }

    public Student(String id, int stt, String rollNumber, String name, String phone, String email, String avatar) {
        this.id = id;
        this.stt = stt;
        this.rollNumber = rollNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.delete = new Button();
        this.edit = new Button();
        this.delete.setOnAction(this::handleDeleteAction);
        this.edit.setOnAction(this::handleEditAction);

        this.delete.setStyle(
//                "-fx-background-radius: 1em; " +
                "-fx-min-width: 32px; " +
                "-fx-min-height: 32px; " +
                "-fx-max-width: 32px; " +
                "-fx-max-height: 32px; " +
                "-fx-graphic: url('/image/if_user_male_remove_11372.png');" +
                "-fx-graphic-size:30px 30px ;" +
                "-fx-background-insets: 0px; " +
                "-fx-padding: 0px;"
        );

        this.edit.setStyle(
//                "-fx-background-radius: 1em; " +
                "-fx-min-width: 32px; " +
                "-fx-min-height: 32px; " +
                "-fx-max-width: 32px; " +
                "-fx-max-height: 32px; " +
                "-fx-graphic: url('/image/if_user_male_edit_11363.png');" +
                "-fx-graphic-size:30px 30px ;" +
                "-fx-background-insets: 0px; " +
                "-fx-padding: 0px;"
        );
    }


    public void handleDeleteAction(ActionEvent actionEvent){

        try{
            if(ListStage.listStage.size() == 0) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/deletestudentviews/DeleteStudent.fxml"));
                Parent root = fxmlLoader.load();
                DeleteSudentController deleteSudentController = fxmlLoader.<DeleteSudentController>getController();
                deleteSudentController.setStudent(new Student(this.id, this.stt, this.rollNumber, this.name, this.phone, this.email, this.avatar));
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Delete Student");
                primaryStage.getIcons().add(new Image("image/Student-3-icon.png"));
                primaryStage.show();
                // add this stage into listStage.
                ListStage.listStage.add(primaryStage);
            }else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/deletestudentviews/DeleteStudent.fxml"));
                Parent root = fxmlLoader.load();
                DeleteSudentController deleteSudentController = fxmlLoader.<DeleteSudentController>getController();
                deleteSudentController.setStudent(new Student(this.id, this.stt, this.rollNumber, this.name, this.phone, this.email, this.avatar));
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Delete Student");
                primaryStage.getIcons().add(new Image("image/Student-3-icon.png"));
                primaryStage.show();
                // get stage before(displaying) hide it, then remove it , and then add stage new into listStage
                ListStage.listStage.get(0).hide();
                ListStage.listStage.remove(0);
                ListStage.listStage.add(primaryStage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleEditAction(ActionEvent actionEvent){

        try{
            if(ListStage.listStage.size() == 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editstudentviews/EditStudent.fxml"));
                Parent root = fxmlLoader.load();
                EditStudentController editStudentController = fxmlLoader.<EditStudentController>getController();
                editStudentController.setStudent(new Student(this.id, this.stt, this.rollNumber, this.name, this.phone, this.email, this.avatar));
                editStudentController.info();
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Edit Student");
                primaryStage.getIcons().add(new Image("image/Student-3-icon.png"));
                primaryStage.show();

                ListStage.listStage.add(primaryStage);
            }else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editstudentviews/EditStudent.fxml"));
                Parent root = fxmlLoader.load();
                EditStudentController editStudentController = fxmlLoader.<EditStudentController>getController();
                editStudentController.setStudent(new Student(this.id, this.stt, this.rollNumber, this.name, this.phone, this.email, this.avatar));
                editStudentController.info();
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Edit Student");
                primaryStage.getIcons().add(new Image("image/Student-3-icon.png"));
                primaryStage.show();

                ListStage.listStage.get(0).hide();
                ListStage.listStage.remove(0);
                ListStage.listStage.add(primaryStage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }
}

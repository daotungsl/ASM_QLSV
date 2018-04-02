package managerview;

import controller.StudentController;
import entity.Student;
import infostudentview.InfoStudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stages.ListStage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ManagerViewController implements Initializable {

    @FXML
    private Button btnAddStudent, btnFirst, btnLast;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> stt;
    @FXML
    private TableColumn<Student, String> rollNumber;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> phone;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableColumn<Student, String> avatar;
    @FXML
    private TableColumn<Student, Button> delete;
    @FXML
    private TableColumn<Student, Button> edit;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField txtSearch;

    private int totalPage;

    private ObservableList<Student> data;
    private FilteredList<Student> filteredData;

    private final static int rowsPerPage = 10;
    public ArrayList<Student> listStudent = StudentController.getListStudent();
    public static FXMLLoader fxmlInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        refresh(listStudent);
        clickDoubleIntoRowTable();
    }

// refresh tableview when data change
    public void refresh(ArrayList<Student> listStudent) {

        data = FXCollections.observableArrayList(listStudent);

        filteredData = new FilteredList<>(data, e -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Student>) student -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (student.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (student.getRollNumber().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            pagination.setPageCount((int) (Math.ceil(filteredData.size() * 1.0 / rowsPerPage)));
            changeTableView(0, rowsPerPage);
        });

        stt.setCellValueFactory(new PropertyValueFactory<Student, Integer>("stt"));
        rollNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("rollNumber"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        avatar.setCellValueFactory(new PropertyValueFactory<Student, String>("avatar"));
        delete.setCellValueFactory(new PropertyValueFactory<Student, Button>("delete"));
        edit.setCellValueFactory(new PropertyValueFactory<Student, Button>("edit"));

        stt.setStyle("-fx-alignment: CENTER;");
        rollNumber.setStyle("-fx-alignment: CENTER-LEFT;");
        name.setStyle("-fx-alignment: CENTER-LEFT;");
        phone.setStyle("-fx-alignment: CENTER-LEFT;");
        email.setStyle("-fx-alignment: CENTER-LEFT;");
        avatar.setStyle("-fx-alignment: CENTER-LEFT;");
        delete.setStyle("-fx-alignment: CENTER;");
        edit.setStyle("-fx-alignment: CENTER;");

        totalPage = (int) (Math.ceil(data.size() * 1.0 / rowsPerPage));
        pagination.setPageCount(totalPage);
        pagination.setCurrentPageIndex(0);
        changeTableView(0, rowsPerPage);
        pagination.currentPageIndexProperty().addListener(
                (observable, oldValue, newValue) -> changeTableView(newValue.intValue(), rowsPerPage));
    }

    //index pagination change then tableview change
    public void changeTableView(int index, int limit) {

        int fromIndex = index * limit;
        int toIndex = Math.min(fromIndex + limit, data.size());

        int minIndex = Math.min(toIndex, filteredData.size());
        SortedList<Student> sortedData = new SortedList<>(
                FXCollections.observableArrayList(filteredData.subList(Math.min(fromIndex, minIndex), minIndex)));
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    public void handleFirst(ActionEvent actionEvent){
        pagination.setCurrentPageIndex(0);
        changeTableView(0,rowsPerPage);
        btnLast.setDisable(false);
        btnFirst.setDisable(true);
    }
    public void handleLast(ActionEvent actionEvent){
        pagination.setCurrentPageIndex(totalPage - 1);
        changeTableView((totalPage - 1),rowsPerPage );
        btnFirst.setDisable(false);
        btnLast.setDisable(true);
    }

    public void handleAddStudent(ActionEvent actionEvent) {

        try {
            if(ListStage.listStage.size() == 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addstudentviews/AddStudent.fxml"));
                Parent root = fxmlLoader.load();
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Add Student");
                primaryStage.getIcons().add(new Image("image/Student-3-icon.png"));
                primaryStage.show();
                // add this stage into listStage.
                ListStage.listStage.add(primaryStage);

            }else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addstudentviews/AddStudent.fxml"));
                Parent root = fxmlLoader.load();
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Add Student");
                primaryStage.getIcons().add(new Image("image/Student-3-icon.png"));
                primaryStage.show();
                // get stage before(displaying) hide it, then remove it , and then add stage new into listStage
                ListStage.listStage.get(0).hide();
                ListStage.listStage.remove(0);
                ListStage.listStage.add(primaryStage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // when click into per row tableview then display view infostudent.
    public void clickDoubleIntoRowTable(){
        tableView.setRowFactory(param -> {
            TableRow<Student> row  = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    Student st = row.getItem();
                    System.out.println(st.getName());
                    try {
                        if(ListStage.listStage.size() == 0) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/infostudentview/InfoStudent.fxml"));
                            fxmlInfo = fxmlLoader;
                            Parent root = fxmlLoader.load();
                            fxmlLoader.<InfoStudentController>getController().setStudent(st);
                            fxmlLoader.<InfoStudentController>getController().info();
                            Stage stage = new Stage();
                            stage.setTitle("Info Student");
                            stage.setScene(new Scene(root));
                            stage.show();

                            ListStage.listStage.add(stage);
                        }else {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/infostudentview/InfoStudent.fxml"));
                            fxmlInfo = fxmlLoader;
                            Parent root = fxmlLoader.load();
                            fxmlLoader.<InfoStudentController>getController().setStudent(st);
                            fxmlLoader.<InfoStudentController>getController().info();
                            Stage stage = new Stage();
                            stage.setTitle("Info Student");
                            stage.setScene(new Scene(root));
                            stage.show();

                            ListStage.listStage.get(0).hide();
                            ListStage.listStage.remove(0);
                            ListStage.listStage.add(stage);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }
}

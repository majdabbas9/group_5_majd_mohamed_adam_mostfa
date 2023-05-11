

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Data;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class AllStudents implements Initializable {

    //@FXML // fx:id="StudentsList"
    //private ListView<String> StudentsList; // Value injected by FXMLLoader

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Student,Integer> studentNo;

    @FXML
    private TableColumn<Student,String> firstName;
    @FXML
    private TableColumn<Student,String> secondName;
    @FXML
    private TableColumn<Student,String> studentID;

    @FXML // fx:id="studentName"
    private Label studentName; // Value injected by FXMLLoader

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        App.setRoot("First");
    }

    @FXML
    void showStudentGrades(ActionEvent event) throws IOException {
        int index=tableView.getSelectionModel().getSelectedIndex();
        if(index!=-1)
        {
            App.setRoot("StudentGrade");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Student> students=(List<Student>) Data.S;
        ObservableList<Student> observableList = FXCollections.observableArrayList();
        Student st =new Student();
        for (Student s: students){
            observableList.add(new Student(s.getId(),s.getFirstName(),s.getSecondName(),s.getIdNum()));
        }

        studentNo.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<Student,String>("secondName"));
        studentID.setCellValueFactory(new PropertyValueFactory<Student,String>("idNum"));
        tableView.setItems(observableList);

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int index=tableView.getSelectionModel().getSelectedIndex();
                if(index==-1)
                {
                    studentName.setText("");
                }
                else {
                    Student s =(Student) tableView.getSelectionModel().getSelectedItem();
                    System.out.println(s.getId());
                    studentName.setText(s.getFirstName()+" "+s.getSecondName());
                }
            }
        });
    }

}

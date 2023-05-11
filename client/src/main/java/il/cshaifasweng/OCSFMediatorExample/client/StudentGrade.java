/**
 * Sample Skeleton for 'studentGrade.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.GradeOfStudent;

import il.cshaifasweng.OCSFMediatorExample.entities.Data;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentGrade implements Initializable {

    @FXML // fx:id="Grade"
    private TableColumn<GradeOfStudent,Integer> Grade; // Value injected by FXMLLoader

    @FXML // fx:id="StudentID"
    private Text StudentID; // Value injected by FXMLLoader

    @FXML // fx:id="gradeNO"
    private TableColumn<GradeOfStudent, Integer> gradeNO; // Value injected by FXMLLoader

    @FXML // fx:id="gradeTable"
    private TableView<GradeOfStudent> gradeTable; // Value injected by FXMLLoader

    @FXML // fx:id="newGrade"
    private TextField newGrade; // Value injected by FXMLLoader

    @FXML // fx:id="studentName"
    private Text studentName; // Value injected by FXMLLoader

    @FXML
    void backToAll(ActionEvent event) throws IOException {
        MsgClass msg = new MsgClass("#get all students", null);
        SimpleClient.getClient().sendToServer(msg);
    }

    @FXML
    void updateGrade(ActionEvent event) {
        int index=gradeTable.getSelectionModel().getSelectedIndex();
        int num;
        if(index!=-1) {
            try {
                num=Integer.valueOf(newGrade.getText());
            }
            catch (Exception ex)
            {
                newGrade.setText("please Enter a valid number !");
                return;
            }
            if(num<0)
            {
                newGrade.setText("please Enter a non negative number !");
                return;
            }
            String msgString="#update";
            try
            {
                //,gradeTable.getSelectionModel().getSelectedIndex(),num
                MsgClass msg = new MsgClass(msgString,Data.selectedStudent,gradeTable.getSelectionModel().getSelectedIndex(),num);
                SimpleClient.getClient().sendToServer(msg);
                App.setRoot("StudentGrade");

                //initialize();
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        }
        else
        {
            newGrade.setText("pick a grade plaese!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student st= Data.selectedStudent;
        studentName.setText(st.getFirstName());
        StudentID.setText(st.getIdNum());
        ObservableList<GradeOfStudent> observableList = FXCollections.observableArrayList();
        observableList.add(new GradeOfStudent(1,st.getFirstGrade()));
        observableList.add(new GradeOfStudent(2,st.getSecondGrade()));
        observableList.add(new GradeOfStudent(3,st.getThirdGrade()));
        gradeNO.setCellValueFactory(new PropertyValueFactory<GradeOfStudent,Integer>("gradeNo"));
        Grade.setCellValueFactory(new PropertyValueFactory<GradeOfStudent,Integer>("grade"));
        gradeTable.setItems(observableList);
    }
}

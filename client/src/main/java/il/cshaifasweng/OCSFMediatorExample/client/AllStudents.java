

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AllStudents implements Initializable {

    @FXML // fx:id="StudentsList"
    private ListView<?> StudentsList; // Value injected by FXMLLoader

    @FXML // fx:id="studentName"
    private Label studentName; // Value injected by FXMLLoader

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        App.setRoot("First");
    }

    @FXML
    void showStudentGrades(ActionEvent event) throws IOException {
        int index=StudentsList.getSelectionModel().getSelectedIndex();
        if(index!=-1)
        {
            App.setRoot("StudentGrade");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentsList.getItems().clear();
        StudentsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int index=StudentsList.getSelectionModel().getSelectedIndex();
                if(index!=-1)
                {
                    studentName.setText("");
                }
            }
        });
    }

}

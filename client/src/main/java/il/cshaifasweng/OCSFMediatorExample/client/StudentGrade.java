/**
 * Sample Skeleton for 'studentGrade.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentGrade implements Initializable {

    @FXML // fx:id="gradesList"
    private ListView<?> gradesList; // Value injected by FXMLLoader

    @FXML // fx:id="newGrade"
    private TextField newGrade; // Value injected by FXMLLoader

    @FXML
    void backToAll(ActionEvent event) throws IOException {
        App.setRoot("allStudents");
    }

    @FXML
    void updateGrade(ActionEvent event) {
        int index=gradesList.getSelectionModel().getSelectedIndex();
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


        }
        else
        {
            newGrade.setText("enter a grade plaese!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

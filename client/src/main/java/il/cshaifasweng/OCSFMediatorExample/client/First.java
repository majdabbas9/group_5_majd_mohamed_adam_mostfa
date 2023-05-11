/**
 * Sample Skeleton for 'First.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class First {

    @FXML
    void showAllStudents(ActionEvent event) throws IOException {
        MsgClass msg = new MsgClass("#get all students", null);
        SimpleClient.getClient().sendToServer(msg);
    }

}

package lk.ijse.examsimulator.ui.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by oshan on 04-Jan-18.
 *
 * @author oshan
 */
public class MonitorScreen extends AbstractController implements Initializable {
    @FXML
    ImageView viewStream;
    @FXML
    StackPane monitorPane;

    private String student;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewStream.fitWidthProperty().bind(monitorPane.widthProperty());
        viewStream.fitHeightProperty().bind(monitorPane.heightProperty());

    }

    public void streamScreen(byte[] imageStream){
        Platform.runLater(()->viewStream.setImage(new Image(new ByteArrayInputStream(imageStream))));
        System.gc();
    }

    public void setStudent(String student){
        this.student=student;
    }

}

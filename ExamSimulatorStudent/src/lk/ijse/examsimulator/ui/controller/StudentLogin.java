package lk.ijse.examsimulator.ui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.examsimulator.proxy.ProxyHandler;
import lk.ijse.examsimulator.service.ServiceFactory;
import lk.ijse.examsimulator.service.custom.StudentService;
import lk.ijse.examsimulator.ui.util.helpers.ResizeHelper;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
public class StudentLogin implements Initializable{

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;

    private StudentService service;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            service=(StudentService) ProxyHandler.getInstance().getServiceFactory().getService(ServiceFactory.ServiceType.STUDENT);
        } catch (Exception ignored) {

        }

    }

    @FXML
    private void login(){
        String username=txtUsername.getText();
        String password=txtPassword.getText();

        try {
            boolean bool=service.login(username,password);
            if(bool){
                Stage primaryStage=new Stage();

                FXMLLoader loader=new FXMLLoader(getClass().getResource("/lk/ijse/examsimulator/ui/fxml/StudentUI.fxml"));

                Scene scene = new Scene(loader.load());

                StudentUI controller=loader.getController();

                controller.setStudentId(username);
                controller.setService(service);

                primaryStage.initStyle(StageStyle.TRANSPARENT);

                scene.setFill(Color.TRANSPARENT);
                primaryStage.setScene(scene);
                ResizeHelper.addResizeListener(primaryStage);

                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

                primaryStage.setWidth(screen.getWidth()/1.2);
                primaryStage.setHeight(screen.getHeight()/1.2);
                primaryStage.getIcons().add(new Image("/lk/ijse/examsimulator/ui/util/icons/logo.png"));
                primaryStage.setOnCloseRequest(e->{
                    Platform.exit();
                    System.exit(0);
                });
                primaryStage.show();
                ((Stage)txtPassword.getScene().getWindow()).close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package lk.ijse.examsimulator.ui.controller;

import com.jfoenix.controls.JFXSnackbar;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.observer.ExaminerObserver;
import lk.ijse.examsimulator.observer.StudentObserver;
import lk.ijse.examsimulator.observer.impl.ExaminerObserverImpl;
import lk.ijse.examsimulator.proxy.ProxyHandler;
import lk.ijse.examsimulator.service.ServiceFactory;
import lk.ijse.examsimulator.service.custom.ExaminerService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by oshan on 16-Dec-17.
 * @author oshan
 */
public class Application extends AbstractController implements Initializable{

    private ExaminerObserver observer;

    private ExaminerService service;

    /////////////////
//    @FXML
//    private javafx.scene.control.TextArea area;
//    @FXML
//    private Label lblOut;
//    @FXML
//    private Button btnExecute;
    //////////////////////


    @FXML
    private AnchorPane titlePane,navTitle,navigator,root;
    @FXML
    private HBox titleNameContainer;
    @FXML
    private Button btnMaximize;
    @FXML
    private StackPane container,studentListContainer;
    @FXML
    private VBox navTextContainer,studentContainer;

    ////////Create panes for different buttons////////////
    private AnchorPane dashboardPane;
    private AnchorPane monitoringPane;
    private AnchorPane manageExamsPane;

    //Controllers
    private MonitorScreen monitorController;
    private Dashboard dashboardController;

    ///////////////////////

    private double offsetX;
    private double offsetY;

    public Application(){
        try {
            observer=new ExaminerObserverImpl(this);

            try {
                service=(ExaminerService) ProxyHandler.getInstance().getServiceFactory().getService(ServiceFactory.ServiceType.EXAMINER);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //////Initialize Screens/////
            FXMLLoader monitorLoader=new FXMLLoader(getClass().getResource("/lk/ijse/examsimulator/ui/fxml/MonitorScreen.fxml"));
            monitoringPane=monitorLoader.load();
            monitorController=monitorLoader.getController();
            //------------------------------------//
            FXMLLoader dashboardLoader=new FXMLLoader(getClass().getResource("/lk/ijse/examsimulator/ui/fxml/Dashboard.fxml"));
            dashboardPane=dashboardLoader.load();
            dashboardController=dashboardLoader.getController();
            //-------------------------------------//
            //////////End of Initializing Screens/////////////////////


        } catch (Exception e) {
            e.printStackTrace();
        }

        AbstractController.setMainController(this);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Label lblName=new Label("Dashboard");
        lblName.setStyle("-fx-font-weight: bold");
        titleNameContainer.getChildren().clear();
        titleNameContainer.getChildren().add(lblName);

        titlePane.setOnMousePressed(event -> {
            offsetX=event.getSceneX();
            offsetY=event.getSceneY();
        });

        titlePane.setOnMouseDragged(event -> {
            (titlePane.getScene().getWindow()).setX(event.getScreenX()-offsetX);
            (titlePane.getScene().getWindow()).setY(event.getScreenY()-offsetY);
        });

        navTitle.setOnMousePressed(event -> {
            offsetX=event.getSceneX();
            offsetY=event.getSceneY();
        });

        navTitle.setOnMouseDragged(event -> {
            (navTitle.getScene().getWindow()).setX(event.getScreenX()-offsetX);
            (navTitle.getScene().getWindow()).setY(event.getScreenY()-offsetY);
        });

        container.getChildren().setAll(dashboardPane);
        updateLoggedStudents();


////////////////////////////////////////////////////
//        btnExecute.setOnAction(e->{
//            String source=area.getText();
//            Executable executable=new ExecutableImpl();
//            try {
//                String output=executable.getOutputFromSource(source,"Demo");
//                lblOut.setText(output);
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        });
//
//        ///////////////////////////////////////

    }

    public void updateLoggedStudents(){
        try {
            Platform.runLater(()-> studentContainer.getChildren().clear());
            ArrayList<StudentObserver> obs=service.getStudents();
            for(StudentObserver ob:obs){
                informStudentLogin(ob.getStudent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeAction(ActionEvent evt){
        try {
            observer.removeExaminerObserver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void navButtonClick(ActionEvent event){
        String name=((javafx.scene.control.Button)event.getSource()).getText();
        Label lblName=new Label(name);
        lblName.setStyle("-fx-font-weight: bold");
        titleNameContainer.getChildren().clear();
        titleNameContainer.getChildren().add(lblName);

        switch (name){
            case "Monitoring":container.getChildren().setAll(monitoringPane);break;
            case "Dashboard":container.getChildren().setAll(dashboardPane);break;
        }
    }

    @FXML
    private void minimize(ActionEvent evt){
        ((Stage)((Button)evt.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    private void restoreMaximize(ActionEvent event){
        Stage stage=((Stage)((Button)event.getSource()).getScene().getWindow());
        if(!stage.isMaximized()){
            stage.setMaximized(true);
            btnMaximize.setGraphic(new ImageView(new Image("/lk/ijse/examsimulator/ui/util/icons/maximize.png")));
            btnMaximize.getTooltip().setText("Restore Down");
        }else{
            stage.setMaximized(false);
            btnMaximize.setGraphic(new ImageView(new Image("/lk/ijse/examsimulator/ui/util/icons/restore.png")));
            btnMaximize.getTooltip().setText("Maximize");
        }
    }
    @FXML
    private void listNavHover(){
        TranslateTransition tr=new TranslateTransition(new Duration(300),studentListContainer);
        tr.setCycleCount(1);
        if(studentListContainer.getTranslateX()==0){
            tr.setToX(223);
            tr.play();
        }else{
            tr.setToX(0);
            tr.play();
        }
    }

    private void startMonitoring(String studentId){
        try {
            container.getChildren().setAll(monitoringPane);
            service.requestScreenShare(studentId);
            monitorController.setStudent(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method should be invoked through student application once he/she opens a restricted app
    public void notifyRestrictedAppBehaviour(String studentId){
        Platform.runLater(()->{
            JFXSnackbar snackbar=new JFXSnackbar(root);

            snackbar.show(studentId+" is using restricted app!","Check",10000, event -> {
                startMonitoring(studentId);
                snackbar.unregisterSnackbarContainer(root);
            });
        });

    }

    public void streamStudentScreen(byte[] imageStream) {
        monitorController.streamScreen(imageStream);
    }

    public void informStudentLogin(final String studentId){
        try{
            Button button=new Button(studentId);
            button.setOnAction(e-> {
                Platform.runLater(() -> {
                    Label lblName = new Label("Monitoring");
                    lblName.setStyle("-fx-font-weight: bold");
                    titleNameContainer.getChildren().clear();
                    titleNameContainer.getChildren().add(lblName);
                });
                startMonitoring(studentId);
            });
            Platform.runLater(()->studentContainer.getChildren().add(button));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void uploadPaper(Paper paper){
        try {
            service.uploadPaper(paper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
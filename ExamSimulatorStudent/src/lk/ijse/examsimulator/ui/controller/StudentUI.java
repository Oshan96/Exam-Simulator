package lk.ijse.examsimulator.ui.controller;

import com.jfoenix.controls.JFXDrawer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.observer.StudentObserver;
import lk.ijse.examsimulator.observer.impl.StudentObserverImpl;
import lk.ijse.examsimulator.proxy.ProxyHandler;
import lk.ijse.examsimulator.service.custom.StudentService;
import sun.awt.image.codec.JPEGImageEncoderImpl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Executable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by oshan on 28-Dec-17.
 *
 * @author oshan
 */
public class StudentUI implements Initializable{

    private final Object THREAD_LOCK = new Object();
    private boolean isBlock=false;

    @FXML
    private Button btnMaximize,btnExecute;
    @FXML
    private AnchorPane titlePane,msgBoxContainer;
    @FXML
    private JFXDrawer msgDrawer;

    @FXML
    private TextArea txtSource;
    @FXML
    private Label lblOut;

    private StudentService service;
    private StudentObserver observer;
    private String studentId="";

    private double offsetX;
    private double offsetY;

    private final java.awt.Rectangle screenRect;
    private final ByteArrayOutputStream baos;
    private final StreamerThread streamerThread;


    public StudentUI() {
        screenRect = new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        baos= new ByteArrayOutputStream();
        streamerThread=new StreamerThread();
        streamerThread.setDaemon(true);
        try {
            observer=new StudentObserverImpl(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setStudentId(String studentId){
        this.studentId=studentId;
    }
    public String getStudentId(){
        return studentId;
    }
    public void setService(StudentService service){
        this.service=service;
        try {
            service.notifyRestrictedAppBehaviour(studentId);
            service.informStudentLogin(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        msgDrawer.setSidePane(msgBoxContainer);
        titlePane.setOnMousePressed(event -> {
            offsetX=event.getSceneX();
            offsetY=event.getSceneY();
        });

        titlePane.setOnMouseDragged(event -> {
            (titlePane.getScene().getWindow()).setX(event.getScreenX()-offsetX);
            (titlePane.getScene().getWindow()).setY(event.getScreenY()-offsetY);
        });

        btnExecute.setOnAction(event->{
            try {
                String output=service.getOutput(txtSource.getText());
                lblOut.setText(output);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void drawerAction(ActionEvent evt){
        if(msgDrawer.isHidden()){
            msgDrawer.open();
            FadeTransition ft=new FadeTransition();
            ft.setDuration(Duration.millis(1000));
            ft.setCycleCount(1);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.setNode(msgBoxContainer);
            ft.setOnFinished(e->msgBoxContainer.setVisible(true));
            ft.play();

        }else{
            msgDrawer.close();
            FadeTransition ft=new FadeTransition();
            ft.setDuration(Duration.millis(1000));
            ft.setCycleCount(1);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setNode(msgBoxContainer);
            ft.setOnFinished(event -> ft.stop());
            ft.play();

        }
    }

    @FXML
    public void closeAction(ActionEvent evt){
        try {
            System.out.println("Close");
            observer.removeStudentObserver(observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent evt){
        ((Stage)((javafx.scene.control.Button)evt.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    private void restoreMaximize(ActionEvent event){
        Stage stage=((Stage)((javafx.scene.control.Button)event.getSource()).getScene().getWindow());
        if(!stage.isMaximized()){
            stage.setMaximized(true);
            btnMaximize.setGraphic(new ImageView(new javafx.scene.image.Image("/lk/ijse/examsimulator/ui/util/icons/maximize.png")));
            btnMaximize.getTooltip().setText("Restore Down");
        }else{
            stage.setMaximized(false);
            btnMaximize.setGraphic(new ImageView(new javafx.scene.image.Image("/lk/ijse/examsimulator/ui/util/icons/restore.png")));
            btnMaximize.getTooltip().setText("Maximize");
        }
    }

    @FXML
    private void getPaper(){
        try {
            Paper paper=observer.getPaper();
            System.out.println(paper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startStreaming(){
        if(!streamerThread.isRunning()){
            streamerThread.setName(studentId+" Thread");
                System.out.println("Streamer : "+streamerThread);
                streamerThread.start();

        }else {
            synchronized (THREAD_LOCK){
                System.out.println("notified");
                isBlock=false;
                THREAD_LOCK.notify();
            }

        }
    }

    public void stopStreaming(){
        if(streamerThread.isRunning()){
            isBlock=true;
        }
    }

    private class StreamerThread extends Thread{
        @Override
        public void run(){
            System.out.println("Started");
            synchronized (THREAD_LOCK){
                while (true) {
                    System.out.println("Running");
                    try {
                        BufferedImage capture = new Robot().createScreenCapture(screenRect);
                        JPEGImageEncoderImpl encoder = new JPEGImageEncoderImpl(baos);
                        encoder.encode(capture);
                        baos.flush();

                        service.streamScreen(baos.toByteArray());
                        baos.reset();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(100);
                        System.gc();
                        if(isBlock) THREAD_LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        boolean isRunning(){
            return this.isAlive();
        }



    }
}

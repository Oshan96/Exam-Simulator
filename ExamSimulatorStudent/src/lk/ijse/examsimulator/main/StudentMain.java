package lk.ijse.examsimulator.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.examsimulator.proxy.ProxyHandler;
import lk.ijse.examsimulator.service.ServiceFactory;
import lk.ijse.examsimulator.service.custom.StudentService;
import lk.ijse.examsimulator.ui.controller.StudentUI;
import lk.ijse.examsimulator.ui.util.helpers.ResizeHelper;

import java.awt.*;


/**
 * Created by oshan on 16-Dec-17.
 * @author oshan
 */
public class StudentMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/examsimulator/ui/fxml/StudentLogin.fxml"))));
        primaryStage.show();

//        final Process process;
//        try {
//            if (System.getProperty("os.name").contains("Windows")) {
//                process = new ProcessBuilder("tasklist.exe", "/v").start();
//            } else {
//                return;
//            }
//            new Thread(() -> {
//                Scanner sc = new Scanner(process.getInputStream());
//                if (sc.hasNextLine()) sc.nextLine();
//                while (sc.hasNextLine()) {
//                    String line = sc.nextLine();
//                    String[] split = line.split("\\s");
//                    if(split.equals("chrome.exe")){
//                        try {
//                            service.notifyRestrictedAppBehaviour("s001");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }

    }
}

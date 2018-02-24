package lk.ijse.examsimulator.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.examsimulator.ui.controller.Application;
import lk.ijse.examsimulator.ui.util.helpers.ResizeHelper;

import java.awt.*;

/**
 * Created by oshan on 16-Dec-17.
 * @author oshan
 */
public class Main extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/lk/ijse/examsimulator/ui/fxml/Application.fxml"));

        Scene scene = new Scene(loader.load());

        Application controller=loader.getController();

        primaryStage.setOnCloseRequest(e->{
            controller.closeAction(new ActionEvent());
        });

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        ResizeHelper.addResizeListener(primaryStage);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        primaryStage.setWidth(screen.getWidth()/1.2);
        primaryStage.setHeight(screen.getHeight()/1.2);
        primaryStage.getIcons().add(new Image("/lk/ijse/examsimulator/ui/util/icons/logo.png"));
        primaryStage.show();
//        primaryStage.setAlwaysOnTop(true);

//////////////////////////////////////////////////////////////////////////////////
        /*
            Get running applications list -> Done -> Optimization left.
         */
//        final Process process;
//        if (System.getProperty("os.name").contains("Windows")){
//            process = new ProcessBuilder("tasklist.exe", "/v").start();
//         }else{
//            return;
//        }
//        new Thread(() -> {
//            Scanner sc = new Scanner(process.getInputStream());
//            if (sc.hasNextLine()) sc.nextLine();
//            while (sc.hasNextLine()) {
//                String line = sc.nextLine();
//                String[] split=line.split("\\s");
//                System.out.println(split[0]);
//            }
//        }).start();
////        process.waitFor();
///////////////////////////////////////////////////////////////////////////////////

    }
}

package lk.ijse.examsimulator.ui.controller;


/**
 * Created by oshan on 26-Jan-18.
 *
 * @author oshan
 */
abstract class AbstractController {

    private static Application mainController;


    public static void setMainController(Application mainController) {
        AbstractController.mainController=mainController;
    }

    public static Application getMainController(){
        return mainController;
    }
}

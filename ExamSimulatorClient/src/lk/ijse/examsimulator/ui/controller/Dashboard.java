package lk.ijse.examsimulator.ui.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.util.json.deserializer.PaperDeserializer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by oshan on 22-Jan-18.
 *
 * @author oshan
 */
public class Dashboard extends AbstractController implements Initializable {
    @FXML
    TextField txtPath;
    @FXML
    AnchorPane root;
    @FXML
    StackPane rootStack;
    @FXML
    JFXButton btnNext;

    private AnchorPane paperDetailsPanel;
    private StackPane dashPanel;
    private PaperDetails paperController;

    public Dashboard(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/lk/ijse/examsimulator/ui/fxml/PaperDetails.fxml"));

        try {
            paperDetailsPanel =loader.load();
            paperController=loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JFXSnackbar snackbar=new JFXSnackbar();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        root.setOnDragEntered(event -> {
            snackbar.registerSnackbarContainer(root);
            snackbar.show("Drop JSON paper file to start exam",100000);
        });

        root.setOnDragOver(e->{
            Dragboard db=e.getDragboard();
            boolean isAccepted=db.getFiles().get(0).getName().endsWith(".json");
            if(db.hasFiles()){
                if(isAccepted){
                    e.acceptTransferModes(TransferMode.COPY);
                }
            }else{
                e.consume();
            }
        });

        root.setOnDragDropped(event -> {
            Dragboard db=event.getDragboard();
            if(db.hasFiles()){
                txtPath.setText(db.getFiles().get(0).getAbsolutePath());
                btnNext.setDisable(false);
            }else{
                event.consume();
            }
        });

        root.setOnDragExited(event -> {
            snackbar.unregisterSnackbarContainer(root);
        });

    }
    @FXML
    private void nextClick(ActionEvent event){
        File file=new File(txtPath.getText());
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(Paper.class, new PaperDeserializer());
        Gson gson=builder.create();
        try(Reader reader=new FileReader(file)){
            Paper paper=gson.fromJson(reader,Paper.class);
            System.out.println(paper);
            dashPanel=rootStack;
            Platform.runLater(()->{
                setView(paperDetailsPanel);
                paperController.setPaper(paper);
                paperController.setMainController(this);
                paperController.setRoot(paperDetailsPanel);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setView(Node pane){
        Platform.runLater(()->{
            rootStack.getChildren().clear();
            rootStack.getChildren().setAll(pane);
        });

    }
}


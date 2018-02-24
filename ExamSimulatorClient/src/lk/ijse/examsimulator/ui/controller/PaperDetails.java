package lk.ijse.examsimulator.ui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.examsimulator.dto.CodingQuestion;
import lk.ijse.examsimulator.dto.MCQQuestion;
import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.ui.table_model.PaperDetailModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by oshan on 22-Jan-18.
 *
 * @author oshan
 */
public class PaperDetails extends AbstractController implements Initializable {
    private Paper paper;
    private Dashboard mainController;
    private StartExam startExamController;
    private AnchorPane startExamPane;
    private Node root;

    @FXML
    private Label lblMCQ,lblCoding,lblPaperNo,lblTotal;
    @FXML
    private TextField txtDuration;
    @FXML
    private JFXButton btnBack,btnForawrd;
    @FXML
    private TableView<PaperDetailModel> tblDetail;
    @FXML
    private TableColumn<PaperDetailModel,String> colQid;
    @FXML
    private TableColumn<PaperDetailModel,String> colType;
    @FXML
    private TableColumn<PaperDetailModel,String> colQuestion;
    @FXML
    private TableColumn<PaperDetailModel,String> colAnswer;
    @FXML
    private TableColumn<PaperDetailModel,Integer> colMarks;

    public void setPaper(Paper paper){
        this.paper=paper;
        updateUI();
        for(MCQQuestion q : paper.getMcqQuestions()){
            tblDetail.getItems().add(
                    new PaperDetailModel(
                            q.getQid(),
                            "MCQ",
                            q.getQuestion(),
                            q.getAnswer(),
                            q.getMarks()
                    )
            );
        }

        for(CodingQuestion q : paper.getCodingQuestions()){
            tblDetail.getItems().add(
                    new PaperDetailModel(
                            q.getQid(),
                            "Coding",
                            q.getQuestion(),
                            q.getAnswer(),
                            q.getMarks()
                    )
            );
        }
    }

    public void setMainController(Dashboard mainController){
        this.mainController=mainController;
    }

    public void setRoot(Node root){
        this.root=root;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colQid.setCellValueFactory(new PropertyValueFactory<>("qid"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQuestion.setCellValueFactory(new PropertyValueFactory<>("question"));
        colAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        colMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));

        colQuestion.prefWidthProperty().bind(
                tblDetail.widthProperty()
                .subtract(colQid.widthProperty())
                .subtract(colType.widthProperty())
                .subtract(colAnswer.widthProperty())
                .subtract(colMarks.widthProperty())
        );

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/lk/ijse/examsimulator/ui/fxml/StartExam.fxml"));
        try {
            startExamPane=loader.load();
            startExamController=loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        txtDuration.setOnKeyTyped(event -> {
            boolean isDigit=event.getCharacter().matches("\\d");
            if(txtDuration.getText().isEmpty() && event.getCharacter().equals(".")){
                event.consume();
                return;
            }
            if(txtDuration.getText().contains(".") && !isDigit) {
                event.consume();
            }else if(!isDigit && !event.getCharacter().equals(".")){
                event.consume();
            }
        });

        txtDuration.setOnKeyPressed(event -> {
            if(event.getCode()!= KeyCode.BACK_SPACE)event.consume();
        });

        txtDuration.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(txtDuration.isFocused() && !txtDuration.getText().isEmpty()){
                Platform.runLater(()-> txtDuration.selectAll());

            }
        });



    }

    private void updateUI(){
        lblMCQ.setText("MCQ Questions\n"+paper.getMcqQuestions().size());
        lblCoding.setText("Coding Questions\n"+paper.getCodingQuestions().size());
        lblTotal.setText("Total Marks\n"+paper.getTotalMarks());
        lblPaperNo.setText("Paper No. : "+paper.getPid());
    }

    @FXML
    private void goBack(){
        try {
            mainController.setView(FXMLLoader.load(getClass().getResource("/lk/ijse/examsimulator/ui/fxml/Dashboard.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void next(){
        startExamController.setMainController(mainController);
        startExamController.setDuration(Double.parseDouble(txtDuration.getText()));
        startExamController.setPreviousPane(root);
        startExamController.setPaper(paper);
        mainController.setView(startExamPane);

    }


}

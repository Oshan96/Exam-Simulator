package lk.ijse.examsimulator.ui.controller;

import com.jfoenix.controls.JFXButton;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import lk.ijse.examsimulator.dto.Paper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by oshan on 26-Jan-18.
 *
 * @author oshan
 */
public class StartExam extends AbstractController implements Initializable{
    private Dashboard mainController;
    private Node previousPane;

    private Paper paper;

    private static final int SECONDS_PER_DAY    = 86_400;
    private static final int SECONDS_PER_HOUR   = 3600;
    private static final int SECONDS_PER_MINUTE = 60;

    private long lastTimerCall;

    private Tile hours;
    private Tile minutes;
    private Tile seconds;

    private Label hoursLabel;
    private Label minutesLabel;
    private Label secondsLabel;

    private Duration duration;

    private AnimationTimer timer;

    @FXML
    private HBox countContainer;
    @FXML
    JFXButton btnBack;

    public StartExam() {


        hours    = createTile("HOURS", "0");
        minutes  = createTile("MINUTES", "0");
        seconds  = createTile("SECONDS", "0");

         hoursLabel = createLabel("HOURS");
         minutesLabel = createLabel("MINUTES");
         secondsLabel = createLabel("SECONDS");

//        duration= Duration.hours(2);

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now > lastTimerCall + 1_000_000_000l) {
                    duration = duration.subtract(Duration.seconds(1));

                    int remainingSeconds = (int) duration.toSeconds();

                    int h = (remainingSeconds % SECONDS_PER_DAY) / SECONDS_PER_HOUR;
                    int m = ((remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE;
                    int s = (((remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) % SECONDS_PER_MINUTE);

                    if (h == 0 && m == 0 && s == 0) { timer.stop(); }

                    hours.setDescription(Integer.toString(h));
                    minutes.setDescription(String.format("%02d", m));
                    seconds.setDescription(String.format("%02d", s));

                    lastTimerCall = now;
                }
                System.gc();
            }

        };
    }

    private Tile createTile(final String TITLE, final String TEXT) {
        return TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(200, 200)
//                .title(TITLE)
//                .titleAlignment(TextAlignment.CENTER)
                .description(TEXT)
                .build();
    }

    private Label createLabel(final String TEXT) {

        final Font FONT  = Fonts.latoRegular(24);
        final Label LABEL = new Label(TEXT);
        LABEL.setFont(FONT);
        LABEL.setTextFill(Tile.FOREGROUND);
        LABEL.setAlignment(Pos.CENTER);
        LABEL.setPrefWidth(200);
        LABEL.getStyleClass().add("countdownLabel");
        return LABEL;
    }

    public void setMainController(Dashboard mainController){
        this.mainController=mainController;
    }

    public void setPreviousPane(Node previousPane) {
        this.previousPane = previousPane;
    }

    public void setDuration(double time){
        duration=Duration.hours(time);
        int h = duration.toHours()%24!=0?(int)duration.toHours()%24:0;
        int m = duration.toMinutes()%60!=0?(int)duration.toMinutes()%60:0;
        int s= duration.toSeconds()%60!=0?(int)duration.toSeconds()%60:0;

        hours.setDescription(String.format("%02d", h));
        minutes.setDescription(String.format("%02d", m));
        seconds.setDescription(String.format("%02d", s));

    }

    public void setPaper(Paper paper){
        this.paper=paper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HBox upper = new HBox(20, hours, minutes, seconds);
        upper.setPadding(new Insets(10));

        HBox middle = new HBox(20, hoursLabel, minutesLabel, secondsLabel);
        middle.setPadding(new Insets(10));
        VBox.setMargin(middle, new Insets(20, 0, -10, 0));

        VBox pane = new VBox(10,middle, upper);
        pane.setPadding(new Insets(10));
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        pane.setAlignment(Pos.CENTER);
        countContainer.getChildren().add(pane);

    }

    @FXML
    private void startExam(ActionEvent evt){
        timer.start();
        btnBack.setDisable(true);
        ((JFXButton)evt.getSource()).setDisable(true);
        AbstractController.getMainController().uploadPaper(paper);
    }

    @FXML
    private void goBack(){
        mainController.setView(previousPane);
    }
}

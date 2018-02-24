package testClasses;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;

/**
 * Created by oshan on 26-Jan-18.
 *
 * @author oshan
 */
@SuppressWarnings("Duplicates")
public class Countup extends Application {
    private static final int SECONDS_PER_DAY    = 86_400;
    private static final int SECONDS_PER_HOUR   = 3600;
    private static final int SECONDS_PER_MINUTE = 60;

    private int h;
    private int m;
    private int s;
    private long lastTimerCall;

    private Tile hours;
    private Tile minutes;
    private Tile seconds;

    private Label hoursLabel;
    private Label minutesLabel;
    private Label secondsLabel;

    private Duration duration;

    private AnimationTimer timer;

    private Tile createTile(final String TITLE, final String TEXT) {
        return TileBuilder.create().skinType(Tile.SkinType.CHARACTER)
                .prefSize(200, 200)
                .title(TITLE)
                .titleAlignment(TextAlignment.CENTER)
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
        return LABEL;
    }

    @Override
    public void init(){
        hours    = createTile("HOURS", "0");
        minutes  = createTile("MINUTES", "0");
        seconds  = createTile("SECONDS", "0");

        hoursLabel   = createLabel("HOURS");
        minutesLabel = createLabel("MINUTES");
        secondsLabel = createLabel("SECONDS");

        duration= Duration.hours(0);

        lastTimerCall = System.nanoTime();



        timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                if (now > lastTimerCall + 1_000_000_000l) {

                    duration=duration.add(Duration.seconds(1));

                    int remainingSeconds =  (int) duration.toSeconds();

                    int h = (remainingSeconds % SECONDS_PER_DAY) / SECONDS_PER_HOUR;
                    int m = ((remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE;
                    int s = (((remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) % SECONDS_PER_MINUTE);


                    hours.setDescription(Integer.toString(h));
                    minutes.setDescription(String.format("%02d", m));
                    seconds.setDescription(String.format("%02d", s));

                    lastTimerCall = now;
                }

            }
        };
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox upper = new HBox(20, hours, minutes, seconds);
        upper.setPadding(new Insets(10));

//        HBox middle = new HBox(20, hoursLabel, minutesLabel, secondsLabel);
//        middle.setPadding(new Insets(10));
//        VBox.setMargin(middle, new Insets(20, 0, -10, 0));

        VBox pane = new VBox(10, upper);
        pane.setPadding(new Insets(10));
        pane.setBackground(new Background(new BackgroundFill(Tile.BACKGROUND.brighter(), CornerRadii.EMPTY, Insets.EMPTY)));

//        PerspectiveCamera camera = new PerspectiveCamera();
//        camera.setFieldOfView(7);

        Scene scene = new Scene(pane);
//        scene.setCamera(camera);

        primaryStage.setTitle("CountUp");
        primaryStage.setScene(scene);
        primaryStage.show();

        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

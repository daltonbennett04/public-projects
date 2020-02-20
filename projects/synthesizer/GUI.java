package Synthesizer;

import Synthesizer.Generators.SineWave;
import Synthesizer.Widgets.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.util.ArrayList;
import java.util.Random;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Synthesizer");
        Button playButton = new Button("Play");

        BorderPane app = new BorderPane();
        ArrayList<WidgetBase> widgets = new ArrayList<>();

        Pane playPane = new Pane();
        app.setCenter(playPane);
        playPane.setPrefSize(700, 700);
        playPane.setStyle("-fx-background-color: GRAY");

        //speaker widget always present
        SpeakerWidget sw = new SpeakerWidget();
        sw.getNode().relocate(700, 700);
        playPane.getChildren().add(sw.getNode());

        widgets.add(sw);

        Pane linePane = new Pane();

        //right side button panel
        VBox buttonBox = new VBox();
        Random random = new Random();
        app.setRight(buttonBox);
        buttonBox.setSpacing(10);
        //sinewave button
        Button sineWaveGenerator = new Button("New Sinewave");
        buttonBox.getChildren().add(sineWaveGenerator);
        sineWaveGenerator.setOnAction(e ->{
            SineWaveWidget sinewave = new SineWaveWidget();
            widgets.add(sinewave);
            sinewave.getNode().relocate(Math.abs(random.nextInt()%700), Math.abs(random.nextInt()%700));
            playPane.getChildren().add(sinewave.getNode());
        });
        //squarewave button
        Button squareWaveGenerator = new Button("New Squarewave");
        buttonBox.getChildren().add(squareWaveGenerator);
        squareWaveGenerator.setOnAction(e ->{
            SquareWaveWidget squarewave = new SquareWaveWidget();
            widgets.add(squarewave);
            squarewave.getNode().relocate(Math.abs(random.nextInt()%700), Math.abs(random.nextInt()%700));
            playPane.getChildren().add(squarewave.getNode());
        });
        //ascending wave button
        Button ascSineWaveGenerator = new Button("New Ascending Sinewave");
        buttonBox.getChildren().add(ascSineWaveGenerator);
        ascSineWaveGenerator.setOnAction(e ->{
            AscendingWidget ascSinewave = new AscendingWidget();
            widgets.add(ascSinewave);
            ascSinewave.getNode().relocate(Math.abs(random.nextInt()%700), Math.abs(random.nextInt()%700));
            playPane.getChildren().add(ascSinewave.getNode());
        });
        //volume button
        Button volumeGenerator = new Button("Volume");
        buttonBox.getChildren().add(volumeGenerator);
        volumeGenerator.setOnAction(e ->{
            VolumeWidget volWidget = new VolumeWidget();
            widgets.add(volWidget);
            volWidget.getNode().relocate(Math.abs(random.nextInt()%700), Math.abs(random.nextInt()%700));
            playPane.getChildren().add(volWidget.getNode());
        });
        //mixer button
        Button mixerButton = new Button("Mixer");
        buttonBox.getChildren().add(mixerButton);
        mixerButton.setOnAction(e ->{
            MixerWidget mixer = new MixerWidget();
            widgets.add(mixer);
            mixer.getNode().relocate(Math.abs(random.nextInt()%700), Math.abs(random.nextInt()%700));
            playPane.getChildren().add(mixer.getNode());
        });
        //clear button
        Button clearButton = new Button("Clear Screen");
        buttonBox.getChildren().add(clearButton);
        clearButton.setOnAction(e ->{
            playPane.getChildren().clear();
            widgets.clear();
            sw.setAudioComponent(new Speaker());
            widgets.add(sw);
            sw.getNode().relocate(700, 700);
            playPane.getChildren().add(sw.getNode());
            playPane.getChildren().add(linePane);
        });

        //bottom play button
        app.setBottom(playButton);
        app.setAlignment(playButton, Pos.BOTTOM_CENTER);

        Line dynamicLine = new Line(0,0,0,0);
        dynamicLine.setStyle("-fx-stroke: HOTPINK;" +
                             "-fx-stroke-width: 2;");
        playPane.getChildren().add(linePane);
        final WidgetBase[] inputBase = {null};
        final WidgetBase[] outputBase = {null};
        final boolean[] drawing = {false};
            playPane.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            for(WidgetBase w : widgets){
                                if(w.getOutputJack() == null){continue;};
                                if (w.getOutputJack().isPressed()) {
                                    drawing[0] = true;
                                    inputBase[0] = w;
                                    dynamicLine.setStartX(mouseEvent.getSceneX());
                                    dynamicLine.setStartY(mouseEvent.getSceneY());
                                    System.out.println("Pressed");
                                    playPane.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                                            new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent mouseEvent) {
                                                    if(drawing[0]) {
                                                        linePane.getChildren().remove(dynamicLine);
                                                        dynamicLine.setEndX(mouseEvent.getSceneX());
                                                        dynamicLine.setEndY(mouseEvent.getSceneY());
                                                        linePane.getChildren().add(dynamicLine);
                                                        linePane.toFront();
                                                    }
                                                }
                                            });
                                    playPane.addEventHandler(MouseEvent.MOUSE_RELEASED,
                                            new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent mouseEvent) {
                                                    drawing[0] = false;
                                                    for(WidgetBase wb : widgets){
                                                        System.out.println("Secondloop");
                                                        if(wb.getInputJack() == null){continue;};
                                                        Circle ij = wb.getInputJack();
                                                        double mouseReleaseX = mouseEvent.getX();
                                                        double mouseReleaseY = mouseEvent.getY();
                                                        Point2D releasePoint = new Point2D(mouseReleaseX, mouseReleaseY);
                                                        if(ij.localToScene(ij.getBoundsInLocal()).contains(releasePoint)){
                                                            outputBase[0] = wb;
                                                            Cable linker = new Cable(inputBase[0], outputBase[0], playPane);
                                                            inputBase[0].addCable(linker);
                                                            outputBase[0].addCable(linker);
                                                            System.out.println("Linked");
                                                            break;
                                                        }
                                                    }
                                                    linePane.getChildren().remove(dynamicLine);
                                                }
                                            });
                                    break;
                                }

                        }
                        }
                    });

        playButton.setOnAction(e -> {
            try {
                Clip c = AudioSystem.getClip();
                AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
                AudioClip clip = sw.getAudioComponent().getClip();
                c.open(format16, clip.getData(), 0, clip.getData().length);
                c.start();
                c.loop(2);
                //while(c.getFramePosition() < AudioClip.sampleRate || c.isActive() || c.isRunning()){}
                //c.close();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
            catch (NullPointerException npe){
                System.out.println("No inputs.");
            }
        });

        primaryStage.setScene(new Scene(app, 1000, 1000));



        primaryStage.show();
    }
}

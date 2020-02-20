package Synthesizer;

import Synthesizer.Widgets.WidgetBase;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Cable {

    WidgetBase input;
    WidgetBase output;
    Line line;
    Pane drawablePane;
    AudioComponent acIn;
    AudioComponent acOut;

    public Cable(WidgetBase soundFrom, WidgetBase soundTo, Pane drawOnMe){
        output = soundFrom;
        input = soundTo;
        drawablePane = drawOnMe;
        acIn = soundFrom.getAudioComponent();
        acOut = soundTo.getAudioComponent();
        acOut.connectInput(0, acIn);
        //updateLine();
        drawLine();
    }

    public void updateLine(){
            //drawablePane.getChildren().remove(line);
            Point2D point1 = output.getOutputJack().localToScene(output.getOutputJack().getCenterX(), output.getOutputJack().getCenterY());
            Point2D point2 = input.getInputJack().localToScene(input.getInputJack().getCenterX(), input.getInputJack().getCenterY());
            line.setStartX(point1.getX());
            line.setStartY(point1.getY());
            line.setEndX(point2.getX());
            line.setEndY(point2.getY());
            //drawablePane.getChildren().add(line);
    }

    public void drawLine(){
        Platform.runLater(() -> updateLine());
        line = new Line();
        line.setStyle("-fx-stroke: GOLD;" +
                "-fx-stroke-width: 4;");
        drawablePane.getChildren().add(line);
    }
}

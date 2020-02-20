package Synthesizer.Widgets;

import Synthesizer.Generators.SineWave;
import Synthesizer.Generators.SquareWave;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SquareWaveWidget extends WidgetBase {
    public SquareWave sqw;

    public SquareWaveWidget(){
        super("SquareWave");

        //create slider
        Slider squareSlider = new Slider(200, 1800, 440);
        squareSlider.setShowTickMarks(true);
        squareSlider.setMajorTickUnit(400);
        squareSlider.setShowTickLabels(true);

        //create label for frequency that updates as you move slider
        final Label[] label2 = {new Label("Freq: " + String.format("%.0f", squareSlider.getValue()))};

        //creates new sinewave and sets to audio component of base
        sqw = new SquareWave(440, 30000);
        super.setAudioComponent(sqw);

        //listening for slider movement and updating label
        squareSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number oldVal, Number newVal) {
                sqw.setFrequency((float) newVal.doubleValue());
                label2[0].setText("Freq: " + String.format("%.0f", newVal));
            }});

        //create output node
        super.getOutputNode();

        //pushes label back into base class
        super.setDisplayGrid(squareSlider, label2[0]);

    }

    //method to get node of widget since a class can't be added to a scene
    public Node getNode(){
        return super.widgetBox;
    }
}

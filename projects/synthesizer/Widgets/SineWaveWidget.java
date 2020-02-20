package Synthesizer.Widgets;
import Synthesizer.Generators.SineWave;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SineWaveWidget extends WidgetBase{
    public SineWave sw;

    public SineWaveWidget(){
        super("SineWave");

        //create slider
        Slider sineSlider = new Slider(200, 1800, 440);
        sineSlider.setShowTickMarks(true);
        sineSlider.setMajorTickUnit(400);
        sineSlider.setShowTickLabels(true);

        //create label for frequency that updates as you move slider
        final Label[] label2 = {new Label("Freq: " + String.format("%.0f", sineSlider.getValue()))};

        //creates new sinewave and sets to audio component of base
        sw = new SineWave(440, 30000);
        super.setAudioComponent(sw);

        //listening for slider movement and updating label
        sineSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number oldVal, Number newVal) {
                sw.setFrequency(newVal.doubleValue());
                label2[0].setText("Freq: " + String.format("%.0f", newVal));
            }});

        //create output node
        super.getOutputNode();

        //pushes label back into base class
        super.setDisplayGrid(sineSlider, label2[0]);

    }

    //method to get node of widget since a class can't be added to a scene
    public Node getNode(){
        return super.widgetBox;
    }

}

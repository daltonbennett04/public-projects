package Synthesizer.Widgets;

import Synthesizer.Generators.AscendingSineWave;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class AscendingWidget extends WidgetBase {

    public AscendingSineWave asw;

    public AscendingWidget(){
        super("Ascending SineWave");

        //create slider
        Slider ascSineSlider = new Slider(200, 1800, 440);
        ascSineSlider.setShowTickMarks(true);
        ascSineSlider.setMajorTickUnit(400);
        ascSineSlider.setShowTickLabels(true);

        //create label for frequency that updates as you move slider
        final Label[] label2 = {new Label("Freq: " + String.format("%.0f", ascSineSlider.getValue()))};

        //creates new ascending sinewave and sets to audio component of base
        asw = new AscendingSineWave(440, 30000);
        super.setAudioComponent(asw);

        //listening for slider movement and updating label
        ascSineSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number oldVal, Number newVal) {
                asw.setFrequency(newVal.doubleValue());
                label2[0].setText("Freq: " + String.format("%.0f", newVal));
            }});

        //create output node
        super.getOutputNode();

        //pushes label back into base class
        super.setDisplayGrid(ascSineSlider, label2[0]);

    }

    //method to get node of widget since a class can't be added to a scene
    public Node getNode(){
        return super.widgetBox;
    }

}

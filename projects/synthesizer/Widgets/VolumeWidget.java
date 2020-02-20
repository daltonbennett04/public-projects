package Synthesizer.Widgets;

import Synthesizer.AudioComponent;
import Synthesizer.Generators.SineWave;
import Synthesizer.VolumeFilter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;

public class VolumeWidget extends WidgetBase {

    AudioComponent ac1;
    VolumeFilter vFilter;

    public VolumeWidget(){
        super("Volume");{

            //create slider
            Slider volumeSlider = new Slider(0, 2, 0.5);
            volumeSlider.setShowTickMarks(true);
            volumeSlider.setMajorTickUnit(0.25);
            volumeSlider.setMinorTickCount(0);
            volumeSlider.setShowTickLabels(true);

            //create dynamic label
            final Label[] label2 = {new Label("Vol: " + String.format("%.2f", volumeSlider.getValue()))};
            super.setDisplayGrid(volumeSlider, label2[0]);

            //new volume filter to set base audio component
            vFilter = new VolumeFilter(0.5);
            super.setAudioComponent(vFilter);

            //listen for slider movement, update dynamic label
            volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov,
                                    Number oldVal, Number newVal) {
                    vFilter.setVolume(newVal.doubleValue());
                    label2[0].setText("Vol: " + String.format("%.2f", newVal));
                }});

            //input label
            super.getInputNode();

            //output label
            super.getOutputNode();
        }
    }

    public Node getNode(){
        return super.widgetBox;
    }
}

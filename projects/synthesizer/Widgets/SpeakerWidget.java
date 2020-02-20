package Synthesizer.Widgets;

import Synthesizer.Speaker;
import Synthesizer.Widgets.WidgetBase;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpeakerWidget extends WidgetBase {

    private Speaker speaker;

    public SpeakerWidget(){
        super("Speaker");
        speaker = new Speaker();
        super.setAudioComponent(speaker);

        //create speaker node
        super.getInputNode();

        //resize
        super.widgetBox.setPrefSize(100, 50);
    }

    public Node getNode(){
        return super.widgetBox;
    }

}

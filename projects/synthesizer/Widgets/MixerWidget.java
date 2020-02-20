package Synthesizer.Widgets;

import Synthesizer.Mixer;
import javafx.scene.Node;

public class MixerWidget extends WidgetBase {

    private Mixer mixer;

    public MixerWidget(){
        super("Mixer");
        mixer = new Mixer();
        super.setAudioComponent(mixer);

        super.getInputNode();
        super.getOutputNode();

        //resize
        super.widgetBox.setPrefSize(100, 50);

    }

    public Node getNode(){
        return super.widgetBox;
    }


}

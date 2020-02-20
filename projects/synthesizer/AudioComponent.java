package Synthesizer;

public interface AudioComponent {

    AudioClip getClip();
    int getNumInputs();
    String getInputName(int index);
    void connectInput(int index, AudioComponent input);

}

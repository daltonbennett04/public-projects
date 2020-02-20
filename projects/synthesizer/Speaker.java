package Synthesizer;

public class Speaker implements AudioComponent {

    private AudioComponent input;

    @Override
    public AudioClip getClip() {
        return input.getClip();
    }

    @Override
    public int getNumInputs() {
        return 0;
    }

    @Override
    public String getInputName(int index) {
        return null;
    }

    @Override
    public void connectInput(int index, AudioComponent input) {
        this.input = input;
    }
}

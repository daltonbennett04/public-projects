package Synthesizer.Generators;

import Synthesizer.AudioClip;
import Synthesizer.AudioComponent;

public class SquareWave implements AudioComponent {

    public float frequency;
    public int amplitude;


    public SquareWave(float pitch, int a){
        frequency = pitch;
        amplitude = a;
    }

    @Override
    public AudioClip getClip() {
        AudioClip newClip = new AudioClip();
        for (int i = 0; i < 44100; i++) {
            float sample = 0;
            if ((frequency * i / AudioClip.sampleRate) % 1 > 0.5) {
                sample = amplitude;
            } else {
                sample = -amplitude;
            }
            newClip.setSample(i, (short) sample);
        }
        return newClip;
    }

    public void setFrequency(float freq) {
        frequency = freq;
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

    }
}

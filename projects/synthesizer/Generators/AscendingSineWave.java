package Synthesizer.Generators;

import Synthesizer.AudioClip;
import Synthesizer.AudioComponent;

public class AscendingSineWave implements AudioComponent {

    public double frequency = 0;
    public int amplitude = 10;

    public AscendingSineWave(double pitch){
        frequency = pitch;
    }

    public AscendingSineWave (double pitch, int a){
        frequency = pitch;
        amplitude = a;
    }

    public void setFrequency(double freq) {
        frequency = freq;
    }

    @Override
    public AudioClip getClip() {
        AudioClip newClip = new AudioClip();
        for (int i = 0; i < 44100; i++) {
            frequency = frequency * 1.000005;
            newClip.setSample( i , (short) (amplitude * Math.sin(2 * Math.PI * frequency * i / AudioClip.sampleRate)));
        }
        return newClip;
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

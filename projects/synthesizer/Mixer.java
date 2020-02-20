package Synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent{

    //private AudioComponent input1;
    //private AudioComponent input2;
    protected ArrayList<AudioComponent> audioComponents = new ArrayList<AudioComponent>();

    public Mixer(){
        //need code here?
    }

    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        for(AudioComponent ac : audioComponents) {
            AudioClip clip = ac.getClip();
            for (int i = 0; i < 44100; i++) {
                int sum =  clip.getSample(i) + result.getSample(i);
                if(sum > Short.MAX_VALUE){sum = Short.MAX_VALUE;};
                if(sum < Short.MIN_VALUE){sum = Short.MIN_VALUE;}
                result.setSample(i, (short) sum);
            }
        }
        return result;
    }

    @Override
    public int getNumInputs() {
        return 2;
    }

    @Override
    public String getInputName(int index) {
        return null;
    }

    @Override
    public void connectInput(int index, AudioComponent input) {
        audioComponents.add(input);
    }
}

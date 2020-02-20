package Synthesizer;

public class VolumeFilter implements AudioComponent {

    private AudioComponent input;
    private double volScale;

    public VolumeFilter(double volumeScale){
        volScale = volumeScale;
    }

    @Override
    public AudioClip getClip() {
        AudioClip original = input.getClip();
        AudioClip result = new AudioClip();
        for (int i = 0; i < 44100; i++){
            double sample = (original.getSample(i)*volScale);
            if(sample > Short.MAX_VALUE){sample = Short.MAX_VALUE;};
            if(sample < Short.MIN_VALUE){sample = Short.MIN_VALUE;};
            result.setSample(i , (short) (sample));
        }
        return result;
    }

    public void setVolume(double vol){
        volScale = vol;
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

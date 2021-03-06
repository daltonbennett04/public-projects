package Synthesizer;

import Synthesizer.Generators.SineWave;
import Synthesizer.Generators.SquareWave;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Main {

    public static void main(String[] args) throws LineUnavailableException {

        //Synthesizer.Generators.SineWave sineWave = new Synthesizer.Generators.SineWave(440, 10);

        //get properties from the system about samples rates, etc
        Clip c = AudioSystem.getClip(); //terrible name

//This is the format that we're following, 44.1KHz mono audio, 16 bits per sample
        AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

        AudioComponent gen = new SineWave(440, 30000);
        AudioComponent gen2 = new SineWave(40, 30000);
        AudioComponent vol = new VolumeFilter(0.5);
        AudioComponent square = new SquareWave(440, 30000);
        AudioComponent mixed = new Mixer();
        mixed.connectInput(0, new SquareWave(300, 30000));
        mixed.connectInput(0, new SquareWave(400, 30000));
        vol.connectInput(0, mixed);
        AudioClip clip = vol.getClip();

        c.open(format16, clip.getData(), 0, clip.getData().length); //reads data from my byte array to play it

        System.out.println("about to play");
        c.start(); //plays it
        c.loop(2); //plays it 2 more times if desired, so 3 seconds total

//makes sure the program doesn't quit before the sound plays
        while(c.getFramePosition() < AudioClip.sampleRate || c.isActive() || c.isRunning()){}

        System.out.println("done");
        c.close();

    }
}

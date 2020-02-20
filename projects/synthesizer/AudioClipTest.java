package Synthesizer;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AudioClipTest {

    @org.junit.jupiter.api.Test
    void setSample() {
        AudioClip ac = new AudioClip();
        ac.setSample(0,(short) -513);
        //assertEquals(ac.getData()[0], -);
        //assertEquals(ac.getData()[1], 2);
        assertEquals(ac.getSample(0), -513);
    }

    @org.junit.jupiter.api.Test
    void setRandomSample() {
        AudioClip ac = new AudioClip();
        Random rdm = new Random();
        for(int i = 0; i < 32; i++){
            short r = (short) rdm.nextInt();
            ac.setSample(i, r);
            assertEquals(ac.getSample(i), r);
            System.out.println(r);
            System.out.println(ac.getSample(i));
        }

        //assertEquals(ac.getData()[0], -);
        //assertEquals(ac.getData()[1], 2);
        //assertEquals(ac.getSample(0), -513);
    }
}
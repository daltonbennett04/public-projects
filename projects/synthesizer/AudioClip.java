package Synthesizer;

public class AudioClip {

    public static int duration = 1;
    public static int sampleRate = 44100;
    private byte[] soundArray = new byte[88200];

    public AudioClip(){
        for(int i = 0; i < 44100; i++){
            soundArray[i] = 0;
        }
    }

    public short getSample(int index){
        int byte1 = soundArray[2*index] & 0xFF;
        byte byte2 = soundArray[(2*index) + 1];
        short value = 0;
        value = byte2;
        value = (short) (value << 8);
        value = (short) (value | byte1);
        return value;
    }

    public void setSample(int index, short value){
        byte byte1 = (byte) value;
        byte byte2 = (byte) (value >> 8);
        soundArray[index*2] = byte1;
        soundArray[(index*2)+1] = byte2;

    }

    byte[] getData(){
        byte[] array1 = new byte[88200];
        for(int i = 0; i < 88200; i++){
            array1[i] = soundArray[i];
        }
        return array1;
    }



}

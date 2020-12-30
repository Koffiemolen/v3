package nl.StijveHark.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sound {
    private Clip clip;

    public Sound(File soundFile) throws IOException, UnsupportedAudioFileException {
        this.clip = (Clip) AudioSystem.getAudioInputStream(new File(String.valueOf(soundFile)).getAbsoluteFile());
    }

    public void playSound() {
        try {
            clip = AudioSystem.getClip();
            clip.open();
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void explosion() {
//        try {
//            AudioInputStream explosionInpuStream = AudioSystem.getAudioInputStream(new File("sounds/explosion.wav").getAbsoluteFile());
//            clip = AudioSystem.getClip();
//            clip.open(explosionInpuStream);
//            clip.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
